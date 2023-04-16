package project.frmr.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import project.frmr.dto.EmotionDTO;
import project.frmr.entity.Emotion;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface EmotionMapper extends GenericMapper<Emotion, EmotionDTO> {

    @Override
    @Mapping(target = "id", ignore = false)
    Emotion asEntity(EmotionDTO dto);
}
