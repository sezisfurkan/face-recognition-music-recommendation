package project.frmr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.frmr.entity.Song;

public interface SongRepository extends JpaRepository<Song, String> {
}
