package com.vladislav.crm.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Entity(name = "Contact")
@Table(name = "contacts")
@AttributeOverride(name = "id", column = @Column(name = "contact_id", updatable = false, nullable = false))
public class Contact extends AbstractEntity {

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

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
}
