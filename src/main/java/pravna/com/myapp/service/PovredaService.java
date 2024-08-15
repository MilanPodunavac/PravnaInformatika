package pravna.com.myapp.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pravna.com.myapp.domain.Povreda;
import pravna.com.myapp.repository.PovredaRepository;

/**
 * Service Implementation for managing {@link Povreda}.
 */
@Service
public class PovredaService {

    private final Logger log = LoggerFactory.getLogger(PovredaService.class);

    private final PovredaRepository povredaRepository;

    public PovredaService(PovredaRepository povredaRepository) {
        this.povredaRepository = povredaRepository;
    }

    /**
     * Save a povreda.
     *
     * @param povreda the entity to save.
     * @return the persisted entity.
     */
    public Povreda save(Povreda povreda) {
        log.debug("Request to save Povreda : {}", povreda);
        return povredaRepository.save(povreda);
    }

    /**
     * Update a povreda.
     *
     * @param povreda the entity to save.
     * @return the persisted entity.
     */
    public Povreda update(Povreda povreda) {
        log.debug("Request to update Povreda : {}", povreda);
        return povredaRepository.save(povreda);
    }

    /**
     * Partially update a povreda.
     *
     * @param povreda the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Povreda> partialUpdate(Povreda povreda) {
        log.debug("Request to partially update Povreda : {}", povreda);

        return povredaRepository
            .findById(povreda.getId())
            .map(existingPovreda -> {
                if (povreda.getOruzje() != null) {
                    existingPovreda.setOruzje(povreda.getOruzje());
                }
                if (povreda.getDeoTela() != null) {
                    existingPovreda.setDeoTela(povreda.getDeoTela());
                }
                if (povreda.getPovrede() != null) {
                    existingPovreda.setPovrede(povreda.getPovrede());
                }

                return existingPovreda;
            })
            .map(povredaRepository::save);
    }

    /**
     * Get all the povredas.
     *
     * @return the list of entities.
     */
    public List<Povreda> findAll() {
        log.debug("Request to get all Povredas");
        return povredaRepository.findAll();
    }

    /**
     * Get one povreda by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<Povreda> findOne(String id) {
        log.debug("Request to get Povreda : {}", id);
        return povredaRepository.findById(id);
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
