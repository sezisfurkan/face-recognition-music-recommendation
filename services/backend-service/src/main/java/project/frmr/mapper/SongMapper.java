package project.frmr.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import project.frmr.dto.SongDTO;
import project.frmr.entity.Song;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface SongMapper extends GenericMapper<Song, SongDTO> {

    @Override
    @Mapping(target = "id", ignore = false)
    Song asEntity(SongDTO dto);

}
