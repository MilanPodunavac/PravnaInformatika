package pravna.com.myapp.service.mapper;

import org.mapstruct.*;
import pravna.com.myapp.domain.RadnjaPresude;
import pravna.com.myapp.service.dto.RadnjaPresudeDTO;

/**
 * Mapper for the entity {@link RadnjaPresude} and its DTO {@link RadnjaPresudeDTO}.
 */
@Mapper(componentModel = "spring")
public interface RadnjaPresudeMapper extends EntityMapper<RadnjaPresudeDTO, RadnjaPresude> {}
