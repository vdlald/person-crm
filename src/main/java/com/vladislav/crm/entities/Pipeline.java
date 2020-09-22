package com.vladislav.crm.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true, exclude = "user")
@Entity(name = "Pipeline")
@Table(name = "pipelines")
@AttributeOverride(name = "id", column = @Column(name = "pipeline_id", updatable = false, nullable = false))
public class Pipeline extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    
}
