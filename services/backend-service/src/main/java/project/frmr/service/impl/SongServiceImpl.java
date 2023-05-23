package project.frmr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.frmr.entity.Emotion;
import project.frmr.entity.Song;
import project.frmr.mapper.SongMapper;
import project.frmr.repository.SongRepository;
import project.frmr.service.SongService;
import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

   private SongMapper songMapper;

    @Autowired
    public void setSongMapper(SongMapper songMapper) {
        this.songMapper=songMapper;
    }

    @Autowired
    private SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository) {
       this.songRepository = songRepository;
    }

    @Override
    public Song save(Song entity) {
        return songRepository.save(entity);
    }

    @Override
    public List<Song> save(List<Song> entities) {
        return (List<Song>) songRepository.saveAll(entities);
    }

    @Override
    public void deleteById(String id) {
        songRepository.deleteById(id);
    }

    @Override
    public Optional<Song> findById(String id) {
        return songRepository.findById(id);

    }

    @Override
    public List<Song> findAll() {
        return (List<Song>) songRepository.findAll();
    }

    @Override
    public Page<Song> findAll(Pageable pageable) {
        Page<Song> entityPage = songRepository.findAll(pageable);
        List<Song> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public Song update(Song entity, String id) {
        Optional<Song> optional = findById(id);
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }

    @Override
    public List<String> findSongApiKeyByEmotionId(String EmotionId){
        List<String> apiKeys = songRepository.findSongApiKeyByEmotionId(EmotionId);



        return apiKeys;
    }

    @Override
    public String findSongTitleByApiKey(String ApiKey){
        String Title = songRepository.findSongTitleByApiKey(ApiKey);
        return Title;
    }


}
