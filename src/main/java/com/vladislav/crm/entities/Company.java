package com.vladislav.crm.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "companies")
@AttributeOverride(name = "id", column = @Column(name = "company_id", updatable = false, nullable = false))
public class Company extends AbstractEntity {

    @NotBlank
    @Size(min = 1, max = 32)
    @Column(name = "name", length = 32, nullable = false)
    @ToString.Include
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Include
    private User user;

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

    public Company setUser(User newUser) {
        if (Objects.equals(user, newUser)) {
            return this;
        }

        if (user != null) {
            user.removeCompany(this);
        }

        user = newUser;

        if (user != null) {
            user.addCompany(this);
        }
        return this;
    }

    public Company setUserUnsafe(User user) {
        this.user = user;
        return this;
    }
}
