package com.vladislav.crm.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true, exclude = {"contacts", "status", "user"})
@EqualsAndHashCode(callSuper = true, exclude = {"contacts", "status", "user"})
@Entity(name = "Lead")
@Table(name = "leads")
@AttributeOverride(name = "id", column = @Column(name = "lead_id", updatable = false, nullable = false))
public class Lead extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "sale")
    private BigDecimal sale;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToMany(mappedBy = "leads")
    private List<Contact> contacts;
}
