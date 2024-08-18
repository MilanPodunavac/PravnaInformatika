package pravna.com.myapp.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pravna.com.myapp.domain.Presuda;
import pravna.com.myapp.repository.PresudaRepository;
import pravna.com.myapp.service.PresudaService;
import pravna.com.myapp.service.dto.PresudaDTO;
import pravna.com.myapp.service.mapper.PresudaMapper;

/**
 * Service Implementation for managing {@link Presuda}.
 */
@Service
public class PresudaServiceImpl implements PresudaService {

    private final Logger log = LoggerFactory.getLogger(PresudaServiceImpl.class);

    private final PresudaRepository presudaRepository;

    private final PresudaMapper presudaMapper;

    public PresudaServiceImpl(PresudaRepository presudaRepository, PresudaMapper presudaMapper) {
        this.presudaRepository = presudaRepository;
        this.presudaMapper = presudaMapper;
    }

    @Override
    public PresudaDTO save(PresudaDTO presudaDTO) {
        log.debug("Request to save Presuda : {}", presudaDTO);
        Presuda presuda = presudaMapper.toEntity(presudaDTO);
        presuda = presudaRepository.save(presuda);
        return presudaMapper.toDto(presuda);
    }

    @Override
    public PresudaDTO update(PresudaDTO presudaDTO) {
        log.debug("Request to update Presuda : {}", presudaDTO);
        Presuda presuda = presudaMapper.toEntity(presudaDTO);
        presuda = presudaRepository.save(presuda);
        return presudaMapper.toDto(presuda);
    }

    @Override
    public Optional<PresudaDTO> partialUpdate(PresudaDTO presudaDTO) {
        log.debug("Request to partially update Presuda : {}", presudaDTO);

        return presudaRepository
            .findById(presudaDTO.getId())
            .map(existingPresuda -> {
                presudaMapper.partialUpdate(existingPresuda, presudaDTO);

                return existingPresuda;
            })
            .map(presudaRepository::save)
            .map(presudaMapper::toDto);
    }

    @Override
    public Page<PresudaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Presudas");
        return presudaRepository.findAll(pageable).map(presudaMapper::toDto);
    }

    @Override
    public Optional<PresudaDTO> findOne(String id) {
        log.debug("Request to get Presuda : {}", id);
        return presudaRepository.findById(id).map(presudaMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Presuda : {}", id);
        presudaRepository.deleteById(id);
    }
}
