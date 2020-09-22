package com.vladislav.crm.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true, exclude = {"contacts", "status", "user"})
@EqualsAndHashCode(callSuper = true, exclude = {"contacts", "status", "user"})
@Entity(name = "Lead")
@Table(name = "leads")
@AttributeOverride(name = "id", column = @Column(name = "lead_id", updatable = false, nullable = false))
public class Lead extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "sale")
    private BigDecimal sale;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToMany(mappedBy = "leads")
    private List<Contact> contacts = new ArrayList<>();

    public void setUser(User newUser) {
        if (Objects.equals(user, newUser))
            return;

        final User oldUser = this.user;
        user = newUser;

        if (oldUser != null)
            oldUser.removeLead(this);

        if (user != null)
            user.addLead(this);
    }

    public void setStatus(Status newStatus) {
        if (Objects.equals(status, newStatus))
            return;

        final Status oldStatus = this.status;
        status = newStatus;

        if (oldStatus != null)
            oldStatus.removeLead(this);

        if (newStatus != null)
            status.addLead(this);
    }

    public void addContact(Contact contact) {
        if (contacts.contains(contact))
            return;
        contacts.add(contact);
        contact.addLead(this);
    }

    public void removeContact(Contact contact) {
        if (!contacts.contains(contact))
            return;
        contacts.remove(contact);
        contact.removeLead(this);
    }

    public List<Contact> getContacts() {
        return new ArrayList<>(contacts);
    }
}
