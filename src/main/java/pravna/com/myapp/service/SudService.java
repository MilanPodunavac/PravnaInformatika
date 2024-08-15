package pravna.com.myapp.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pravna.com.myapp.domain.Sud;
import pravna.com.myapp.repository.SudRepository;

/**
 * Service Implementation for managing {@link Sud}.
 */
@Service
public class SudService {

    private final Logger log = LoggerFactory.getLogger(SudService.class);

    private final SudRepository sudRepository;

    public SudService(SudRepository sudRepository) {
        this.sudRepository = sudRepository;
    }

    /**
     * Save a sud.
     *
     * @param sud the entity to save.
     * @return the persisted entity.
     */
    public Sud save(Sud sud) {
        log.debug("Request to save Sud : {}", sud);
        return sudRepository.save(sud);
    }

    /**
     * Update a sud.
     *
     * @param sud the entity to save.
     * @return the persisted entity.
     */
    public Sud update(Sud sud) {
        log.debug("Request to update Sud : {}", sud);
        return sudRepository.save(sud);
    }

    /**
     * Partially update a sud.
     *
     * @param sud the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Sud> partialUpdate(Sud sud) {
        log.debug("Request to partially update Sud : {}", sud);

        return sudRepository
            .findById(sud.getId())
            .map(existingSud -> {
                if (sud.getTip() != null) {
                    existingSud.setTip(sud.getTip());
                }
                if (sud.getNaselje() != null) {
                    existingSud.setNaselje(sud.getNaselje());
                }

                return existingSud;
            })
            .map(sudRepository::save);
    }

    /**
     * Get all the suds.
     *
     * @return the list of entities.
     */
    public List<Sud> findAll() {
        log.debug("Request to get all Suds");
        return sudRepository.findAll();
    }

    /**
     * Get one sud by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<Sud> findOne(String id) {
        log.debug("Request to get Sud : {}", id);
        return sudRepository.findById(id);
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
