package com.vladislav.crm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true, exclude = {"password", "contacts", "pipelines", "leads"})
@EqualsAndHashCode(callSuper = true, exclude = {"info", "authorities", "contacts", "pipelines", "leads"})
@Entity
@Table(name = "users")
@AttributeOverride(name = "id", column = @Column(name = "user_id", updatable = false, nullable = false))
@EntityListeners(AuditingEntityListener.class)
public class User extends AbstractEntity implements UserDetails {

    @Pattern(regexp = "^(?=.{3,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$")
    @Column(name = "username", updatable = false, nullable = false, unique = true, length = 32)
    private String username;

    @NotEmpty
    @Size(min = 8, max = 64)
    @Column(name = "password", nullable = false, length = 64)
    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Authority> authorities = new ArrayList<>() {{
        add(Authority.ROLE_USER);
    }};

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user")
    private List<Contact> contacts = new ArrayList<>();

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user")
    private List<Pipeline> pipelines = new ArrayList<>();

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user")
    private List<Status> statuses = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private UserInfo info;

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Lead> leads = new ArrayList<>();

    @Column(name = "createdAt", nullable = false, updatable = false)
    @Setter(AccessLevel.PRIVATE)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updatedAt")
    @Setter(AccessLevel.PRIVATE)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public User setInfo(UserInfo newInfo) {
        if (Objects.equals(info, newInfo)) {
            return this;
        }

        final UserInfo oldInfo = this.info;
        info = newInfo;

        if (oldInfo != null) {
            oldInfo.setUser(null);
        }

        if (info != null) {
            info.setUser(this);
        }
        return this;
    }

    public User addContact(Contact contact) {
        if (contacts.contains(contact)) {
            return this;
        }
        contacts.add(contact);
        contact.setUser(this);
        return this;
    }

    public User removeContact(Contact contact) {
        if (!contacts.contains(contact)) {
            return this;
        }
        contacts.remove(contact);
        contact.setUser(null);
        return this;
    }

    public User addPipeline(Pipeline pipeline) {
        if (pipelines.contains(pipeline)) {
            return this;
        }
        pipelines.add(pipeline);
        pipeline.setUser(this);
        return this;
    }

    public User removePipeline(Pipeline pipeline) {
        if (!pipelines.contains(pipeline)) {
            return this;
        }
        pipelines.remove(pipeline);
        pipeline.setUser(null);
        return this;
    }

    public User addStatus(Status status) {
        if (statuses.contains(status)) {
            return this;
        }
        statuses.add(status);
        status.setUser(this);
        return this;
    }

    public User removeStatus(Status status) {
        if (!statuses.contains(status)) {
            return this;
        }
        statuses.remove(status);
        status.setUser(null);
        return this;
    }

    public User addLead(Lead lead) {
        if (leads.contains(lead)) {
            return this;
        }
        leads.add(lead);
        lead.setUser(this);
        return this;
    }

    public User removeLead(Lead lead) {
        if (!leads.contains(lead)) {
            return this;
        }
        leads.remove(lead);
        lead.setUser(null);
        return this;
    }

    public List<Contact> getContacts() {
        return new ArrayList<>(contacts);
    }

    public List<Lead> getLeads() {
        return new ArrayList<>(leads);
    }

    public List<Pipeline> getPipelines() {
        return new ArrayList<>(pipelines);
    }

    public List<Status> getStatuses() {
        return new ArrayList<>(statuses);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @AllArgsConstructor
    public enum Authority implements GrantedAuthority {
        ROLE_USER("role_user");

        @Getter
        private final String authority;

    }
}
