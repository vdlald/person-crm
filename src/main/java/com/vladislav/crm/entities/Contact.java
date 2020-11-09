package com.vladislav.crm.entities;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "contacts")
@AttributeOverride(name = "id", column = @Column(name = "contact_id", updatable = false, nullable = false))
@EntityListeners(AuditingEntityListener.class)
public class Contact extends AbstractEntityWithTime {

    @NotBlank
    @Size(min = 1, max = 32)
    @Column(name = "name", length = 32)
    @ToString.Include
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Include
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    @Setter(AccessLevel.PRIVATE)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "leads_contacts",
            joinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "contact_id"),
            inverseJoinColumns = @JoinColumn(name = "lead_id", referencedColumnName = "lead_id"))
    private Set<Lead> leads = new HashSet<>();

    public Contact setUserSafe(User newUser) {
        if (Objects.equals(user, newUser)) {
            return this;
        }

        if (user != null) {
            user.removeContact(this);
        }

        user = newUser;

        if (user != null) {
            user.addContact(this);
        }
        return this;
    }

    public Contact setCompanySafe(Company newCompany) {
        if (Objects.equals(company, newCompany)) {
            return this;
        }

        if (company != null) {
            company.removeContact(this);
        }

        company = newCompany;

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
