package project.frmr.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.frmr.dto.UserDTO;
import project.frmr.entity.User;
import project.frmr.repository.UserRepository;

import java.util.List;


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
                return null;



            }

            @Override
            public List<User> asEntityList(List<UserDTO> dtoList) {
                return null;
            }

            @Override
            public List<UserDTO> asDTOList(List<User> entityList) {
                return null;
            }
        };
    }
}
