package pravna.com.myapp.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pravna.com.myapp.service.dto.PresudaDTO;

/**
 * Service Interface for managing {@link pravna.com.myapp.domain.Presuda}.
 */
public interface PresudaService {
    /**
     * Save a presuda.
     *
     * @param presudaDTO the entity to save.
     * @return the persisted entity.
     */
    PresudaDTO save(PresudaDTO presudaDTO);

    /**
     * Updates a presuda.
     *
     * @param presudaDTO the entity to update.
     * @return the persisted entity.
     */
    PresudaDTO update(PresudaDTO presudaDTO);

    /**
     * Partially updates a presuda.
     *
     * @param presudaDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PresudaDTO> partialUpdate(PresudaDTO presudaDTO);

    /**
     * Get all the presudas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PresudaDTO> findAll(Pageable pageable);

    /**
     * Get all the presudas with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PresudaDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" presuda.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PresudaDTO> findOne(String id);

    /**
     * Delete the "id" presuda.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
