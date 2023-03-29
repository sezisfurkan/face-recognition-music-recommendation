package project.frmr.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.frmr.dto.UserDTO;
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
}
