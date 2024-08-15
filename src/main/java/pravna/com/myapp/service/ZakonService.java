package pravna.com.myapp.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pravna.com.myapp.domain.Zakon;

/**
 * Service Interface for managing {@link Zakon}.
 */
public interface ZakonService {
    /**
     * Save a zakon.
     *
     * @param zakon the entity to save.
     * @return the persisted entity.
     */
    Zakon save(Zakon zakon);

    /**
     * Updates a zakon.
     *
     * @param zakon the entity to update.
     * @return the persisted entity.
     */
    Zakon update(Zakon zakon);

    /**
     * Partially updates a zakon.
     *
     * @param zakon the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Zakon> partialUpdate(Zakon zakon);

    /**
     * Get all the zakons.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Zakon> findAll(Pageable pageable);

    /**
     * Get the "id" zakon.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Zakon> findOne(String id);

    /**
     * Delete the "id" zakon.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
