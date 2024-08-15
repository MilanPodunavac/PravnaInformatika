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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pravna.com.myapp.domain.Zakon;
import pravna.com.myapp.repository.ZakonRepository;
import pravna.com.myapp.service.ZakonService;
import pravna.com.myapp.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link pravna.com.myapp.domain.Zakon}.
 */
@RestController
@RequestMapping("/api")
public class ZakonResource {

    private final Logger log = LoggerFactory.getLogger(ZakonResource.class);

    private static final String ENTITY_NAME = "zakon";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ZakonService zakonService;

    private final ZakonRepository zakonRepository;

    public ZakonResource(ZakonService zakonService, ZakonRepository zakonRepository) {
        this.zakonService = zakonService;
        this.zakonRepository = zakonRepository;
    }

    /**
     * {@code POST  /zakons} : Create a new zakon.
     *
     * @param zakon the zakon to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new zakon, or with status {@code 400 (Bad Request)} if the zakon has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/zakons")
    public ResponseEntity<Zakon> createZakon(@Valid @RequestBody Zakon zakon) throws URISyntaxException {
        log.debug("REST request to save Zakon : {}", zakon);
        if (zakon.getId() != null) {
            throw new BadRequestAlertException("A new zakon cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Zakon result = zakonService.save(zakon);
        return ResponseEntity
            .created(new URI("/api/zakons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /zakons/:id} : Updates an existing zakon.
     *
     * @param id the id of the zakon to save.
     * @param zakon the zakon to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated zakon,
     * or with status {@code 400 (Bad Request)} if the zakon is not valid,
     * or with status {@code 500 (Internal Server Error)} if the zakon couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/zakons/{id}")
    public ResponseEntity<Zakon> updateZakon(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody Zakon zakon
    ) throws URISyntaxException {
        log.debug("REST request to update Zakon : {}, {}", id, zakon);
        if (zakon.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, zakon.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!zakonRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Zakon result = zakonService.update(zakon);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, zakon.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /zakons/:id} : Partial updates given fields of an existing zakon, field will ignore if it is null
     *
     * @param id the id of the zakon to save.
     * @param zakon the zakon to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated zakon,
     * or with status {@code 400 (Bad Request)} if the zakon is not valid,
     * or with status {@code 404 (Not Found)} if the zakon is not found,
     * or with status {@code 500 (Internal Server Error)} if the zakon couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/zakons/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Zakon> partialUpdateZakon(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody Zakon zakon
    ) throws URISyntaxException {
        log.debug("REST request to partial update Zakon partially : {}, {}", id, zakon);
        if (zakon.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, zakon.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!zakonRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Zakon> result = zakonService.partialUpdate(zakon);

        return ResponseUtil.wrapOrNotFound(result, HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, zakon.getId()));
    }

    /**
     * {@code GET  /zakons} : get all the zakons.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of zakons in body.
     */
    @GetMapping("/zakons")
    public ResponseEntity<List<Zakon>> getAllZakons(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Zakons");
        Page<Zakon> page = zakonService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /zakons/:id} : get the "id" zakon.
     *
     * @param id the id of the zakon to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the zakon, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/zakons/{id}")
    public ResponseEntity<Zakon> getZakon(@PathVariable String id) {
        log.debug("REST request to get Zakon : {}", id);
        Optional<Zakon> zakon = zakonService.findOne(id);
        return ResponseUtil.wrapOrNotFound(zakon);
    }

    /**
     * {@code DELETE  /zakons/:id} : delete the "id" zakon.
     *
     * @param id the id of the zakon to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/zakons/{id}")
    public ResponseEntity<Void> deleteZakon(@PathVariable String id) {
        log.debug("REST request to delete Zakon : {}", id);
        zakonService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
