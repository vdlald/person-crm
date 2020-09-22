package com.vladislav.crm.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true, exclude = "contacts")
@EqualsAndHashCode(callSuper = true, exclude = "contacts")
@Entity(name = "Company")
@Table(name = "companies")
@AttributeOverride(name = "id", column = @Column(name = "company_id", updatable = false, nullable = false))
public class Company extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private List<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        if (contacts.contains(contact))
            return;
        contacts.add(contact);
        contact.setCompany(this);
    }

    public void removeContact(Contact contact) {
        if (!contacts.contains(contact))
            return;
        contacts.remove(contact);
        contact.setCompany(null);
    }

    public List<Contact> getContacts() {
        return new ArrayList<>(contacts);
    }
}
