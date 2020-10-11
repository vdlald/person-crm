package com.vladislav.crm.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true, exclude = "user")
@Entity
@Table(name = "usersinfo")
@AttributeOverride(name = "id", column = @Column(name = "userinfo_id", updatable = false, nullable = false))
public class UserInfo extends AbstractEntity {

    @Size(max = 64)
    @Column(name = "email", unique = true, length = 64)
    @Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    private String email;

    @Size(max = 32)
    @Column(name = "firstname", length = 32)
    private String firstname;

    @Size(max = 32)
    @Column(name = "lastname", length = 32)
    private String lastname;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public UserInfo setUser(User newUser) {
        if (Objects.equals(user, newUser)) {
            return this;
        }

        final User oldInfo = this.user;
        user = newUser;

        if (oldInfo != null) {
            oldInfo.setInfo(null);
        }

        if (user != null) {
            user.setInfo(this);
        }
        return this;
    }

    @PreRemove
    private void preRemove() {
        if (user != null) {
            throw new EntityExistsException("Can' t remove \"usersinfo\" entity as long as \"user\" entity exists");
        }
    }
}
