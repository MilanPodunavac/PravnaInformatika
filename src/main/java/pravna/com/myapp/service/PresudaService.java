package pravna.com.myapp.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pravna.com.myapp.domain.Presuda;

/**
 * Service Interface for managing {@link Presuda}.
 */
public interface PresudaService {
    /**
     * Save a presuda.
     *
     * @param presuda the entity to save.
     * @return the persisted entity.
     */
    Presuda save(Presuda presuda);

    /**
     * Updates a presuda.
     *
     * @param presuda the entity to update.
     * @return the persisted entity.
     */
    Presuda update(Presuda presuda);

    /**
     * Partially updates a presuda.
     *
     * @param presuda the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Presuda> partialUpdate(Presuda presuda);

    /**
     * Get all the presudas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Presuda> findAll(Pageable pageable);

    /**
     * Get the "id" presuda.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Presuda> findOne(String id);

    /**
     * Delete the "id" presuda.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
