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
import pravna.com.myapp.domain.Optuzeni;
import pravna.com.myapp.repository.OptuzeniRepository;
import pravna.com.myapp.service.OptuzeniService;
import pravna.com.myapp.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link pravna.com.myapp.domain.Optuzeni}.
 */
@RestController
@RequestMapping("/api")
public class OptuzeniResource {

    private final Logger log = LoggerFactory.getLogger(OptuzeniResource.class);

    private static final String ENTITY_NAME = "optuzeni";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OptuzeniService optuzeniService;

    private final OptuzeniRepository optuzeniRepository;

    public OptuzeniResource(OptuzeniService optuzeniService, OptuzeniRepository optuzeniRepository) {
        this.optuzeniService = optuzeniService;
        this.optuzeniRepository = optuzeniRepository;
    }

    /**
     * {@code POST  /optuzenis} : Create a new optuzeni.
     *
     * @param optuzeni the optuzeni to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new optuzeni, or with status {@code 400 (Bad Request)} if the optuzeni has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/optuzenis")
    public ResponseEntity<Optuzeni> createOptuzeni(@Valid @RequestBody Optuzeni optuzeni) throws URISyntaxException {
        log.debug("REST request to save Optuzeni : {}", optuzeni);
        if (optuzeni.getId() != null) {
            throw new BadRequestAlertException("A new optuzeni cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Optuzeni result = optuzeniService.save(optuzeni);
        return ResponseEntity
            .created(new URI("/api/optuzenis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /optuzenis/:id} : Updates an existing optuzeni.
     *
     * @param id the id of the optuzeni to save.
     * @param optuzeni the optuzeni to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated optuzeni,
     * or with status {@code 400 (Bad Request)} if the optuzeni is not valid,
     * or with status {@code 500 (Internal Server Error)} if the optuzeni couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/optuzenis/{id}")
    public ResponseEntity<Optuzeni> updateOptuzeni(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody Optuzeni optuzeni
    ) throws URISyntaxException {
        log.debug("REST request to update Optuzeni : {}, {}", id, optuzeni);
        if (optuzeni.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, optuzeni.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!optuzeniRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optuzeni result = optuzeniService.update(optuzeni);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, optuzeni.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /optuzenis/:id} : Partial updates given fields of an existing optuzeni, field will ignore if it is null
     *
     * @param id the id of the optuzeni to save.
     * @param optuzeni the optuzeni to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated optuzeni,
     * or with status {@code 400 (Bad Request)} if the optuzeni is not valid,
     * or with status {@code 404 (Not Found)} if the optuzeni is not found,
     * or with status {@code 500 (Internal Server Error)} if the optuzeni couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/optuzenis/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Optuzeni> partialUpdateOptuzeni(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody Optuzeni optuzeni
    ) throws URISyntaxException {
        log.debug("REST request to partial update Optuzeni partially : {}, {}", id, optuzeni);
        if (optuzeni.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, optuzeni.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!optuzeniRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Optuzeni> result = optuzeniService.partialUpdate(optuzeni);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, optuzeni.getId())
        );
    }

    /**
     * {@code GET  /optuzenis} : get all the optuzenis.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of optuzenis in body.
     */
    @GetMapping("/optuzenis")
    public ResponseEntity<List<Optuzeni>> getAllOptuzenis(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Optuzenis");
        Page<Optuzeni> page = optuzeniService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /optuzenis/:id} : get the "id" optuzeni.
     *
     * @param id the id of the optuzeni to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the optuzeni, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/optuzenis/{id}")
    public ResponseEntity<Optuzeni> getOptuzeni(@PathVariable String id) {
        log.debug("REST request to get Optuzeni : {}", id);
        Optional<Optuzeni> optuzeni = optuzeniService.findOne(id);
        return ResponseUtil.wrapOrNotFound(optuzeni);
    }

    /**
     * {@code DELETE  /optuzenis/:id} : delete the "id" optuzeni.
     *
     * @param id the id of the optuzeni to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/optuzenis/{id}")
    public ResponseEntity<Void> deleteOptuzeni(@PathVariable String id) {
        log.debug("REST request to delete Optuzeni : {}", id);
        optuzeniService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
