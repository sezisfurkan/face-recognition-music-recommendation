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
@Table(name = "playlists")
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Playlists extends ModifiableEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="playlist_id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "song_id")
    private Song song;





}
