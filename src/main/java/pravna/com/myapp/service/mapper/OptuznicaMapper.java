package pravna.com.myapp.service.mapper;

import org.mapstruct.*;
import pravna.com.myapp.domain.Optuznica;
import pravna.com.myapp.service.dto.OptuznicaDTO;

/**
 * Mapper for the entity {@link Optuznica} and its DTO {@link OptuznicaDTO}.
 */
@Mapper(componentModel = "spring")
public interface OptuznicaMapper extends EntityMapper<OptuznicaDTO, Optuznica> {}
