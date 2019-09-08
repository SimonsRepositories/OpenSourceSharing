package com.slh.opensourcesharing.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "auth_role")
public class Role
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "auth_role_id")
    private int role_id;

    @Column(name = "role_name")
    private String role;

    @Column(name = "role_desc")
    private String desc;
}
