package pravna.com.myapp.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pravna.com.myapp.domain.Sud;
import pravna.com.myapp.repository.SudRepository;
import pravna.com.myapp.service.dto.SudDTO;
import pravna.com.myapp.service.mapper.SudMapper;

/**
 * Service Implementation for managing {@link Sud}.
 */
@Service
public class SudService {

    private final Logger log = LoggerFactory.getLogger(SudService.class);

    private final SudRepository sudRepository;

    private final SudMapper sudMapper;

    public SudService(SudRepository sudRepository, SudMapper sudMapper) {
        this.sudRepository = sudRepository;
        this.sudMapper = sudMapper;
    }

    /**
     * Save a sud.
     *
     * @param sudDTO the entity to save.
     * @return the persisted entity.
     */
    public SudDTO save(SudDTO sudDTO) {
        log.debug("Request to save Sud : {}", sudDTO);
        Sud sud = sudMapper.toEntity(sudDTO);
        sud = sudRepository.save(sud);
        return sudMapper.toDto(sud);
    }

    /**
     * Update a sud.
     *
     * @param sudDTO the entity to save.
     * @return the persisted entity.
     */
    public SudDTO update(SudDTO sudDTO) {
        log.debug("Request to update Sud : {}", sudDTO);
        Sud sud = sudMapper.toEntity(sudDTO);
        sud = sudRepository.save(sud);
        return sudMapper.toDto(sud);
    }

    /**
     * Partially update a sud.
     *
     * @param sudDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<SudDTO> partialUpdate(SudDTO sudDTO) {
        log.debug("Request to partially update Sud : {}", sudDTO);

        return sudRepository
            .findById(sudDTO.getId())
            .map(existingSud -> {
                sudMapper.partialUpdate(existingSud, sudDTO);

                return existingSud;
            })
            .map(sudRepository::save)
            .map(sudMapper::toDto);
    }

    /**
     * Get all the suds.
     *
     * @return the list of entities.
     */
    public List<SudDTO> findAll() {
        log.debug("Request to get all Suds");
        return sudRepository.findAll().stream().map(sudMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one sud by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<SudDTO> findOne(String id) {
        log.debug("Request to get Sud : {}", id);
        return sudRepository.findById(id).map(sudMapper::toDto);
    }

    /**
     * Delete the sud by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Sud : {}", id);
        sudRepository.deleteById(id);
    }
}
