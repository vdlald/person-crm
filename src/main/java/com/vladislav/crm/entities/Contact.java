package com.vladislav.crm.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true, exclude = "user")
@ToString(callSuper = true, exclude = {"user"})
@Entity(name = "Contact")
@Table(name = "contacts")
@AttributeOverride(name = "id", column = @Column(name = "contact_id", updatable = false, nullable = false))
public class Contact extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany
    @Setter(AccessLevel.PRIVATE)
    private List<Lead> leads = new ArrayList<>();

    public void setUser(User newUser) {
        if (Objects.equals(user, newUser))
            return;

        final User oldUser = this.user;
        user = newUser;

        if (oldUser != null)
            oldUser.removeContact(this);

        if (user != null)
            user.addContact(this);
    }

    public void setCompany(Company newCompany) {
        if (Objects.equals(company, newCompany))
            return;

        final Company oldCompany = this.company;
        company = newCompany;

        if (oldCompany != null)
            oldCompany.removeContact(this);

        if (company != null)
            newCompany.addContact(this);
    }

    public void addLead(Lead lead) {
        if (leads.contains(lead))
            return;
        leads.add(lead);
        lead.addContact(this);
    }

    public void removeLead(Lead lead) {
        if (!leads.contains(lead))
            return;
        leads.remove(lead);
        lead.removeContact(this);
    }

    public List<Lead> getLeads() {
        return new ArrayList<>(leads);
    }
}
