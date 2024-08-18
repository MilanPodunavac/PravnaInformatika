package pravna.com.myapp.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pravna.com.myapp.domain.Osoba;
import pravna.com.myapp.repository.OsobaRepository;
import pravna.com.myapp.service.dto.OsobaDTO;
import pravna.com.myapp.service.mapper.OsobaMapper;

/**
 * Service Implementation for managing {@link Osoba}.
 */
@Service
public class OsobaService {

    private final Logger log = LoggerFactory.getLogger(OsobaService.class);

    private final OsobaRepository osobaRepository;

    private final OsobaMapper osobaMapper;

    public OsobaService(OsobaRepository osobaRepository, OsobaMapper osobaMapper) {
        this.osobaRepository = osobaRepository;
        this.osobaMapper = osobaMapper;
    }

    /**
     * Save a osoba.
     *
     * @param osobaDTO the entity to save.
     * @return the persisted entity.
     */
    public OsobaDTO save(OsobaDTO osobaDTO) {
        log.debug("Request to save Osoba : {}", osobaDTO);
        Osoba osoba = osobaMapper.toEntity(osobaDTO);
        osoba = osobaRepository.save(osoba);
        return osobaMapper.toDto(osoba);
    }

    /**
     * Update a osoba.
     *
     * @param osobaDTO the entity to save.
     * @return the persisted entity.
     */
    public OsobaDTO update(OsobaDTO osobaDTO) {
        log.debug("Request to update Osoba : {}", osobaDTO);
        Osoba osoba = osobaMapper.toEntity(osobaDTO);
        osoba = osobaRepository.save(osoba);
        return osobaMapper.toDto(osoba);
    }

    /**
     * Partially update a osoba.
     *
     * @param osobaDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<OsobaDTO> partialUpdate(OsobaDTO osobaDTO) {
        log.debug("Request to partially update Osoba : {}", osobaDTO);

        return osobaRepository
            .findById(osobaDTO.getId())
            .map(existingOsoba -> {
                osobaMapper.partialUpdate(existingOsoba, osobaDTO);

                return existingOsoba;
            })
            .map(osobaRepository::save)
            .map(osobaMapper::toDto);
    }

    /**
     * Get all the osobas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<OsobaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Osobas");
        return osobaRepository.findAll(pageable).map(osobaMapper::toDto);
    }

    /**
     * Get one osoba by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<OsobaDTO> findOne(String id) {
        log.debug("Request to get Osoba : {}", id);
        return osobaRepository.findById(id).map(osobaMapper::toDto);
    }

    /**
     * Delete the osoba by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Osoba : {}", id);
        osobaRepository.deleteById(id);
    }
}
