package project.frmr.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import project.frmr.models.ModifiableEntity;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "SONG")
@Data
public class Song extends ModifiableEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 100)
    private String artist;

    @Column(nullable = false, length = 100)
    private String genre;

    @Column(nullable = false, length = 100)
    private String emotionId;

    @Column(nullable = false, length = 100)
    private String apiKey;
}
