package pravna.com.myapp.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pravna.com.myapp.domain.Optuzeni;
import pravna.com.myapp.repository.OptuzeniRepository;

/**
 * Service Implementation for managing {@link Optuzeni}.
 */
@Service
public class OptuzeniService {

    private final Logger log = LoggerFactory.getLogger(OptuzeniService.class);

    private final OptuzeniRepository optuzeniRepository;

    public OptuzeniService(OptuzeniRepository optuzeniRepository) {
        this.optuzeniRepository = optuzeniRepository;
    }

    /**
     * Save a optuzeni.
     *
     * @param optuzeni the entity to save.
     * @return the persisted entity.
     */
    public Optuzeni save(Optuzeni optuzeni) {
        log.debug("Request to save Optuzeni : {}", optuzeni);
        return optuzeniRepository.save(optuzeni);
    }

    /**
     * Update a optuzeni.
     *
     * @param optuzeni the entity to save.
     * @return the persisted entity.
     */
    public Optuzeni update(Optuzeni optuzeni) {
        log.debug("Request to update Optuzeni : {}", optuzeni);
        return optuzeniRepository.save(optuzeni);
    }

    /**
     * Partially update a optuzeni.
     *
     * @param optuzeni the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Optuzeni> partialUpdate(Optuzeni optuzeni) {
        log.debug("Request to partially update Optuzeni : {}", optuzeni);

        return optuzeniRepository
            .findById(optuzeni.getId())
            .map(existingOptuzeni -> {
                if (optuzeni.getIme() != null) {
                    existingOptuzeni.setIme(optuzeni.getIme());
                }
                if (optuzeni.getJmbg() != null) {
                    existingOptuzeni.setJmbg(optuzeni.getJmbg());
                }
                if (optuzeni.getImeOca() != null) {
                    existingOptuzeni.setImeOca(optuzeni.getImeOca());
                }
                if (optuzeni.getImeMajke() != null) {
                    existingOptuzeni.setImeMajke(optuzeni.getImeMajke());
                }
                if (optuzeni.getPol() != null) {
                    existingOptuzeni.setPol(optuzeni.getPol());
                }
                if (optuzeni.getDatumRodjenja() != null) {
                    existingOptuzeni.setDatumRodjenja(optuzeni.getDatumRodjenja());
                }
                if (optuzeni.getMestoRodjenja() != null) {
                    existingOptuzeni.setMestoRodjenja(optuzeni.getMestoRodjenja());
                }
                if (optuzeni.getDrzavaRodjenja() != null) {
                    existingOptuzeni.setDrzavaRodjenja(optuzeni.getDrzavaRodjenja());
                }
                if (optuzeni.getPrebivaliste() != null) {
                    existingOptuzeni.setPrebivaliste(optuzeni.getPrebivaliste());
                }
                if (optuzeni.getBracniStatus() != null) {
                    existingOptuzeni.setBracniStatus(optuzeni.getBracniStatus());
                }
                if (optuzeni.getBrojDece() != null) {
                    existingOptuzeni.setBrojDece(optuzeni.getBrojDece());
                }
                if (optuzeni.getBrojMaloletneDece() != null) {
                    existingOptuzeni.setBrojMaloletneDece(optuzeni.getBrojMaloletneDece());
                }
                if (optuzeni.getImovinskoStanje() != null) {
                    existingOptuzeni.setImovinskoStanje(optuzeni.getImovinskoStanje());
                }
                if (optuzeni.getObrazovanje() != null) {
                    existingOptuzeni.setObrazovanje(optuzeni.getObrazovanje());
                }
                if (optuzeni.getZaposlenje() != null) {
                    existingOptuzeni.setZaposlenje(optuzeni.getZaposlenje());
                }
                if (optuzeni.getMestoZaposlenja() != null) {
                    existingOptuzeni.setMestoZaposlenja(optuzeni.getMestoZaposlenja());
                }

                return existingOptuzeni;
            })
            .map(optuzeniRepository::save);
    }

    /**
     * Get all the optuzenis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<Optuzeni> findAll(Pageable pageable) {
        log.debug("Request to get all Optuzenis");
        return optuzeniRepository.findAll(pageable);
    }

    /**
     * Get one optuzeni by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<Optuzeni> findOne(String id) {
        log.debug("Request to get Optuzeni : {}", id);
        return optuzeniRepository.findById(id);
    }

    /**
     * Delete the optuzeni by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Optuzeni : {}", id);
        optuzeniRepository.deleteById(id);
    }
}
