package pravna.com.myapp.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pravna.com.myapp.domain.RadnjaPresude;
import pravna.com.myapp.repository.RadnjaPresudeRepository;
import pravna.com.myapp.service.dto.RadnjaPresudeDTO;
import pravna.com.myapp.service.mapper.RadnjaPresudeMapper;

/**
 * Service Implementation for managing {@link RadnjaPresude}.
 */
@Service
public class RadnjaPresudeService {

    private final Logger log = LoggerFactory.getLogger(RadnjaPresudeService.class);

    private final RadnjaPresudeRepository radnjaPresudeRepository;

    private final RadnjaPresudeMapper radnjaPresudeMapper;

    public RadnjaPresudeService(RadnjaPresudeRepository radnjaPresudeRepository, RadnjaPresudeMapper radnjaPresudeMapper) {
        this.radnjaPresudeRepository = radnjaPresudeRepository;
        this.radnjaPresudeMapper = radnjaPresudeMapper;
    }

    /**
     * Save a radnjaPresude.
     *
     * @param radnjaPresudeDTO the entity to save.
     * @return the persisted entity.
     */
    public RadnjaPresudeDTO save(RadnjaPresudeDTO radnjaPresudeDTO) {
        log.debug("Request to save RadnjaPresude : {}", radnjaPresudeDTO);
        RadnjaPresude radnjaPresude = radnjaPresudeMapper.toEntity(radnjaPresudeDTO);
        radnjaPresude = radnjaPresudeRepository.save(radnjaPresude);
        return radnjaPresudeMapper.toDto(radnjaPresude);
    }

    /**
     * Update a radnjaPresude.
     *
     * @param radnjaPresudeDTO the entity to save.
     * @return the persisted entity.
     */
    public RadnjaPresudeDTO update(RadnjaPresudeDTO radnjaPresudeDTO) {
        log.debug("Request to update RadnjaPresude : {}", radnjaPresudeDTO);
        RadnjaPresude radnjaPresude = radnjaPresudeMapper.toEntity(radnjaPresudeDTO);
        radnjaPresude = radnjaPresudeRepository.save(radnjaPresude);
        return radnjaPresudeMapper.toDto(radnjaPresude);
    }

    /**
     * Partially update a radnjaPresude.
     *
     * @param radnjaPresudeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RadnjaPresudeDTO> partialUpdate(RadnjaPresudeDTO radnjaPresudeDTO) {
        log.debug("Request to partially update RadnjaPresude : {}", radnjaPresudeDTO);

        return radnjaPresudeRepository
            .findById(radnjaPresudeDTO.getId())
            .map(existingRadnjaPresude -> {
                radnjaPresudeMapper.partialUpdate(existingRadnjaPresude, radnjaPresudeDTO);

                return existingRadnjaPresude;
            })
            .map(radnjaPresudeRepository::save)
            .map(radnjaPresudeMapper::toDto);
    }

    /**
     * Get all the radnjaPresudes.
     *
     * @return the list of entities.
     */
    public List<RadnjaPresudeDTO> findAll() {
        log.debug("Request to get all RadnjaPresudes");
        return radnjaPresudeRepository.findAll().stream().map(radnjaPresudeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get all the radnjaPresudes where Presuda is {@code null}.
     *  @return the list of entities.
     */

    public List<RadnjaPresudeDTO> findAllWherePresudaIsNull() {
        log.debug("Request to get all radnjaPresudes where Presuda is null");
        return StreamSupport
            .stream(radnjaPresudeRepository.findAll().spliterator(), false)
            .filter(radnjaPresude -> radnjaPresude.getPresuda() == null)
            .map(radnjaPresudeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one radnjaPresude by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<RadnjaPresudeDTO> findOne(String id) {
        log.debug("Request to get RadnjaPresude : {}", id);
        return radnjaPresudeRepository.findById(id).map(radnjaPresudeMapper::toDto);
    }

    /**
     * Delete the radnjaPresude by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete RadnjaPresude : {}", id);
        radnjaPresudeRepository.deleteById(id);
    }
}
