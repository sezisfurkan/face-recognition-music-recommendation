package project.frmr.service.impl;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.frmr.entity.Emotion;
import project.frmr.mapper.EmotionMapper;
import project.frmr.repository.EmotionRepository;
import project.frmr.service.EmotionService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class EmotionServiceImpl implements EmotionService {

    private EmotionMapper emotionMapper;

    @Autowired
    public void setEmotionMapper(EmotionMapper emotionMapper) {
        this.emotionMapper=emotionMapper;
    }

    @Autowired
    private EmotionRepository emotionRepository;

    public EmotionServiceImpl(EmotionRepository emotionRepository) {
        this.emotionRepository = emotionRepository;
    }

    @Override
    public Emotion save(Emotion entity) {
        return emotionRepository.save(entity);
    }

    @Override
    public List<Emotion> save(List<Emotion> entities) {
        return (List<Emotion>) emotionRepository.saveAll(entities);    }

    @Override
    public void deleteById(String id) {
        emotionRepository.deleteById(id);
    }

    @Override
    public Optional<Emotion> findById(String id) {
        return emotionRepository.findById(id);
    }

    @Override
    public List<Emotion> findAll() {
        return (List<Emotion>) emotionRepository.findAll();
    }

    @Override
    public Page<Emotion> findAll(Pageable pageable) {
        Page<Emotion> entityPage = emotionRepository.findAll(pageable);
        List<Emotion> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public Emotion update(Emotion entity, String id) {
        Optional<Emotion> optional = findById(id);
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }

    @Override
    public String findEmotionIdByEmotionName(String EmotionName){
        return emotionRepository.findEmotionIdByEmotionName(EmotionName);
    }
}
