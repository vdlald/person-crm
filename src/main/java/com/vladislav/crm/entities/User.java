package com.vladislav.crm.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true, exclude = {"password", "contacts", "pipelines", "leads"})
@EqualsAndHashCode(callSuper = true, exclude = {"info", "authorities", "contacts", "pipelines", "leads"})
@Entity(name = "User")
@Table(name = "users")
@AttributeOverride(name = "id", column = @Column(name = "user_id", updatable = false, nullable = false))
public class User extends AbstractEntity implements UserDetails {

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

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user")
    private List<Contact> contacts = new ArrayList<>();

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user")
    private List<Pipeline> pipelines = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private UserInfo info;

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Lead> leads = new ArrayList<>();

    public void setInfo(UserInfo newInfo) {
        if (Objects.equals(info, newInfo))
            return;

        final UserInfo oldInfo = this.info;
        info = newInfo;

        if (oldInfo != null)
            oldInfo.setUser(null);

        if (info != null)
            info.setUser(this);
    }

    public void addContact(Contact contact) {
        if (contacts.contains(contact))
            return;
        contacts.add(contact);
        contact.setUser(this);
    }

    public void removeContact(Contact contact) {
        if (!contacts.contains(contact))
            return;
        contacts.remove(contact);
        contact.setUser(null);
    }

    public void addPipeline(Pipeline pipeline) {
        if (pipelines.contains(pipeline))
            return;
        pipelines.add(pipeline);
        pipeline.setUser(this);
    }

    public void removePipeline(Pipeline pipeline) {
        if (!pipelines.contains(pipeline))
            return;
        pipelines.remove(pipeline);
        pipeline.setUser(null);
    }

    public void addLead(Lead lead) {
        if (leads.contains(lead))
            return;
        leads.add(lead);
        lead.setUser(this);
    }

    public void removeLead(Lead lead) {
        if (!leads.contains(lead))
            return;
        leads.remove(lead);
        lead.setUser(null);
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
