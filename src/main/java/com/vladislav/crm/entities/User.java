package com.vladislav.crm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "users")
@AttributeOverride(name = "id", column = @Column(name = "user_id", updatable = false, nullable = false))
@EntityListeners(AuditingEntityListener.class)
public class User extends AbstractEntityWithTime implements UserDetails {

    @ToString.Include
    @Pattern(regexp = "^(?=.{3,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$")
    @Column(name = "username", updatable = false, nullable = false, unique = true, length = 32)
    private String username;

    @NotBlank
    @Size(min = 60, max = 60)
    @Column(name = "password", nullable = false, length = 60)
    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_authorities", joinColumns = @JoinColumn(name = "user_id"))
    @ToString.Include
    private List<Authority> authorities = new ArrayList<>();

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Contact> contacts = new ArrayList<>();

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user")
    private List<Pipeline> pipelines = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private UserInfo info;

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnoreProperties("user")
    private List<Lead> leads = new ArrayList<>();

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnoreProperties("user")
    private List<Company> companies = new ArrayList<>();

    public User setInfo(UserInfo newInfo) {
        if (Objects.equals(info, newInfo)) {
            return this;
        }

        if (info != null) {
            final UserInfo oldInfo = info;
            info = null;
            oldInfo.setUser(null);
        }

        info = newInfo;

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

    public User addCompany(Company company) {
        if (companies.contains(company)) {
            return this;
        }
        companies.add(company);
        company.setUser(this);
        return this;
    }

    public User removeCompany(Company company) {
        if (!companies.contains(company)) {
            return this;
        }
        companies.remove(company);
        company.setUser(null);
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

    public List<Company> getCompanies() {
        return new ArrayList<>(companies);
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
        READ_ALL("read_all"),
        ALL("all");

        @Getter
        private final String authority;

    }
}
