package project.frmr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import project.frmr.models.ModifiableEntity;

/*
import javax.persistence.*;
*/

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "SYS_USER")

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Where(clause = "is_deleted = false")
public class User extends ModifiableEntity  {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

  /*  @Column(nullable = true)
    private byte[] picture;
*/
    @Column(nullable = true, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String fname;

    @Column(nullable = true, length = 100)
    private String sname;

    @Column(nullable = false, length = 100)
    private String username;

    @JsonIgnore
    @Column(nullable = false, length = 1000)
    private String password;

    @Column(nullable = false, length = 100)
    private String email;

}
