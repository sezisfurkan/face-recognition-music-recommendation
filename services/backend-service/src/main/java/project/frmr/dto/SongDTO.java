package project.frmr.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import project.frmr.entity.Emotion;

@Data
public class SongDTO {

    private String id;
    private String title;
    private String artist;
    private String genre;
    private Emotion emotion;
    private String apiKey;



}
