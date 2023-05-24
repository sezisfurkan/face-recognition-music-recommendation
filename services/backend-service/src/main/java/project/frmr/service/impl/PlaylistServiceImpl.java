package project.frmr.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.frmr.entity.Emotion;
import project.frmr.entity.Playlist;
import project.frmr.mapper.PlaylistMapper;
import project.frmr.repository.EmotionRepository;
import project.frmr.repository.PlaylistRepository;
import project.frmr.service.PlaylistService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

    private PlaylistMapper playlistMapper;

    @Autowired
    public void setPlaylistMapper(PlaylistMapper playlistMapper) {
        this.playlistMapper = playlistMapper;
    }

    @Autowired
    private PlaylistRepository playlistRepository;

    public PlaylistServiceImpl(PlaylistRepository playlistRepository) {

        this.playlistRepository = playlistRepository;
    }

    @Override
    public Playlist save(Playlist entity) {
        return playlistRepository.save(entity);
    }

    @Override
    public List<Playlist> save(List<Playlist> entities) {
        return (List<Playlist>) playlistRepository.saveAll(entities);
    }

    @Override
    public void deleteById(String id) {
        playlistRepository.deleteById(id);
    }

    @Override
    public Optional<Playlist> findById(String id) {
        return playlistRepository.findById(id);
    }

    @Override
    public List<Playlist> findAll() {
        return (List<Playlist>) playlistRepository.findAll();
    }

    @Override
    public Page<Playlist> findAll(Pageable pageable) {
        Page<Playlist> entityPage = playlistRepository.findAll(pageable);
        List<Playlist> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public Playlist update(Playlist entity, String id) {
        Optional<Playlist> optional = findById(id);
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }

   /* @Override
    public List<Playlist> findByUser_Id(String id) {
        return playlistRepository.findByUser_Id(id);
    }*/

    @Override
    public List<String> findTitleAndApiKeyByUserID(String UserId) {
        List<String> list = playlistRepository.findTitleAndApiKeyByUserId(UserId);

        return list;
    }
}
