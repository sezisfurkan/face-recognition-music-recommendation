package project.frmr.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import project.frmr.dto.UserDTO;
import project.frmr.entity.User;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface UserMapper extends GenericMapper<User, UserDTO>{
    @Override
    @Mapping(target = "id", ignore = false)
    User asEntity(UserDTO dto);
}
