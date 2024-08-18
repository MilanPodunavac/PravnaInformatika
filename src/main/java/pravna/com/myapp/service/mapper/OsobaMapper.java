package pravna.com.myapp.service.mapper;

import org.mapstruct.*;
import pravna.com.myapp.domain.Osoba;
import pravna.com.myapp.service.dto.OsobaDTO;

/**
 * Mapper for the entity {@link Osoba} and its DTO {@link OsobaDTO}.
 */
@Mapper(componentModel = "spring")
public interface OsobaMapper extends EntityMapper<OsobaDTO, Osoba> {}
