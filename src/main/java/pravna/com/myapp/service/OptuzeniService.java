package pravna.com.myapp.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pravna.com.myapp.domain.Optuzeni;
import pravna.com.myapp.repository.OptuzeniRepository;
import pravna.com.myapp.service.dto.OptuzeniDTO;
import pravna.com.myapp.service.mapper.OptuzeniMapper;

/**
 * Service Implementation for managing {@link Optuzeni}.
 */
@Service
public class OptuzeniService {

    private final Logger log = LoggerFactory.getLogger(OptuzeniService.class);

    private final OptuzeniRepository optuzeniRepository;

    private final OptuzeniMapper optuzeniMapper;

    public OptuzeniService(OptuzeniRepository optuzeniRepository, OptuzeniMapper optuzeniMapper) {
        this.optuzeniRepository = optuzeniRepository;
        this.optuzeniMapper = optuzeniMapper;
    }

    /**
     * Save a optuzeni.
     *
     * @param optuzeniDTO the entity to save.
     * @return the persisted entity.
     */
    public OptuzeniDTO save(OptuzeniDTO optuzeniDTO) {
        log.debug("Request to save Optuzeni : {}", optuzeniDTO);
        Optuzeni optuzeni = optuzeniMapper.toEntity(optuzeniDTO);
        optuzeni = optuzeniRepository.save(optuzeni);
        return optuzeniMapper.toDto(optuzeni);
    }

    /**
     * Update a optuzeni.
     *
     * @param optuzeniDTO the entity to save.
     * @return the persisted entity.
     */
    public OptuzeniDTO update(OptuzeniDTO optuzeniDTO) {
        log.debug("Request to update Optuzeni : {}", optuzeniDTO);
        Optuzeni optuzeni = optuzeniMapper.toEntity(optuzeniDTO);
        optuzeni = optuzeniRepository.save(optuzeni);
        return optuzeniMapper.toDto(optuzeni);
    }

    /**
     * Partially update a optuzeni.
     *
     * @param optuzeniDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<OptuzeniDTO> partialUpdate(OptuzeniDTO optuzeniDTO) {
        log.debug("Request to partially update Optuzeni : {}", optuzeniDTO);

        return optuzeniRepository
            .findById(optuzeniDTO.getId())
            .map(existingOptuzeni -> {
                optuzeniMapper.partialUpdate(existingOptuzeni, optuzeniDTO);

                return existingOptuzeni;
            })
            .map(optuzeniRepository::save)
            .map(optuzeniMapper::toDto);
    }

    /**
     * Get all the optuzenis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<OptuzeniDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Optuzenis");
        return optuzeniRepository.findAll(pageable).map(optuzeniMapper::toDto);
    }

    /**
     * Get one optuzeni by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<OptuzeniDTO> findOne(String id) {
        log.debug("Request to get Optuzeni : {}", id);
        return optuzeniRepository.findById(id).map(optuzeniMapper::toDto);
    }

    /**
     * Delete the optuzeni by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Optuzeni : {}", id);
        optuzeniRepository.deleteById(id);
    }
}
