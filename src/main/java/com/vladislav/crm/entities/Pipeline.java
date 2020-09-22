package com.vladislav.crm.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true, exclude = "user")
@ToString(exclude = "user")
@Entity(name = "Pipeline")
@Table(name = "pipelines")
@AttributeOverride(name = "id", column = @Column(name = "pipeline_id", updatable = false, nullable = false))
public class Pipeline extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

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

}
