package pravna.com.myapp.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pravna.com.myapp.domain.Optuznica;
import pravna.com.myapp.repository.OptuznicaRepository;
import pravna.com.myapp.service.dto.OptuznicaDTO;
import pravna.com.myapp.service.mapper.OptuznicaMapper;

/**
 * Service Implementation for managing {@link Optuznica}.
 */
@Service
public class OptuznicaService {

    private final Logger log = LoggerFactory.getLogger(OptuznicaService.class);

    private final OptuznicaRepository optuznicaRepository;

    private final OptuznicaMapper optuznicaMapper;

    public OptuznicaService(OptuznicaRepository optuznicaRepository, OptuznicaMapper optuznicaMapper) {
        this.optuznicaRepository = optuznicaRepository;
        this.optuznicaMapper = optuznicaMapper;
    }

    /**
     * Save a optuznica.
     *
     * @param optuznicaDTO the entity to save.
     * @return the persisted entity.
     */
    public OptuznicaDTO save(OptuznicaDTO optuznicaDTO) {
        log.debug("Request to save Optuznica : {}", optuznicaDTO);
        Optuznica optuznica = optuznicaMapper.toEntity(optuznicaDTO);
        optuznica = optuznicaRepository.save(optuznica);
        return optuznicaMapper.toDto(optuznica);
    }

    /**
     * Update a optuznica.
     *
     * @param optuznicaDTO the entity to save.
     * @return the persisted entity.
     */
    public OptuznicaDTO update(OptuznicaDTO optuznicaDTO) {
        log.debug("Request to update Optuznica : {}", optuznicaDTO);
        Optuznica optuznica = optuznicaMapper.toEntity(optuznicaDTO);
        optuznica = optuznicaRepository.save(optuznica);
        return optuznicaMapper.toDto(optuznica);
    }

    /**
     * Partially update a optuznica.
     *
     * @param optuznicaDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<OptuznicaDTO> partialUpdate(OptuznicaDTO optuznicaDTO) {
        log.debug("Request to partially update Optuznica : {}", optuznicaDTO);

        return optuznicaRepository
            .findById(optuznicaDTO.getId())
            .map(existingOptuznica -> {
                optuznicaMapper.partialUpdate(existingOptuznica, optuznicaDTO);

                return existingOptuznica;
            })
            .map(optuznicaRepository::save)
            .map(optuznicaMapper::toDto);
    }

    /**
     * Get all the optuznicas.
     *
     * @return the list of entities.
     */
    public List<OptuznicaDTO> findAll() {
        log.debug("Request to get all Optuznicas");
        return optuznicaRepository.findAll().stream().map(optuznicaMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get all the optuznicas where Presuda is {@code null}.
     *  @return the list of entities.
     */

    public List<OptuznicaDTO> findAllWherePresudaIsNull() {
        log.debug("Request to get all optuznicas where Presuda is null");
        return StreamSupport
            .stream(optuznicaRepository.findAll().spliterator(), false)
            .filter(optuznica -> optuznica.getPresuda() == null)
            .map(optuznicaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one optuznica by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<OptuznicaDTO> findOne(String id) {
        log.debug("Request to get Optuznica : {}", id);
        return optuznicaRepository.findById(id).map(optuznicaMapper::toDto);
    }

    /**
     * Delete the optuznica by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Optuznica : {}", id);
        optuznicaRepository.deleteById(id);
    }
}
