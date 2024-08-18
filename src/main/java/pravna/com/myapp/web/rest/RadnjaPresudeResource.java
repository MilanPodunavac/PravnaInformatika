package pravna.com.myapp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pravna.com.myapp.repository.RadnjaPresudeRepository;
import pravna.com.myapp.service.RadnjaPresudeService;
import pravna.com.myapp.service.dto.RadnjaPresudeDTO;
import pravna.com.myapp.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link pravna.com.myapp.domain.RadnjaPresude}.
 */
@RestController
@RequestMapping("/api")
public class RadnjaPresudeResource {

    private final Logger log = LoggerFactory.getLogger(RadnjaPresudeResource.class);

    private static final String ENTITY_NAME = "radnjaPresude";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RadnjaPresudeService radnjaPresudeService;

    private final RadnjaPresudeRepository radnjaPresudeRepository;

    public RadnjaPresudeResource(RadnjaPresudeService radnjaPresudeService, RadnjaPresudeRepository radnjaPresudeRepository) {
        this.radnjaPresudeService = radnjaPresudeService;
        this.radnjaPresudeRepository = radnjaPresudeRepository;
    }

    /**
     * {@code POST  /radnja-presudes} : Create a new radnjaPresude.
     *
     * @param radnjaPresudeDTO the radnjaPresudeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new radnjaPresudeDTO, or with status {@code 400 (Bad Request)} if the radnjaPresude has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/radnja-presudes")
    public ResponseEntity<RadnjaPresudeDTO> createRadnjaPresude(@Valid @RequestBody RadnjaPresudeDTO radnjaPresudeDTO)
        throws URISyntaxException {
        log.debug("REST request to save RadnjaPresude : {}", radnjaPresudeDTO);
        if (radnjaPresudeDTO.getId() != null) {
            throw new BadRequestAlertException("A new radnjaPresude cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RadnjaPresudeDTO result = radnjaPresudeService.save(radnjaPresudeDTO);
        return ResponseEntity
            .created(new URI("/api/radnja-presudes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /radnja-presudes/:id} : Updates an existing radnjaPresude.
     *
     * @param id the id of the radnjaPresudeDTO to save.
     * @param radnjaPresudeDTO the radnjaPresudeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated radnjaPresudeDTO,
     * or with status {@code 400 (Bad Request)} if the radnjaPresudeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the radnjaPresudeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/radnja-presudes/{id}")
    public ResponseEntity<RadnjaPresudeDTO> updateRadnjaPresude(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody RadnjaPresudeDTO radnjaPresudeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update RadnjaPresude : {}, {}", id, radnjaPresudeDTO);
        if (radnjaPresudeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, radnjaPresudeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!radnjaPresudeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RadnjaPresudeDTO result = radnjaPresudeService.update(radnjaPresudeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, radnjaPresudeDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /radnja-presudes/:id} : Partial updates given fields of an existing radnjaPresude, field will ignore if it is null
     *
     * @param id the id of the radnjaPresudeDTO to save.
     * @param radnjaPresudeDTO the radnjaPresudeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated radnjaPresudeDTO,
     * or with status {@code 400 (Bad Request)} if the radnjaPresudeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the radnjaPresudeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the radnjaPresudeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/radnja-presudes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RadnjaPresudeDTO> partialUpdateRadnjaPresude(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody RadnjaPresudeDTO radnjaPresudeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update RadnjaPresude partially : {}, {}", id, radnjaPresudeDTO);
        if (radnjaPresudeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, radnjaPresudeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!radnjaPresudeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RadnjaPresudeDTO> result = radnjaPresudeService.partialUpdate(radnjaPresudeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, radnjaPresudeDTO.getId())
        );
    }

    /**
     * {@code GET  /radnja-presudes} : get all the radnjaPresudes.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of radnjaPresudes in body.
     */
    @GetMapping("/radnja-presudes")
    public List<RadnjaPresudeDTO> getAllRadnjaPresudes(@RequestParam(required = false) String filter) {
        if ("presuda-is-null".equals(filter)) {
            log.debug("REST request to get all RadnjaPresudes where presuda is null");
            return radnjaPresudeService.findAllWherePresudaIsNull();
        }
        log.debug("REST request to get all RadnjaPresudes");
        return radnjaPresudeService.findAll();
    }

    /**
     * {@code GET  /radnja-presudes/:id} : get the "id" radnjaPresude.
     *
     * @param id the id of the radnjaPresudeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the radnjaPresudeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/radnja-presudes/{id}")
    public ResponseEntity<RadnjaPresudeDTO> getRadnjaPresude(@PathVariable String id) {
        log.debug("REST request to get RadnjaPresude : {}", id);
        Optional<RadnjaPresudeDTO> radnjaPresudeDTO = radnjaPresudeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(radnjaPresudeDTO);
    }

    /**
     * {@code DELETE  /radnja-presudes/:id} : delete the "id" radnjaPresude.
     *
     * @param id the id of the radnjaPresudeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/radnja-presudes/{id}")
    public ResponseEntity<Void> deleteRadnjaPresude(@PathVariable String id) {
        log.debug("REST request to delete RadnjaPresude : {}", id);
        radnjaPresudeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
