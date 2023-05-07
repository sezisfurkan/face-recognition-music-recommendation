package project.frmr.dto;

import jakarta.persistence.*;
import lombok.Data;
import project.frmr.entity.Song;
import project.frmr.entity.User;

import java.util.HashSet;
import java.util.Set;

@Data
public class PlaylistDTO {

    private String id;
    private User user;
    private Set<Song> songs = new HashSet<>();

}
