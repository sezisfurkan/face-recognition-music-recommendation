package project.frmr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.frmr.entity.Song;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, String> {
    @Query(value = "select api_key\n" +
            "from song where emotion_id =:EmotionId", nativeQuery = true)
    List<String> findSongApiKeyByEmotionId (String EmotionId);
   /* List<String> findSongTitleByEmotionId (String EmotionId);*/
}
