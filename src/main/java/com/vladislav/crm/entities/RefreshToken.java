package com.vladislav.crm.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "refresh_tokens")
@EntityListeners(AuditingEntityListener.class)
public class RefreshToken {

    @Id
    @Type(type = "uuid-char")
    @Column(name = "token", updatable = false, nullable = false, length = 36)
    @EqualsAndHashCode.Include
    private UUID token = UUID.randomUUID();

    @NotNull
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @NotNull
    @EqualsAndHashCode.Include
    private LocalDateTime validUntil;

    public RefreshToken setUser(User newUser) {
        if (Objects.equals(user, newUser)) {
            return this;
        }

        if (user != null) {
            user.removeRefreshToken(this);
        }

        user = newUser;

        if (user != null) {
            user.addRefreshToken(this);
        }
        return this;
    }

    public RefreshToken setUserUnsafe(User user) {
        this.user = user;
        return this;
    }
}
