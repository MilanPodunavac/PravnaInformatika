package pravna.com.myapp.service.mapper;

import org.mapstruct.*;
import pravna.com.myapp.domain.Zakon;
import pravna.com.myapp.service.dto.ZakonDTO;

/**
 * Mapper for the entity {@link Zakon} and its DTO {@link ZakonDTO}.
 */
@Mapper(componentModel = "spring")
public interface ZakonMapper extends EntityMapper<ZakonDTO, Zakon> {}
