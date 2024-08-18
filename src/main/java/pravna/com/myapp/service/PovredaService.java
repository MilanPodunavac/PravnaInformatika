package pravna.com.myapp.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pravna.com.myapp.domain.Povreda;
import pravna.com.myapp.repository.PovredaRepository;
import pravna.com.myapp.service.dto.PovredaDTO;
import pravna.com.myapp.service.mapper.PovredaMapper;

/**
 * Service Implementation for managing {@link Povreda}.
 */
@Service
public class PovredaService {

    private final Logger log = LoggerFactory.getLogger(PovredaService.class);

    private final PovredaRepository povredaRepository;

    private final PovredaMapper povredaMapper;

    public PovredaService(PovredaRepository povredaRepository, PovredaMapper povredaMapper) {
        this.povredaRepository = povredaRepository;
        this.povredaMapper = povredaMapper;
    }

    /**
     * Save a povreda.
     *
     * @param povredaDTO the entity to save.
     * @return the persisted entity.
     */
    public PovredaDTO save(PovredaDTO povredaDTO) {
        log.debug("Request to save Povreda : {}", povredaDTO);
        Povreda povreda = povredaMapper.toEntity(povredaDTO);
        povreda = povredaRepository.save(povreda);
        return povredaMapper.toDto(povreda);
    }

    /**
     * Update a povreda.
     *
     * @param povredaDTO the entity to save.
     * @return the persisted entity.
     */
    public PovredaDTO update(PovredaDTO povredaDTO) {
        log.debug("Request to update Povreda : {}", povredaDTO);
        Povreda povreda = povredaMapper.toEntity(povredaDTO);
        povreda = povredaRepository.save(povreda);
        return povredaMapper.toDto(povreda);
    }

    /**
     * Partially update a povreda.
     *
     * @param povredaDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PovredaDTO> partialUpdate(PovredaDTO povredaDTO) {
        log.debug("Request to partially update Povreda : {}", povredaDTO);

        return povredaRepository
            .findById(povredaDTO.getId())
            .map(existingPovreda -> {
                povredaMapper.partialUpdate(existingPovreda, povredaDTO);

                return existingPovreda;
            })
            .map(povredaRepository::save)
            .map(povredaMapper::toDto);
    }

    /**
     * Get all the povredas.
     *
     * @return the list of entities.
     */
    public List<PovredaDTO> findAll() {
        log.debug("Request to get all Povredas");
        return povredaRepository.findAll().stream().map(povredaMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one povreda by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<PovredaDTO> findOne(String id) {
        log.debug("Request to get Povreda : {}", id);
        return povredaRepository.findById(id).map(povredaMapper::toDto);
    }

    /**
     * Delete the povreda by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Povreda : {}", id);
        povredaRepository.deleteById(id);
    }
}
