package pravna.com.myapp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pravna.com.myapp.domain.RadnjaPresude;
import pravna.com.myapp.repository.RadnjaPresudeRepository;

/**
 * Service Implementation for managing {@link RadnjaPresude}.
 */
@Service
public class RadnjaPresudeService {

    private final Logger log = LoggerFactory.getLogger(RadnjaPresudeService.class);

    private final RadnjaPresudeRepository radnjaPresudeRepository;

    public RadnjaPresudeService(RadnjaPresudeRepository radnjaPresudeRepository) {
        this.radnjaPresudeRepository = radnjaPresudeRepository;
    }

    /**
     * Save a radnjaPresude.
     *
     * @param radnjaPresude the entity to save.
     * @return the persisted entity.
     */
    public RadnjaPresude save(RadnjaPresude radnjaPresude) {
        log.debug("Request to save RadnjaPresude : {}", radnjaPresude);
        return radnjaPresudeRepository.save(radnjaPresude);
    }

    /**
     * Update a radnjaPresude.
     *
     * @param radnjaPresude the entity to save.
     * @return the persisted entity.
     */
    public RadnjaPresude update(RadnjaPresude radnjaPresude) {
        log.debug("Request to update RadnjaPresude : {}", radnjaPresude);
        return radnjaPresudeRepository.save(radnjaPresude);
    }

    /**
     * Partially update a radnjaPresude.
     *
     * @param radnjaPresude the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RadnjaPresude> partialUpdate(RadnjaPresude radnjaPresude) {
        log.debug("Request to partially update RadnjaPresude : {}", radnjaPresude);

        return radnjaPresudeRepository
            .findById(radnjaPresude.getId())
            .map(existingRadnjaPresude -> {
                if (radnjaPresude.getVremeRadnje() != null) {
                    existingRadnjaPresude.setVremeRadnje(radnjaPresude.getVremeRadnje());
                }
                if (radnjaPresude.getMestoRadnje() != null) {
                    existingRadnjaPresude.setMestoRadnje(radnjaPresude.getMestoRadnje());
                }
                if (radnjaPresude.getBitneNapomene() != null) {
                    existingRadnjaPresude.setBitneNapomene(radnjaPresude.getBitneNapomene());
                }
                if (radnjaPresude.getMestoSmrti() != null) {
                    existingRadnjaPresude.setMestoSmrti(radnjaPresude.getMestoSmrti());
                }
                if (radnjaPresude.getVremeSmrti() != null) {
                    existingRadnjaPresude.setVremeSmrti(radnjaPresude.getVremeSmrti());
                }

                return existingRadnjaPresude;
            })
            .map(radnjaPresudeRepository::save);
    }

    /**
     * Get all the radnjaPresudes.
     *
     * @return the list of entities.
     */
    public List<RadnjaPresude> findAll() {
        log.debug("Request to get all RadnjaPresudes");
        return radnjaPresudeRepository.findAll();
    }

    /**
     *  Get all the radnjaPresudes where Presuda is {@code null}.
     *  @return the list of entities.
     */

    public List<RadnjaPresude> findAllWherePresudaIsNull() {
        log.debug("Request to get all radnjaPresudes where Presuda is null");
        return StreamSupport
            .stream(radnjaPresudeRepository.findAll().spliterator(), false)
            .filter(radnjaPresude -> radnjaPresude.getPresuda() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one radnjaPresude by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<RadnjaPresude> findOne(String id) {
        log.debug("Request to get RadnjaPresude : {}", id);
        return radnjaPresudeRepository.findById(id);
    }

    /**
     * Delete the radnjaPresude by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete RadnjaPresude : {}", id);
        radnjaPresudeRepository.deleteById(id);
    }
}
