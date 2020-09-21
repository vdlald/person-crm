package com.vladislav.crm.entities;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true, exclude = "password")
@EqualsAndHashCode(callSuper = true, exclude = "info")
@Entity(name = "User")
@Table(name = "users")
@AttributeOverride(name = "id", column = @Column(name = "user_id", updatable = false, nullable = false))
public class User extends AbstractEntity {

    @Setter(AccessLevel.PRIVATE)
    @Pattern(regexp = "^(?=.{3,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$")
    @Column(name = "username", updatable = false, nullable = false, unique = true, length = 32)
    private String username;

    @NotEmpty
    @Size(min = 8, max = 64)
    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Authority> authorities = new ArrayList<>() {{
        add(Authority.ROLE_USER);
    }};

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user")
    private UserInfo info;

    @AllArgsConstructor
    public enum Authority implements GrantedAuthority {
        ROLE_USER("role_user");

        @Getter
        private final String authority;

    }
}
