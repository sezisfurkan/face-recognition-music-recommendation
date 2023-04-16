package project.frmr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.frmr.entity.Emotion;

public interface EmotionRepository extends JpaRepository<Emotion, String> {
}
