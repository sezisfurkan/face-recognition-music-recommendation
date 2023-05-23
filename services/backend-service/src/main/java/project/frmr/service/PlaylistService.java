package project.frmr.service;

import project.frmr.entity.Playlist;

import java.util.List;

public interface PlaylistService extends GenericService<Playlist,String>{
    List<Playlist> findByUser_Id(String id);
}
