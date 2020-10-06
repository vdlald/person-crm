package com.vladislav.crm.entities;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true, exclude = {"contacts", "user"})
@EqualsAndHashCode(callSuper = true, exclude = {"contacts", "user"})
@Entity
@Table(name = "leads")
@AttributeOverride(name = "id", column = @Column(name = "lead_id", updatable = false, nullable = false))
@EntityListeners(AuditingEntityListener.class)
public class Lead extends AbstractEntityWithTime {

    @NotBlank
    @Size(min = 1, max = 32)
    @Column(name = "name", length = 32)
    private String name;

    @DecimalMin("0.0")
    @Column(name = "sale")
    private BigDecimal sale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    private Status status;

    @Setter(AccessLevel.PRIVATE)
    @ManyToMany(mappedBy = "leads", fetch = FetchType.LAZY)
    private Set<Contact> contacts = new HashSet<>();

    public Lead setUser(User newUser) {
        if (Objects.equals(user, newUser)) {
            return null;
        }

        final User oldUser = this.user;
        user = newUser;

        if (oldUser != null) {
            oldUser.removeLead(this);
        }

        if (user != null) {
            user.addLead(this);
        }
        return this;
    }

    public Lead setStatus(Status newStatus) {
        if (Objects.equals(status, newStatus)) {
            return this;
        }

        final Status oldStatus = this.status;
        status = newStatus;

        if (oldStatus != null) {
            oldStatus.removeLead(this);
        }

        if (newStatus != null) {
            status.addLead(this);
        }
        return this;
    }

    public Lead addContact(Contact contact) {
        if (contacts.contains(contact)) {
            return this;
        }
        contacts.add(contact);
        contact.addLead(this);
        return this;
    }

    public Lead removeContact(Contact contact) {
        if (!contacts.contains(contact)) {
            return this;
        }
        contacts.remove(contact);
        contact.removeLead(this);
        return this;
    }

    public List<Contact> getContacts() {
        return new ArrayList<>(contacts);
    }

    @PreRemove
    private void preRemove() {
        user.removeLead(this);
        status.removeLead(this);
        contacts.forEach(contact -> contact.removeLead(this));
    }
}
