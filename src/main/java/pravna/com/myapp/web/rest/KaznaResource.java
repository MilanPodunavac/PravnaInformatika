package pravna.com.myapp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pravna.com.myapp.domain.Kazna;
import pravna.com.myapp.repository.KaznaRepository;
import pravna.com.myapp.service.KaznaService;
import pravna.com.myapp.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link pravna.com.myapp.domain.Kazna}.
 */
@RestController
@RequestMapping("/api")
public class KaznaResource {

    private final Logger log = LoggerFactory.getLogger(KaznaResource.class);

    private static final String ENTITY_NAME = "kazna";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KaznaService kaznaService;

    private final KaznaRepository kaznaRepository;

    public KaznaResource(KaznaService kaznaService, KaznaRepository kaznaRepository) {
        this.kaznaService = kaznaService;
        this.kaznaRepository = kaznaRepository;
    }

    /**
     * {@code POST  /kaznas} : Create a new kazna.
     *
     * @param kazna the kazna to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new kazna, or with status {@code 400 (Bad Request)} if the kazna has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/kaznas")
    public ResponseEntity<Kazna> createKazna(@Valid @RequestBody Kazna kazna) throws URISyntaxException {
        log.debug("REST request to save Kazna : {}", kazna);
        if (kazna.getId() != null) {
            throw new BadRequestAlertException("A new kazna cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Kazna result = kaznaService.save(kazna);
        return ResponseEntity
            .created(new URI("/api/kaznas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /kaznas/:id} : Updates an existing kazna.
     *
     * @param id the id of the kazna to save.
     * @param kazna the kazna to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated kazna,
     * or with status {@code 400 (Bad Request)} if the kazna is not valid,
     * or with status {@code 500 (Internal Server Error)} if the kazna couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/kaznas/{id}")
    public ResponseEntity<Kazna> updateKazna(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody Kazna kazna
    ) throws URISyntaxException {
        log.debug("REST request to update Kazna : {}, {}", id, kazna);
        if (kazna.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, kazna.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!kaznaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Kazna result = kaznaService.update(kazna);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, kazna.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /kaznas/:id} : Partial updates given fields of an existing kazna, field will ignore if it is null
     *
     * @param id the id of the kazna to save.
     * @param kazna the kazna to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated kazna,
     * or with status {@code 400 (Bad Request)} if the kazna is not valid,
     * or with status {@code 404 (Not Found)} if the kazna is not found,
     * or with status {@code 500 (Internal Server Error)} if the kazna couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/kaznas/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Kazna> partialUpdateKazna(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody Kazna kazna
    ) throws URISyntaxException {
        log.debug("REST request to partial update Kazna partially : {}, {}", id, kazna);
        if (kazna.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, kazna.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!kaznaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Kazna> result = kaznaService.partialUpdate(kazna);

        return ResponseUtil.wrapOrNotFound(result, HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, kazna.getId()));
    }

    /**
     * {@code GET  /kaznas} : get all the kaznas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of kaznas in body.
     */
    @GetMapping("/kaznas")
    public List<Kazna> getAllKaznas() {
        log.debug("REST request to get all Kaznas");
        return kaznaService.findAll();
    }

    /**
     * {@code GET  /kaznas/:id} : get the "id" kazna.
     *
     * @param id the id of the kazna to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the kazna, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/kaznas/{id}")
    public ResponseEntity<Kazna> getKazna(@PathVariable String id) {
        log.debug("REST request to get Kazna : {}", id);
        Optional<Kazna> kazna = kaznaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(kazna);
    }

    /**
     * {@code DELETE  /kaznas/:id} : delete the "id" kazna.
     *
     * @param id the id of the kazna to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/kaznas/{id}")
    public ResponseEntity<Void> deleteKazna(@PathVariable String id) {
        log.debug("REST request to delete Kazna : {}", id);
        kaznaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
