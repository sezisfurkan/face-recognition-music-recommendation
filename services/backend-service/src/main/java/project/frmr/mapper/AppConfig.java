package project.frmr.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.frmr.dto.UserDTO;
import project.frmr.entity.User;

import java.util.List;


@Configuration
public class AppConfig {


    @Bean
    public UserMapper userMapper() {
        return new UserMapper() {
            @Override
            public User asEntity(UserDTO dto) {
                return null;
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
