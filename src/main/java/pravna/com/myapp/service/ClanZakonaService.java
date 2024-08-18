package pravna.com.myapp.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pravna.com.myapp.service.dto.ClanZakonaDTO;

/**
 * Service Interface for managing {@link pravna.com.myapp.domain.ClanZakona}.
 */
public interface ClanZakonaService {
    /**
     * Save a clanZakona.
     *
     * @param clanZakonaDTO the entity to save.
     * @return the persisted entity.
     */
    ClanZakonaDTO save(ClanZakonaDTO clanZakonaDTO);

    /**
     * Updates a clanZakona.
     *
     * @param clanZakonaDTO the entity to update.
     * @return the persisted entity.
     */
    ClanZakonaDTO update(ClanZakonaDTO clanZakonaDTO);

    /**
     * Partially updates a clanZakona.
     *
     * @param clanZakonaDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ClanZakonaDTO> partialUpdate(ClanZakonaDTO clanZakonaDTO);

    /**
     * Get all the clanZakonas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ClanZakonaDTO> findAll(Pageable pageable);

    /**
     * Get the "id" clanZakona.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClanZakonaDTO> findOne(String id);

    /**
     * Delete the "id" clanZakona.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
