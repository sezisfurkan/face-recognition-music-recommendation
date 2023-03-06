

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name= "message")
@Data
public class Message {


    @Id @GeneratedValue
    Long id;

    String content;
    String username;
    Date timestamp;

 /*   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    User user;*/

/*    String title;
    @Lob
    @Column(columnDefinition ="text")
    String text;*/



}