package com.codedream.intf.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Accessors(chain = true)
@Data
@Entity
@Table(name = "admin_role_menu")
@ToString
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class AdminRoleMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Role id.
     */
    private int rid;

    /**
     * Menu id.
     */
    private int mid;

    public int getRid() {
        return rid;
    }

    public int getMid() {
        return mid;
    }
}
