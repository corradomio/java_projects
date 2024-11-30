package org.hls.data;

import jakarta.persistence.*;
import lombok.Getter;


@Getter
@Entity()
@Table(name = AttributeMaster.TABLE_NAME)
public class AttributeMaster {
    public static final String TABLE_NAME="tb_attribute_master";

    @Id
    private Long id;

    @Column(name = "attribute_master_name")
    private String name;
    @Column(name = "attribute_desc")
    private String description;

    public AttributeMaster() {
        this.name = "name";
        this.description = "descrition";
    }

    public AttributeMaster(String name, String description) {
        this.name = "name";
        this.description = "descrition";
    }

}
