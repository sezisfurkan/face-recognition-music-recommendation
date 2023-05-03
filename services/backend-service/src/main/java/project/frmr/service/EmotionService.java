package project.frmr.service;

import project.frmr.entity.Emotion;

public interface EmotionService extends GenericService<Emotion, String> {

    public String findEmotionIdByEmotionName(String EmotionName);
}
