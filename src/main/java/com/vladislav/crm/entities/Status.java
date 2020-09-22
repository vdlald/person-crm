package com.vladislav.crm.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity(name = "Status")
@Table(name = "statuses")
@AttributeOverride(name = "id", column = @Column(name = "status_id", updatable = false, nullable = false))
public class Status extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pipeline pipeline;

    public void setPipeline(Pipeline newPipeline) {
        if (Objects.equals(pipeline, newPipeline))
            return;

        final Pipeline oldPipeline = this.pipeline;
        pipeline = newPipeline;

        if (oldPipeline != null)
            oldPipeline.removeStatus(this);

        if (pipeline != null)
            pipeline.addStatus(this);
    }
}
