package com.vladislav.crm.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true, exclude = {"user", "statuses"})
@ToString(callSuper = true, exclude = {"user", "statuses"})
@Entity(name = "Pipeline")
@Table(name = "pipelines")
@AttributeOverride(name = "id", column = @Column(name = "pipeline_id", updatable = false, nullable = false))
public class Pipeline extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "pipeline", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Status> statuses = new ArrayList<>();

    public void setUser(User newUser) {
        if (Objects.equals(user, newUser))
            return;

        final User oldUser = this.user;
        user = newUser;

        if (oldUser != null)
            oldUser.removePipeline(this);

        if (user != null)
            user.addPipeline(this);
    }

    public void addStatus(Status status) {
        if (statuses.contains(status))
            return;
        statuses.add(status);
        status.setPipeline(this);
    }

    public void removeStatus(Status status) {
        if (!statuses.contains(status))
            return;
        statuses.remove(status);
        status.setPipeline(null);
    }
}
