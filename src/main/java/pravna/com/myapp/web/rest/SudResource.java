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
import pravna.com.myapp.repository.SudRepository;
import pravna.com.myapp.service.SudService;
import pravna.com.myapp.service.dto.SudDTO;
import pravna.com.myapp.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link pravna.com.myapp.domain.Sud}.
 */
@RestController
@RequestMapping("/api")
public class SudResource {

    private final Logger log = LoggerFactory.getLogger(SudResource.class);

    private static final String ENTITY_NAME = "sud";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SudService sudService;

    private final SudRepository sudRepository;

    public SudResource(SudService sudService, SudRepository sudRepository) {
        this.sudService = sudService;
        this.sudRepository = sudRepository;
    }

    /**
     * {@code POST  /suds} : Create a new sud.
     *
     * @param sudDTO the sudDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sudDTO, or with status {@code 400 (Bad Request)} if the sud has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/suds")
    public ResponseEntity<SudDTO> createSud(@Valid @RequestBody SudDTO sudDTO) throws URISyntaxException {
        log.debug("REST request to save Sud : {}", sudDTO);
        if (sudDTO.getId() != null) {
            throw new BadRequestAlertException("A new sud cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SudDTO result = sudService.save(sudDTO);
        return ResponseEntity
            .created(new URI("/api/suds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /suds/:id} : Updates an existing sud.
     *
     * @param id the id of the sudDTO to save.
     * @param sudDTO the sudDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sudDTO,
     * or with status {@code 400 (Bad Request)} if the sudDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sudDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/suds/{id}")
    public ResponseEntity<SudDTO> updateSud(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody SudDTO sudDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Sud : {}, {}", id, sudDTO);
        if (sudDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sudDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sudRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SudDTO result = sudService.update(sudDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sudDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /suds/:id} : Partial updates given fields of an existing sud, field will ignore if it is null
     *
     * @param id the id of the sudDTO to save.
     * @param sudDTO the sudDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sudDTO,
     * or with status {@code 400 (Bad Request)} if the sudDTO is not valid,
     * or with status {@code 404 (Not Found)} if the sudDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the sudDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/suds/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SudDTO> partialUpdateSud(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody SudDTO sudDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Sud partially : {}, {}", id, sudDTO);
        if (sudDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sudDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sudRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SudDTO> result = sudService.partialUpdate(sudDTO);

        return ResponseUtil.wrapOrNotFound(result, HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sudDTO.getId()));
    }

    /**
     * {@code GET  /suds} : get all the suds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of suds in body.
     */
    @GetMapping("/suds")
    public List<SudDTO> getAllSuds() {
        log.debug("REST request to get all Suds");
        return sudService.findAll();
    }

    /**
     * {@code GET  /suds/:id} : get the "id" sud.
     *
     * @param id the id of the sudDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sudDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/suds/{id}")
    public ResponseEntity<SudDTO> getSud(@PathVariable String id) {
        log.debug("REST request to get Sud : {}", id);
        Optional<SudDTO> sudDTO = sudService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sudDTO);
    }

    /**
     * {@code DELETE  /suds/:id} : delete the "id" sud.
     *
     * @param id the id of the sudDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/suds/{id}")
    public ResponseEntity<Void> deleteSud(@PathVariable String id) {
        log.debug("REST request to delete Sud : {}", id);
        sudService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
