package project.frmr.service;

import project.frmr.entity.Emotion;
import project.frmr.entity.Song;

import java.util.List;

public interface SongService extends GenericService<Song, String> {
    public List<String> findSongApiKeyByEmotionId(String EmotionId);
 /*   public List<String> findSongTitleByEmotionId(String EmotionId);*/
    public String findSongTitleByApiKey(String ApiKey);
}
