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
import pravna.com.myapp.domain.Povreda;
import pravna.com.myapp.repository.PovredaRepository;
import pravna.com.myapp.service.PovredaService;
import pravna.com.myapp.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link pravna.com.myapp.domain.Povreda}.
 */
@RestController
@RequestMapping("/api")
public class PovredaResource {

    private final Logger log = LoggerFactory.getLogger(PovredaResource.class);

    private static final String ENTITY_NAME = "povreda";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PovredaService povredaService;

    private final PovredaRepository povredaRepository;

    public PovredaResource(PovredaService povredaService, PovredaRepository povredaRepository) {
        this.povredaService = povredaService;
        this.povredaRepository = povredaRepository;
    }

    /**
     * {@code POST  /povredas} : Create a new povreda.
     *
     * @param povreda the povreda to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new povreda, or with status {@code 400 (Bad Request)} if the povreda has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/povredas")
    public ResponseEntity<Povreda> createPovreda(@Valid @RequestBody Povreda povreda) throws URISyntaxException {
        log.debug("REST request to save Povreda : {}", povreda);
        if (povreda.getId() != null) {
            throw new BadRequestAlertException("A new povreda cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Povreda result = povredaService.save(povreda);
        return ResponseEntity
            .created(new URI("/api/povredas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /povredas/:id} : Updates an existing povreda.
     *
     * @param id the id of the povreda to save.
     * @param povreda the povreda to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated povreda,
     * or with status {@code 400 (Bad Request)} if the povreda is not valid,
     * or with status {@code 500 (Internal Server Error)} if the povreda couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/povredas/{id}")
    public ResponseEntity<Povreda> updatePovreda(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody Povreda povreda
    ) throws URISyntaxException {
        log.debug("REST request to update Povreda : {}, {}", id, povreda);
        if (povreda.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, povreda.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!povredaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Povreda result = povredaService.update(povreda);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, povreda.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /povredas/:id} : Partial updates given fields of an existing povreda, field will ignore if it is null
     *
     * @param id the id of the povreda to save.
     * @param povreda the povreda to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated povreda,
     * or with status {@code 400 (Bad Request)} if the povreda is not valid,
     * or with status {@code 404 (Not Found)} if the povreda is not found,
     * or with status {@code 500 (Internal Server Error)} if the povreda couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/povredas/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Povreda> partialUpdatePovreda(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody Povreda povreda
    ) throws URISyntaxException {
        log.debug("REST request to partial update Povreda partially : {}, {}", id, povreda);
        if (povreda.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, povreda.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!povredaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Povreda> result = povredaService.partialUpdate(povreda);

        return ResponseUtil.wrapOrNotFound(result, HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, povreda.getId()));
    }

    /**
     * {@code GET  /povredas} : get all the povredas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of povredas in body.
     */
    @GetMapping("/povredas")
    public List<Povreda> getAllPovredas() {
        log.debug("REST request to get all Povredas");
        return povredaService.findAll();
    }

    /**
     * {@code GET  /povredas/:id} : get the "id" povreda.
     *
     * @param id the id of the povreda to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the povreda, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/povredas/{id}")
    public ResponseEntity<Povreda> getPovreda(@PathVariable String id) {
        log.debug("REST request to get Povreda : {}", id);
        Optional<Povreda> povreda = povredaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(povreda);
    }

    /**
     * {@code DELETE  /povredas/:id} : delete the "id" povreda.
     *
     * @param id the id of the povreda to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/povredas/{id}")
    public ResponseEntity<Void> deletePovreda(@PathVariable String id) {
        log.debug("REST request to delete Povreda : {}", id);
        povredaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
