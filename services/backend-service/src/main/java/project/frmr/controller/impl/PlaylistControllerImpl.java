package project.frmr.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import project.frmr.controller.PlaylistController;
import project.frmr.dto.EmotionDTO;
import project.frmr.dto.PlaylistDTO;
import project.frmr.entity.Emotion;
import project.frmr.entity.Playlist;
import project.frmr.mapper.PlaylistMapper;
import project.frmr.service.PlaylistService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/playlist")
@CrossOrigin
public class PlaylistControllerImpl implements PlaylistController {


    @Autowired
    PlaylistService playlistService;
    @Autowired
    PlaylistMapper playlistMapper;

    @Override
    @PostMapping("/playlist")
    @ResponseStatus(HttpStatus.CREATED)
    public PlaylistDTO save(PlaylistDTO playlistDTO) {
        Playlist playlist = playlistMapper.asEntity(playlistDTO);
        return playlistMapper.asDTO(playlistService.save(playlist));
    }

    @Override
    @GetMapping("/playlist/{id}")
    public PlaylistDTO findById(String id) {
        Playlist playlist = playlistService.findById(id).orElse(null);
        return playlistMapper.asDTO(playlist);
    }

    @Override
    @DeleteMapping("/playlist/{id}")
    public void delete(@PathVariable("id") String id) {
        playlistService.deleteById(id);
    }

    @Override
    @GetMapping("/playlist")
    public List<PlaylistDTO> list() {
        return playlistMapper.asDTOList(playlistService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<PlaylistDTO> pageQuery(Pageable pageable) {
        Page<Playlist> playlistPage = playlistService.findAll(pageable);
        List<PlaylistDTO> dtoList = playlistPage
                .stream()
                .map(playlistMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, playlistPage.getTotalElements());
    }

    @Override
    @PutMapping("/playlist/{id}")
    public PlaylistDTO update(PlaylistDTO dto, String id) {
        Playlist playlist = playlistMapper.asEntity(dto);
        return playlistMapper.asDTO(playlistService.update(playlist, id));
    }

    @Override
    @GetMapping("/getplaylistbyuserid")
    public List<Playlist> findByUser_Id(@RequestParam String id) {
        return playlistService.findByUser_Id(id);
    }
}
