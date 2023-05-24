package project.frmr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.frmr.entity.Playlist;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist,String> {
    /*List<Playlist> findByUser_Id(String id);*/

    @Query(value = "select api_key,title \n" +
            "from playlist where user_id =:UserId", nativeQuery = true)
    List<String> findTitleAndApiKeyByUserId(String UserId);
}
