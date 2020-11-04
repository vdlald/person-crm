package com.vladislav.crm.entities;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "leads")
@AttributeOverride(name = "id", column = @Column(name = "lead_id", updatable = false, nullable = false))
@EntityListeners(AuditingEntityListener.class)
public class Lead extends AbstractEntityWithTime {

    @NotBlank
    @Size(min = 1, max = 32)
    @Column(name = "name", length = 32)
    @ToString.Include
    private String name;

    @DecimalMin("0.0")
    @Column(name = "sale")
    @ToString.Include
    private BigDecimal sale;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Include
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    @ToString.Include
    private Status status;

    @Setter(AccessLevel.PRIVATE)
    @ManyToMany(mappedBy = "leads", fetch = FetchType.LAZY)
    private Set<Contact> contacts = new HashSet<>();

    @Value
    @Builder(setterPrefix = "set")
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class LeadInfo {

        @EqualsAndHashCode.Include
        Long id;
        String name;
        BigDecimal sale;

    }

    public Lead setUser(User newUser) {
        if (Objects.equals(user, newUser)) {
            return this;
        }

        if (user != null) {
            user.removeLead(this);
        }

        user = newUser;

        if (user != null) {
            user.addLead(this);
        }
        return this;
    }

    public Lead setUserUnsafe(User newUser) {
        user = newUser;
        return this;
    }

    public Lead setStatus(Status newStatus) {
        if (Objects.equals(status, newStatus)) {
            return this;
        }

        if (status != null) {
            status.removeLead(this);
        }

        status = newStatus;

        if (newStatus != null) {
            status.addLead(this);
        }
        return this;
    }

    public Lead setStatusUnsafe(Status newStatus) {
        status = newStatus;
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
}
