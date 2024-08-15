package pravna.com.myapp.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pravna.com.myapp.domain.Osoba;
import pravna.com.myapp.repository.OsobaRepository;

/**
 * Service Implementation for managing {@link Osoba}.
 */
@Service
public class OsobaService {

    private final Logger log = LoggerFactory.getLogger(OsobaService.class);

    private final OsobaRepository osobaRepository;

    public OsobaService(OsobaRepository osobaRepository) {
        this.osobaRepository = osobaRepository;
    }

    /**
     * Save a osoba.
     *
     * @param osoba the entity to save.
     * @return the persisted entity.
     */
    public Osoba save(Osoba osoba) {
        log.debug("Request to save Osoba : {}", osoba);
        return osobaRepository.save(osoba);
    }

    /**
     * Update a osoba.
     *
     * @param osoba the entity to save.
     * @return the persisted entity.
     */
    public Osoba update(Osoba osoba) {
        log.debug("Request to update Osoba : {}", osoba);
        return osobaRepository.save(osoba);
    }

    /**
     * Partially update a osoba.
     *
     * @param osoba the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Osoba> partialUpdate(Osoba osoba) {
        log.debug("Request to partially update Osoba : {}", osoba);

        return osobaRepository
            .findById(osoba.getId())
            .map(existingOsoba -> {
                if (osoba.getIme() != null) {
                    existingOsoba.setIme(osoba.getIme());
                }

                return existingOsoba;
            })
            .map(osobaRepository::save);
    }

    /**
     * Get all the osobas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<Osoba> findAll(Pageable pageable) {
        log.debug("Request to get all Osobas");
        return osobaRepository.findAll(pageable);
    }

    /**
     * Get all the osobas with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<Osoba> findAllWithEagerRelationships(Pageable pageable) {
        return osobaRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one osoba by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<Osoba> findOne(String id) {
        log.debug("Request to get Osoba : {}", id);
        return osobaRepository.findOneWithEagerRelationships(id);
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
