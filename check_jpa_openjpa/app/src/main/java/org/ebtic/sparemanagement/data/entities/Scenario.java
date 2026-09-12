package org.ebtic.sparemanagement.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "scenarios")
public class Scenario {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "scenario_name")
    private String name;
}
