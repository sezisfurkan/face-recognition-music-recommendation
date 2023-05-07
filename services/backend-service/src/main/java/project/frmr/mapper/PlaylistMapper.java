package project.frmr.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import project.frmr.dto.PlaylistDTO;
import project.frmr.entity.Playlist;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface PlaylistMapper extends  GenericMapper<Playlist, PlaylistDTO>{
    @Override
    @Mapping(target = "id", ignore = false)
    Playlist asEntity(PlaylistDTO dto);

}
