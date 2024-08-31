package pravna.com.myapp.service.mapper;

import org.mapstruct.*;
import pravna.com.myapp.domain.Optuzeni;
import pravna.com.myapp.domain.Optuznica;
import pravna.com.myapp.domain.Osoba;
import pravna.com.myapp.domain.Presuda;
import pravna.com.myapp.domain.RadnjaPresude;
import pravna.com.myapp.domain.Sud;
import pravna.com.myapp.service.dto.OptuzeniDTO;
import pravna.com.myapp.service.dto.OptuznicaDTO;
import pravna.com.myapp.service.dto.OsobaDTO;
import pravna.com.myapp.service.dto.PresudaDTO;
import pravna.com.myapp.service.dto.RadnjaPresudeDTO;
import pravna.com.myapp.service.dto.SudDTO;

/**
 * Mapper for the entity {@link Presuda} and its DTO {@link PresudaDTO}.
 */
@Mapper(componentModel = "spring")
public interface PresudaMapper extends EntityMapper<PresudaDTO, Presuda> {
    @Mapping(target = "radnja", source = "radnja", qualifiedByName = "radnjaPresudeId")
    @Mapping(target = "optuznica", source = "optuznica", qualifiedByName = "optuznicaId")
    @Mapping(target = "optuzeni", source = "optuzeni", qualifiedByName = "optuzeniId")
    @Mapping(target = "sudija", source = "sudija", qualifiedByName = "osobaId")
    @Mapping(target = "zapisnicar", source = "zapisnicar", qualifiedByName = "osobaId")
    @Mapping(target = "tuzilac", source = "tuzilac", qualifiedByName = "osobaId")
    @Mapping(target = "branilac", source = "branilac", qualifiedByName = "osobaId")
    @Mapping(target = "osteceni", source = "osteceni", qualifiedByName = "osobaId")
    @Mapping(target = "sud", source = "sud", qualifiedByName = "sudId")
    PresudaDTO toDto(Presuda s);

    @Named("radnjaPresudeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "vremeRadnje", source = "vremeRadnje")
    @Mapping(target = "mestoRadnje", source = "mestoRadnje")
    @Mapping(target = "mestoSmrti", source = "mestoSmrti")
    @Mapping(target = "vremeSmrti", source = "vremeSmrti")
    RadnjaPresudeDTO toDtoRadnjaPresudeId(RadnjaPresude radnjaPresude);

    @Named("optuznicaId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "kod", source = "kod")
    @Mapping(target = "datum", source = "datum")
    @Mapping(target = "ustanova", source = "ustanova")
    OptuznicaDTO toDtoOptuznicaId(Optuznica optuznica);

    @Named("optuzeniId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "ime", source = "ime")
    @Mapping(target = "jmbg", source = "jmbg")
    @Mapping(target = "imeOca", source = "imeOca")
    @Mapping(target = "imeMajke", source = "imeMajke")
    @Mapping(target = "pol", source = "pol")
    @Mapping(target = "datumRodjenja", source = "datumRodjenja")
    @Mapping(target = "mestoRodjenja", source = "mestoRodjenja")
    @Mapping(target = "drzavaRodjenja", source = "drzavaRodjenja")
    @Mapping(target = "prebivaliste", source = "prebivaliste")
    @Mapping(target = "bracniStatus", source = "bracniStatus")
    @Mapping(target = "brojDece", source = "brojDece")
    @Mapping(target = "brojMaloletneDece", source = "brojMaloletneDece")
    @Mapping(target = "imovinskoStanje", source = "imovinskoStanje")
    @Mapping(target = "obrazovanje", source = "obrazovanje")
    @Mapping(target = "zaposlenje", source = "zaposlenje")
    @Mapping(target = "mestoZaposlenja", source = "mestoZaposlenja")
    OptuzeniDTO toDtoOptuzeniId(Optuzeni optuzeni);

    @Named("osobaId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "ime", source = "ime")
    OsobaDTO toDtoOsobaId(Osoba osoba);

    @Named("sudId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "naziv", source = "naziv")
    @Mapping(target = "tip", source = "tip")
    @Mapping(target = "mesto", source = "mesto")
    SudDTO toDtoSudId(Sud sud);
}
