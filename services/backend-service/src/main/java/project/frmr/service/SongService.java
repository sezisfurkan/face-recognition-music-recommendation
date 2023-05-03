package project.frmr.service;

import project.frmr.entity.Emotion;
import project.frmr.entity.Song;

public interface SongService extends GenericService<Song, String> {
    public String findSongApiKeyByEmotionId(String EmotionId);
}
