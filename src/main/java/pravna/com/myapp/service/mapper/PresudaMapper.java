package pravna.com.myapp.service.mapper;

import org.mapstruct.*;
import pravna.com.myapp.domain.Optuzeni;
import pravna.com.myapp.domain.Osoba;
import pravna.com.myapp.domain.Presuda;
import pravna.com.myapp.domain.RadnjaPresude;
import pravna.com.myapp.service.dto.OptuzeniDTO;
import pravna.com.myapp.service.dto.OsobaDTO;
import pravna.com.myapp.service.dto.PresudaDTO;
import pravna.com.myapp.service.dto.RadnjaPresudeDTO;

/**
 * Mapper for the entity {@link Presuda} and its DTO {@link PresudaDTO}.
 */
@Mapper(componentModel = "spring")
public interface PresudaMapper extends EntityMapper<PresudaDTO, Presuda> {
    @Mapping(target = "radnja", source = "radnja", qualifiedByName = "radnjaPresudeId")
    @Mapping(target = "optuzeni", source = "optuzeni", qualifiedByName = "optuzeniId")
    @Mapping(target = "sudija", source = "sudija", qualifiedByName = "osobaId")
    @Mapping(target = "zapisnicar", source = "zapisnicar", qualifiedByName = "osobaId")
    @Mapping(target = "tuzilac", source = "tuzilac", qualifiedByName = "osobaId")
    @Mapping(target = "branilac", source = "branilac", qualifiedByName = "osobaId")
    PresudaDTO toDto(Presuda s);

    @Named("radnjaPresudeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    RadnjaPresudeDTO toDtoRadnjaPresudeId(RadnjaPresude radnjaPresude);

    @Named("optuzeniId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OptuzeniDTO toDtoOptuzeniId(Optuzeni optuzeni);

    @Named("osobaId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OsobaDTO toDtoOsobaId(Osoba osoba);
}
