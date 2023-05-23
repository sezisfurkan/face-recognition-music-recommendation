package project.frmr.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.frmr.dto.EmotionDTO;
import project.frmr.dto.PlaylistDTO;
import project.frmr.dto.SongDTO;
import project.frmr.dto.UserDTO;
import project.frmr.entity.Emotion;
import project.frmr.entity.Playlist;
import project.frmr.entity.Song;
import project.frmr.entity.User;
import project.frmr.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;


@Configuration
public class AppConfig {

    @Bean
    public UserMapper userMapper() {
        return new UserMapper() {
            @Override
            public User asEntity(UserDTO dto) {
                User user = new User();
                user.setId(dto.getId());
                user.setPassword(dto.getPassword());
                user.setName(dto.getName());
                user.setFname(dto.getFname());
                user.setSname(dto.getSname());
                user.setUsername(dto.getUsername());
                user.setEmail(dto.getEmail());
                return user;

            }

            @Override
            public UserDTO asDTO(User entity) {
                UserDTO userDTO = new UserDTO();
                userDTO.setId(entity.getId());
                userDTO.setPassword(entity.getPassword());
                userDTO.setName(entity.getName());
                userDTO.setFname(entity.getFname());
                userDTO.setSname(entity.getSname());
                userDTO.setUsername(entity.getUsername());
                userDTO.setEmail(entity.getEmail());
                return userDTO;
            }

            @Override
            public List<User> asEntityList(List<UserDTO> dtoList) {
                return dtoList.stream()
                        .map(this::asEntity)
                        .collect(Collectors.toList());
            }

            @Override
            public List<UserDTO> asDTOList(List<User> entityList) {
                return entityList.stream()
                        .map(this::asDTO)
                        .collect(Collectors.toList());
            }
        };
    }

    @Bean
    public EmotionMapper emotionMapper() {
        return new EmotionMapper() {
            @Override
            public Emotion asEntity(EmotionDTO dto) {
                Emotion emotion = new Emotion();
                emotion.setId(dto.getId());
                emotion.setEmotionName(dto.getEmotionName());
                emotion.setSongs(dto.getSongs());
                return emotion;

            }

            @Override
            public EmotionDTO asDTO(Emotion entity) {
                EmotionDTO emotionDTO = new EmotionDTO();
                emotionDTO.setId(entity.getId());
                emotionDTO.setEmotionName(entity.getEmotionName());
                emotionDTO.setSongs(entity.getSongs());

                return emotionDTO;
            }

            @Override
            public List<Emotion> asEntityList(List<EmotionDTO> dtoList) {
                return dtoList.stream()
                        .map(this::asEntity)
                        .collect(Collectors.toList());
            }

            @Override
            public List<EmotionDTO> asDTOList(List<Emotion> entityList) {
                return entityList.stream()
                        .map(this::asDTO)
                        .collect(Collectors.toList());
            }
        };
    }

    @Bean
    public SongMapper songMapper() {
        return new SongMapper() {
            @Override
            public Song asEntity(SongDTO dto) {
                Song song = new Song();
                song.setId(dto.getId());
                song.setTitle(dto.getTitle());
                song.setArtist(dto.getArtist());
                song.setGenre(dto.getGenre());
                song.setEmotion(dto.getEmotion());
                song.setApiKey(dto.getApiKey());
                return song;

            }

            @Override
            public SongDTO asDTO(Song entity) {
                SongDTO songDTO = new SongDTO();
                songDTO.setId(entity.getId());
                songDTO.setTitle(entity.getTitle());
                songDTO.setArtist(entity.getArtist());
                songDTO.setGenre(entity.getGenre());
                songDTO.setEmotion(entity.getEmotion());
                songDTO.setApiKey(entity.getApiKey());

                return songDTO;
            }

            @Override
            public List<Song> asEntityList(List<SongDTO> dtoList) {
                return dtoList.stream()
                        .map(this::asEntity)
                        .collect(Collectors.toList());
            }

            @Override
            public List<SongDTO> asDTOList(List<Song> entityList) {
                return entityList.stream()
                        .map(this::asDTO)
                        .collect(Collectors.toList());
            }
        };
    }

    @Bean
    public PlaylistMapper playlistMapper() {
        return new PlaylistMapper() {
            @Override
            public Playlist asEntity(PlaylistDTO dto) {
                Playlist playlist = new Playlist();
                playlist.setId(dto.getId());
                playlist.setApiKey(dto.getApiKey());
                playlist.setUserId(dto.getUserId());
                return playlist;
            }

            @Override
            public PlaylistDTO asDTO(Playlist entity) {
                PlaylistDTO playlistDTO = new PlaylistDTO();
                playlistDTO.setId(entity.getId());
                playlistDTO.setApiKey(entity.getApiKey());
                playlistDTO.setUserId(entity.getUserId());
                return playlistDTO;
            }

            @Override
            public List<Playlist> asEntityList(List<PlaylistDTO> dtoList) {
                return dtoList.stream()
                        .map(this::asEntity)
                        .collect(Collectors.toList());
            }

            @Override
            public List<PlaylistDTO> asDTOList(List<Playlist> entityList) {
                return entityList.stream()
                        .map(this::asDTO)
                        .collect(Collectors.toList());
            }
        };
    }

}
