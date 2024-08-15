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
import pravna.com.myapp.domain.Osoba;
import pravna.com.myapp.repository.OsobaRepository;
import pravna.com.myapp.service.OsobaService;
import pravna.com.myapp.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link pravna.com.myapp.domain.Osoba}.
 */
@RestController
@RequestMapping("/api")
public class OsobaResource {

    private final Logger log = LoggerFactory.getLogger(OsobaResource.class);

    private static final String ENTITY_NAME = "osoba";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OsobaService osobaService;

    private final OsobaRepository osobaRepository;

    public OsobaResource(OsobaService osobaService, OsobaRepository osobaRepository) {
        this.osobaService = osobaService;
        this.osobaRepository = osobaRepository;
    }

    /**
     * {@code POST  /osobas} : Create a new osoba.
     *
     * @param osoba the osoba to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new osoba, or with status {@code 400 (Bad Request)} if the osoba has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/osobas")
    public ResponseEntity<Osoba> createOsoba(@Valid @RequestBody Osoba osoba) throws URISyntaxException {
        log.debug("REST request to save Osoba : {}", osoba);
        if (osoba.getId() != null) {
            throw new BadRequestAlertException("A new osoba cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Osoba result = osobaService.save(osoba);
        return ResponseEntity
            .created(new URI("/api/osobas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /osobas/:id} : Updates an existing osoba.
     *
     * @param id the id of the osoba to save.
     * @param osoba the osoba to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated osoba,
     * or with status {@code 400 (Bad Request)} if the osoba is not valid,
     * or with status {@code 500 (Internal Server Error)} if the osoba couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/osobas/{id}")
    public ResponseEntity<Osoba> updateOsoba(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody Osoba osoba
    ) throws URISyntaxException {
        log.debug("REST request to update Osoba : {}, {}", id, osoba);
        if (osoba.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, osoba.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!osobaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Osoba result = osobaService.update(osoba);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, osoba.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /osobas/:id} : Partial updates given fields of an existing osoba, field will ignore if it is null
     *
     * @param id the id of the osoba to save.
     * @param osoba the osoba to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated osoba,
     * or with status {@code 400 (Bad Request)} if the osoba is not valid,
     * or with status {@code 404 (Not Found)} if the osoba is not found,
     * or with status {@code 500 (Internal Server Error)} if the osoba couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/osobas/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Osoba> partialUpdateOsoba(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody Osoba osoba
    ) throws URISyntaxException {
        log.debug("REST request to partial update Osoba partially : {}, {}", id, osoba);
        if (osoba.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, osoba.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!osobaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Osoba> result = osobaService.partialUpdate(osoba);

        return ResponseUtil.wrapOrNotFound(result, HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, osoba.getId()));
    }

    /**
     * {@code GET  /osobas} : get all the osobas.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of osobas in body.
     */
    @GetMapping("/osobas")
    public ResponseEntity<List<Osoba>> getAllOsobas(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        @RequestParam(required = false, defaultValue = "false") boolean eagerload
    ) {
        log.debug("REST request to get a page of Osobas");
        Page<Osoba> page;
        if (eagerload) {
            page = osobaService.findAllWithEagerRelationships(pageable);
        } else {
            page = osobaService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /osobas/:id} : get the "id" osoba.
     *
     * @param id the id of the osoba to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the osoba, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/osobas/{id}")
    public ResponseEntity<Osoba> getOsoba(@PathVariable String id) {
        log.debug("REST request to get Osoba : {}", id);
        Optional<Osoba> osoba = osobaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(osoba);
    }

    /**
     * {@code DELETE  /osobas/:id} : delete the "id" osoba.
     *
     * @param id the id of the osoba to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/osobas/{id}")
    public ResponseEntity<Void> deleteOsoba(@PathVariable String id) {
        log.debug("REST request to delete Osoba : {}", id);
        osobaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
