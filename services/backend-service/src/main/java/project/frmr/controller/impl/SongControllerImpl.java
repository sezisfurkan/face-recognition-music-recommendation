package project.frmr.controller.impl;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.frmr.controller.SongController;
import project.frmr.dto.SongDTO;
import project.frmr.entity.Song;
import project.frmr.mapper.SongMapper;
import project.frmr.service.SongService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/song")
@CrossOrigin
public class SongControllerImpl implements SongController {

    @Autowired
    SongService songService;
    @Autowired
    SongMapper songMapper;

    @Override
    @PostMapping("/song")
    @ResponseStatus(HttpStatus.CREATED)
    public SongDTO save(SongDTO songDTO) {
        Song song = songMapper.asEntity(songDTO);
        return songMapper.asDTO(songService.save(song));
    }

    @Override
    public ResponseEntity<?> login(HttpSession session) {
        return null;
    }

    @Override
    @GetMapping("/song/{id}")
    public SongDTO findById(@PathVariable("id") String id) {
        Song song = songService.findById(id).orElse(null);
        return songMapper.asDTO(song);
    }

    @Override
    @DeleteMapping("/song/{id}")
    public void delete(@PathVariable("id") String id) {
        songService.deleteById(id);
    }

    @Override
    @GetMapping("/song")
    public List<SongDTO> list() {
        return songMapper.asDTOList(songService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<SongDTO> pageQuery(Pageable pageable) {
        Page<Song> songPage = songService.findAll(pageable);
        List<SongDTO> dtoList = songPage
                .stream()
                .map(songMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, songPage.getTotalElements());
    }

    @Override
    @PutMapping("/song/{id}")
    public SongDTO update(SongDTO songDTO, @PathVariable("id") String id) {
       Song song = songMapper.asEntity(songDTO);
        return songMapper.asDTO(songService.update(song, id));
    }

    @Override
    @GetMapping("/emotion/{id}")
    public String getSongApiKeyByEmotionId(@PathVariable("id") String emotionId){
        return songService.findSongApiKeyByEmotionId(emotionId);
    }
}
