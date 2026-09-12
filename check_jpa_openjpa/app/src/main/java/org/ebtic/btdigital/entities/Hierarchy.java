package org.ebtic.btdigital.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tb_attribute_master")
public class Hierarchy {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "attribute_master_name")
    private String name;

    @Column(name = "attribute_desc")
    private String description;

}
