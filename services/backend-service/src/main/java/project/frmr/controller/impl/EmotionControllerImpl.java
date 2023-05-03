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
import project.frmr.controller.EmotionController;
import project.frmr.dto.EmotionDTO;
import project.frmr.entity.Emotion;
import project.frmr.mapper.EmotionMapper;
import project.frmr.service.EmotionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/emotion")
@CrossOrigin
public class EmotionControllerImpl implements EmotionController {

    @Autowired
    EmotionService emotionService;
    @Autowired
    EmotionMapper emotionMapper;

    @Override
    @PostMapping("/emotion")
    @ResponseStatus(HttpStatus.CREATED)
    public EmotionDTO save(@RequestBody EmotionDTO emotionDTO) {
        Emotion emotion = emotionMapper.asEntity(emotionDTO);
        return emotionMapper.asDTO(emotionService.save(emotion));
    }

    @Override
    public ResponseEntity<?> login(HttpSession session) {
        return null;
    }

    @Override
    @GetMapping("/emotion/{id}")
    public EmotionDTO findById(@PathVariable("id") String id) {
        Emotion emotion = emotionService.findById(id).orElse(null);
        return emotionMapper.asDTO(emotion);
    }

    @Override
    @DeleteMapping("/emotion/{id}")
    public void delete(@PathVariable("id") String id) {
        emotionService.deleteById(id);
    }

    @Override
    @GetMapping("/emotion")
    public List<EmotionDTO> list() {
        return emotionMapper.asDTOList(emotionService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<EmotionDTO> pageQuery(Pageable pageable) {
        Page<Emotion> emotionPage = emotionService.findAll(pageable);
        List<EmotionDTO> dtoList = emotionPage
                .stream()
                .map(emotionMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, emotionPage.getTotalElements());

    }

    @Override
    @PutMapping("/emotion/{id}")
    public EmotionDTO update(@RequestBody EmotionDTO emotionDTO, @PathVariable("id") String id) {
        Emotion emotion = emotionMapper.asEntity(emotionDTO);
        return emotionMapper.asDTO(emotionService.update(emotion, id));    }

    @Override
    @GetMapping("/emotion/{name}")
    public String getEmotionIdByEmotionName(@PathVariable("name") String emotionName){
        return emotionService.findEmotionIdByEmotionName(emotionName);
    }
}
