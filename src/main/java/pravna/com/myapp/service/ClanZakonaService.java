package pravna.com.myapp.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pravna.com.myapp.domain.ClanZakona;

/**
 * Service Interface for managing {@link ClanZakona}.
 */
public interface ClanZakonaService {
    /**
     * Save a clanZakona.
     *
     * @param clanZakona the entity to save.
     * @return the persisted entity.
     */
    ClanZakona save(ClanZakona clanZakona);

    /**
     * Updates a clanZakona.
     *
     * @param clanZakona the entity to update.
     * @return the persisted entity.
     */
    ClanZakona update(ClanZakona clanZakona);

    /**
     * Partially updates a clanZakona.
     *
     * @param clanZakona the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ClanZakona> partialUpdate(ClanZakona clanZakona);

    /**
     * Get all the clanZakonas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ClanZakona> findAll(Pageable pageable);

    /**
     * Get the "id" clanZakona.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClanZakona> findOne(String id);

    /**
     * Delete the "id" clanZakona.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
