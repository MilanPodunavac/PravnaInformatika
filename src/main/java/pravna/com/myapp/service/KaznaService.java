package pravna.com.myapp.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pravna.com.myapp.domain.Kazna;
import pravna.com.myapp.repository.KaznaRepository;

/**
 * Service Implementation for managing {@link Kazna}.
 */
@Service
public class KaznaService {

    private final Logger log = LoggerFactory.getLogger(KaznaService.class);

    private final KaznaRepository kaznaRepository;

    public KaznaService(KaznaRepository kaznaRepository) {
        this.kaznaRepository = kaznaRepository;
    }

    /**
     * Save a kazna.
     *
     * @param kazna the entity to save.
     * @return the persisted entity.
     */
    public Kazna save(Kazna kazna) {
        log.debug("Request to save Kazna : {}", kazna);
        return kaznaRepository.save(kazna);
    }

    /**
     * Update a kazna.
     *
     * @param kazna the entity to save.
     * @return the persisted entity.
     */
    public Kazna update(Kazna kazna) {
        log.debug("Request to update Kazna : {}", kazna);
        return kaznaRepository.save(kazna);
    }

    /**
     * Partially update a kazna.
     *
     * @param kazna the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Kazna> partialUpdate(Kazna kazna) {
        log.debug("Request to partially update Kazna : {}", kazna);

        return kaznaRepository
            .findById(kazna.getId())
            .map(existingKazna -> {
                if (kazna.getTip() != null) {
                    existingKazna.setTip(kazna.getTip());
                }
                if (kazna.getDuzinaPritvora() != null) {
                    existingKazna.setDuzinaPritvora(kazna.getDuzinaPritvora());
                }
                if (kazna.getUracunavanjePritvora() != null) {
                    existingKazna.setUracunavanjePritvora(kazna.getUracunavanjePritvora());
                }
                if (kazna.getKolicinaNovca() != null) {
                    existingKazna.setKolicinaNovca(kazna.getKolicinaNovca());
                }
                if (kazna.getPrimalacNovca() != null) {
                    existingKazna.setPrimalacNovca(kazna.getPrimalacNovca());
                }
                if (kazna.getNazivImovine() != null) {
                    existingKazna.setNazivImovine(kazna.getNazivImovine());
                }

                return existingKazna;
            })
            .map(kaznaRepository::save);
    }

    /**
     * Get all the kaznas.
     *
     * @return the list of entities.
     */
    public List<Kazna> findAll() {
        log.debug("Request to get all Kaznas");
        return kaznaRepository.findAll();
    }

    /**
     * Get one kazna by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<Kazna> findOne(String id) {
        log.debug("Request to get Kazna : {}", id);
        return kaznaRepository.findById(id);
    }

    /**
     * Delete the kazna by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Kazna : {}", id);
        kaznaRepository.deleteById(id);
    }
}
