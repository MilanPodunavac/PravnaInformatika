package pravna.com.myapp.service.mapper;

import org.mapstruct.*;
import pravna.com.myapp.domain.Optuzeni;
import pravna.com.myapp.service.dto.OptuzeniDTO;

/**
 * Mapper for the entity {@link Optuzeni} and its DTO {@link OptuzeniDTO}.
 */
@Mapper(componentModel = "spring")
public interface OptuzeniMapper extends EntityMapper<OptuzeniDTO, Optuzeni> {}
