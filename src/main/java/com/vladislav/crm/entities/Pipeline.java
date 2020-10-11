package com.vladislav.crm.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "pipelines")
@AttributeOverride(name = "id", column = @Column(name = "pipeline_id", updatable = false, nullable = false))
public class Pipeline extends AbstractEntity {

    @NotBlank
    @Size(min = 1, max = 32)
    @Column(name = "name", length = 32)
    @ToString.Include
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Include
    private User user;

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "pipeline", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Status> statuses = new ArrayList<>();

    public Pipeline setUser(User newUser) {
        if (Objects.equals(user, newUser)) {
            return this;
        }

        final User oldUser = this.user;
        user = newUser;

        if (oldUser != null) {
            oldUser.removePipeline(this);
        }

        if (user != null) {
            user.addPipeline(this);
        }
        return this;
    }

    public Pipeline addStatus(Status status) {
        if (statuses.contains(status)) {
            return this;
        }
        statuses.add(status);
        status.setPipeline(this);
        return this;
    }

    public Pipeline removeStatus(Status status) {
        if (!statuses.contains(status)) {
            return this;
        }
        statuses.remove(status);
        status.setPipeline(null);
        return this;
    }

    public List<Status> getStatuses() {
        return new ArrayList<>(statuses);
    }

    @PreRemove
    private void preRemove() {
        user.removePipeline(this);
    }
}
