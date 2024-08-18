package pravna.com.myapp.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pravna.com.myapp.service.dto.ZakonDTO;

/**
 * Service Interface for managing {@link pravna.com.myapp.domain.Zakon}.
 */
public interface ZakonService {
    /**
     * Save a zakon.
     *
     * @param zakonDTO the entity to save.
     * @return the persisted entity.
     */
    ZakonDTO save(ZakonDTO zakonDTO);

    /**
     * Updates a zakon.
     *
     * @param zakonDTO the entity to update.
     * @return the persisted entity.
     */
    ZakonDTO update(ZakonDTO zakonDTO);

    /**
     * Partially updates a zakon.
     *
     * @param zakonDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ZakonDTO> partialUpdate(ZakonDTO zakonDTO);

    /**
     * Get all the zakons.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ZakonDTO> findAll(Pageable pageable);

    /**
     * Get the "id" zakon.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ZakonDTO> findOne(String id);

    /**
     * Delete the "id" zakon.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
