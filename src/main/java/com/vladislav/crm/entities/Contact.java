package com.vladislav.crm.entities;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true, exclude = {"user", "leads"})
@ToString(callSuper = true, exclude = {"user", "leads"})
@Entity
@Table(name = "contacts")
@AttributeOverride(name = "id", column = @Column(name = "contact_id", updatable = false, nullable = false))
@EntityListeners(AuditingEntityListener.class)
public class Contact extends AbstractEntity {

    @Size(min = 1, max = 32)
    @Column(name = "name", length = 32)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;

    @Setter(AccessLevel.PRIVATE)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "leads_contacts",
            joinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "contact_id"),
            inverseJoinColumns = @JoinColumn(name = "lead_id", referencedColumnName = "lead_id"))
    private List<Lead> leads = new ArrayList<>();

    @Column(name = "createdAt", nullable = false, updatable = false)
    @Setter(AccessLevel.PRIVATE)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updatedAt")
    @Setter(AccessLevel.PRIVATE)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Contact setUser(User newUser) {
        if (Objects.equals(user, newUser)) {
            return this;
        }

        final User oldUser = this.user;
        user = newUser;

        if (oldUser != null) {
            oldUser.removeContact(this);
        }

        if (user != null) {
            user.addContact(this);
        }
        return this;
    }

    public Contact setCompany(Company newCompany) {
        if (Objects.equals(company, newCompany))
            return this;

        final Company oldCompany = this.company;
        company = newCompany;

        if (oldCompany != null) {
            oldCompany.removeContact(this);
        }

        if (company != null) {
            newCompany.addContact(this);
        }
        return this;
    }

    public Contact addLead(Lead lead) {
        if (leads.contains(lead)) {
            return null;
        }
        leads.add(lead);
        lead.addContact(this);
        return this;
    }

    public Contact removeLead(Lead lead) {
        if (!leads.contains(lead)) {
            return null;
        }
        leads.remove(lead);
        lead.removeContact(this);
        return this;
    }

    public List<Lead> getLeads() {
        return new ArrayList<>(leads);
    }
}
