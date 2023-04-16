package project.frmr.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import project.frmr.models.ModifiableEntity;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "EMOTION")
@Data
public class Emotion extends ModifiableEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="emotion_id")
    private String id;

    @Column(nullable = false, length = 100)
    private String emotionName;

    @OneToMany(mappedBy = "emotion")
    private List<Song> songs;

}
