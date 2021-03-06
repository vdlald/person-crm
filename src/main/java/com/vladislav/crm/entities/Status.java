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
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "statuses")
@AttributeOverride(name = "id", column = @Column(name = "status_id", updatable = false, nullable = false))
public class Status extends AbstractEntity {

    @NotBlank
    @Size(min = 1, max = 32)
    @Column(name = "name", length = 32)
    @ToString.Include
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pipeline_id")
    @ToString.Include
    private Pipeline pipeline;

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    private List<Lead> leads = new ArrayList<>();

    public Status setPipelineSafe(Pipeline newPipeline) {
        if (Objects.equals(pipeline, newPipeline)) {
            return this;
        }

        if (pipeline != null) {
            pipeline.removeStatus(this);
        }

        pipeline = newPipeline;

        if (pipeline != null) {
            pipeline.addStatus(this);
        }
        return this;
    }

    public Status addLead(Lead lead) {
        if (leads.contains(lead)) {
            return this;
        }
        leads.add(lead);
        lead.setStatusSafe(this);
        return this;
    }

    public Status removeLead(Lead lead) {
        if (!leads.contains(lead)) {
            return this;
        }
        leads.remove(lead);
        lead.setStatusSafe(null);
        return this;
    }

    public List<Lead> getLeads() {
        return new ArrayList<>(leads);
    }
}
