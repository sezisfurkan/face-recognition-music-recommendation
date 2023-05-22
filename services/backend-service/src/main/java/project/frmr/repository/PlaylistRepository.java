package project.frmr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.frmr.entity.Playlist;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist,String> {
    List<Playlist> findByUser_Id(String id);
}
