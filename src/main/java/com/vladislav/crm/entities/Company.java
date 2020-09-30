package com.vladislav.crm.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

// Нужна ли связь с User ?
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true, exclude = "contacts")
@EqualsAndHashCode(callSuper = true, exclude = "contacts")
@Entity
@Table(name = "companies")
@AttributeOverride(name = "id", column = @Column(name = "company_id", updatable = false, nullable = false))
public class Company extends AbstractEntity {

    @Size(min = 1, max = 32)
    @Column(name = "name", length = 32)
    private String name;

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private List<Contact> contacts = new ArrayList<>();

    public Company addContact(Contact contact) {
        if (contacts.contains(contact)) {
            return null;
        }
        contacts.add(contact);
        contact.setCompany(this);
        return this;
    }

    public Company removeContact(Contact contact) {
        if (!contacts.contains(contact)) {
            return null;
        }
        contacts.remove(contact);
        contact.setCompany(null);
        return this;
    }

    public List<Contact> getContacts() {
        return new ArrayList<>(contacts);
    }
}
