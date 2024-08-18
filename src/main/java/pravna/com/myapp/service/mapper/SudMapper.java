package pravna.com.myapp.service.mapper;

import org.mapstruct.*;
import pravna.com.myapp.domain.Sud;
import pravna.com.myapp.service.dto.SudDTO;

/**
 * Mapper for the entity {@link Sud} and its DTO {@link SudDTO}.
 */
@Mapper(componentModel = "spring")
public interface SudMapper extends EntityMapper<SudDTO, Sud> {}
