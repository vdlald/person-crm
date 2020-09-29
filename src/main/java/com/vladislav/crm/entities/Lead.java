package com.vladislav.crm.entities;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
public class Lead extends AbstractEntity {

    @Size(min = 1, max = 32)
    @Column(name = "name", length = 32)
    private String name;

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
    private List<Contact> contacts = new ArrayList<>();

    @Column(name = "createdAt", nullable = false, updatable = false)
    @Setter(AccessLevel.PRIVATE)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updatedAt")
    @Setter(AccessLevel.PRIVATE)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public void setUser(User newUser) {
        if (Objects.equals(user, newUser)) {
            return;
        }

        final User oldUser = this.user;
        user = newUser;

        if (oldUser != null) {
            oldUser.removeLead(this);
        }

        if (user != null) {
            user.addLead(this);
        }
    }

    public void setStatus(Status newStatus) {
        if (Objects.equals(status, newStatus)) {
            return;
        }

        final Status oldStatus = this.status;
        status = newStatus;

        if (oldStatus != null) {
            oldStatus.removeLead(this);
        }

        if (newStatus != null) {
            status.addLead(this);
        }
    }

    public void addContact(Contact contact) {
        if (contacts.contains(contact)) {
            return;
        }
        contacts.add(contact);
        contact.addLead(this);
    }

    public void removeContact(Contact contact) {
        if (!contacts.contains(contact)) {
            return;
        }
        contacts.remove(contact);
        contact.removeLead(this);
    }

    public List<Contact> getContacts() {
        return new ArrayList<>(contacts);
    }
}
