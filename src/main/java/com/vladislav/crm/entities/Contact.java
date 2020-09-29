package com.vladislav.crm.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Size;
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

    public void setCompany(Company newCompany) {
        if (Objects.equals(company, newCompany))
            return;

        final Company oldCompany = this.company;
        company = newCompany;

        if (oldCompany != null) {
            oldCompany.removeContact(this);
        }

        if (company != null) {
            newCompany.addContact(this);
        }
    }

    public void addLead(Lead lead) {
        if (leads.contains(lead)) {
            return;
        }
        leads.add(lead);
        lead.addContact(this);
    }

    public void removeLead(Lead lead) {
        if (!leads.contains(lead)) {
            return;
        }
        leads.remove(lead);
        lead.removeContact(this);
    }

    public List<Lead> getLeads() {
        return new ArrayList<>(leads);
    }
}
