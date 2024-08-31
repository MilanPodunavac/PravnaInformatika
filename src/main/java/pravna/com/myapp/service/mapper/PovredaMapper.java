package pravna.com.myapp.service.mapper;

import org.mapstruct.*;
import pravna.com.myapp.domain.Povreda;
import pravna.com.myapp.domain.RadnjaPresude;
import pravna.com.myapp.service.dto.PovredaDTO;
import pravna.com.myapp.service.dto.RadnjaPresudeDTO;

/**
 * Mapper for the entity {@link Povreda} and its DTO {@link PovredaDTO}.
 */
@Mapper(componentModel = "spring")
public interface PovredaMapper extends EntityMapper<PovredaDTO, Povreda> {
    @Mapping(target = "radnja", source = "radnja", qualifiedByName = "radnjaPresudeId")
    PovredaDTO toDto(Povreda s);

    @Named("radnjaPresudeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "vremeRadnje", source = "vremeRadnje")
    @Mapping(target = "mestoRadnje", source = "mestoRadnje")
    @Mapping(target = "mestoSmrti", source = "mestoSmrti")
    @Mapping(target = "vremeSmrti", source = "vremeSmrti")
    RadnjaPresudeDTO toDtoRadnjaPresudeId(RadnjaPresude radnjaPresude);
}
