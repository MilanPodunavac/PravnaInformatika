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
    @Mapping(target = "tekst", source = "tekst")
    @Mapping(target = "datum", source = "datum")
    @Mapping(target = "datumObjave", source = "datumObjave")
    @Mapping(target = "datumPritvora", source = "datumPritvora")
    @Mapping(target = "tip", source = "tip")
    @Mapping(target = "broj", source = "broj")
    @Mapping(target = "godina", source = "godina")
    @Mapping(target = "optuznica", source = "optuznica")
    @Mapping(target = "datumOptuznice", source = "datumOptuznice")
    @Mapping(target = "pokusaj", source = "pokusaj")
    @Mapping(target = "krivica", source = "krivica")
    @Mapping(target = "nacin", source = "nacin")
    @Mapping(target = "radnja", source = "radnja")
    @Mapping(target = "optuzeni", source = "optuzeni")
    @Mapping(target = "sudija", source = "sudija")
    @Mapping(target = "zapisnicar", source = "zapisnicar")
    @Mapping(target = "tuzilac", source = "tuzilac")
    @Mapping(target = "branilac", source = "branilac")
    PresudaDTO toDtoPresudaId(Presuda presuda);
}
