package project.frmr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.frmr.entity.Emotion;

public interface EmotionRepository extends JpaRepository<Emotion, String> {
    @Query(value = "select emotion_id\n" +
            "from emotion where emotion_name =:EmotionName", nativeQuery = true)
    String findEmotionIdByEmotionName(String EmotionName);
}
