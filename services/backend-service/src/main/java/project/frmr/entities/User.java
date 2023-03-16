package project.frmr.entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;

    @Column(name = "name")
    String name;
    @Column(name = "surName")
    String surName;
    @Column(name = "userName")
    String userName;
    @Column(name = "password")
    String password;
    @Column(name = "email")
    String email;

}




