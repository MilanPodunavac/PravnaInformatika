package pravna.com.myapp.service.mapper;

import org.mapstruct.*;
import pravna.com.myapp.domain.Kazna;
import pravna.com.myapp.domain.Presuda;
import pravna.com.myapp.service.dto.KaznaDTO;
import pravna.com.myapp.service.dto.PresudaDTO;

/**
 * Mapper for the entity {@link Kazna} and its DTO {@link KaznaDTO}.
 */
@Mapper(componentModel = "spring")
public interface KaznaMapper extends EntityMapper<KaznaDTO, Kazna> {
    @Mapping(target = "presuda", source = "presuda", qualifiedByName = "presudaId")
    KaznaDTO toDto(Kazna s);

    @Named("presudaId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PresudaDTO toDtoPresudaId(Presuda presuda);
}
