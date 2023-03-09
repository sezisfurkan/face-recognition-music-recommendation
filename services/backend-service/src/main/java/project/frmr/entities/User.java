package project.frmr.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue
    Long Id;

    String name;

    String surName;

    String userName;

    String password;

    String email;




}




/*@Entity
@Table(name ="user")
@Data
public class User {
    @Id
    @GeneratedValue
    Long id;

    @NotNull
    @Size(min=4, max=255)
    @UniqueUsername
    @JsonView(Views.Base.class)
    String userName;

    @NotNull
    @Size(min=4, max=255)
    @JsonView(Views.Base.class)
    String displayName;

    @NotNull
//    @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")
    @Size(min=8, max=255)
    @JsonView(Views.Sensitive.class)
    String password;

    @JsonView(Views.Base.class)
    private String image;

}*/
