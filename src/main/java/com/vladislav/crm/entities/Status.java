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
@ToString(callSuper = true, exclude = {"leads"})
@EqualsAndHashCode(callSuper = true, exclude = {"leads"})
@Entity(name = "Status")
@Table(name = "statuses")
@AttributeOverride(name = "id", column = @Column(name = "status_id", updatable = false, nullable = false))
public class Status extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private Pipeline pipeline;

    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Lead> leads = new ArrayList<>();

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

    public void addLead(Lead lead) {
        if (leads.contains(lead))
            return;
        leads.add(lead);
        lead.setStatus(this);
    }

    public void removeLead(Lead lead) {
        if (!leads.contains(lead))
            return;
        leads.remove(lead);
        lead.setStatus(null);
    }

    public List<Lead> getLeads() {
        return new ArrayList<>(leads);
    }
}