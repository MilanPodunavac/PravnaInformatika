package pravna.com.myapp.service.mapper;

import org.mapstruct.*;
import pravna.com.myapp.domain.ClanZakona;
import pravna.com.myapp.domain.Zakon;
import pravna.com.myapp.service.dto.ClanZakonaDTO;
import pravna.com.myapp.service.dto.ZakonDTO;

/**
 * Mapper for the entity {@link ClanZakona} and its DTO {@link ClanZakonaDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClanZakonaMapper extends EntityMapper<ClanZakonaDTO, ClanZakona> {
    @Mapping(target = "zakon", source = "zakon", qualifiedByName = "zakonId")
    ClanZakonaDTO toDto(ClanZakona s);

    @Named("zakonId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ZakonDTO toDtoZakonId(Zakon zakon);
}
