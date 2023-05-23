package project.frmr.controller;

import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import project.frmr.dto.EmotionDTO;
import project.frmr.dto.PlaylistDTO;
import project.frmr.entity.Playlist;

import java.util.List;

public interface PlaylistController {

    @ApiOperation("Add new data")
    public PlaylistDTO save(@RequestBody PlaylistDTO playlistDTO);

    @ApiOperation("Find by Id")
    public PlaylistDTO findById(@PathVariable("id") String id);

    @ApiOperation("Delete based on primary key")
    public void delete(@PathVariable("id") String id);

    @ApiOperation("Find all data")
    public List<PlaylistDTO> list();

    @ApiOperation("Pagination request")
    public Page<PlaylistDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    public PlaylistDTO update(@RequestBody PlaylistDTO dto, @PathVariable("id") String id);

    /*@ApiOperation("Find playlist by user id")
    public List<Playlist> findByUser_Id(@RequestParam String id);*/
    @ApiOperation("Find Title by Song Apikey")
    public String findTitleByApiKey(String ApiKey);

}
