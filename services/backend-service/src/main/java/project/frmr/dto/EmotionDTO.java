package project.frmr.dto;

import lombok.Data;
import project.frmr.entity.Song;
import java.util.List;

@Data
public class EmotionDTO {

    private String id;
    private String emotionName;
    private List<Song> songs;
}
