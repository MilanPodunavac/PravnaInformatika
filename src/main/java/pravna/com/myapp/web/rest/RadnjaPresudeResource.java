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
import pravna.com.myapp.domain.RadnjaPresude;
import pravna.com.myapp.repository.RadnjaPresudeRepository;
import pravna.com.myapp.service.RadnjaPresudeService;
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
     * @param radnjaPresude the radnjaPresude to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new radnjaPresude, or with status {@code 400 (Bad Request)} if the radnjaPresude has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/radnja-presudes")
    public ResponseEntity<RadnjaPresude> createRadnjaPresude(@Valid @RequestBody RadnjaPresude radnjaPresude) throws URISyntaxException {
        log.debug("REST request to save RadnjaPresude : {}", radnjaPresude);
        if (radnjaPresude.getId() != null) {
            throw new BadRequestAlertException("A new radnjaPresude cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RadnjaPresude result = radnjaPresudeService.save(radnjaPresude);
        return ResponseEntity
            .created(new URI("/api/radnja-presudes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /radnja-presudes/:id} : Updates an existing radnjaPresude.
     *
     * @param id the id of the radnjaPresude to save.
     * @param radnjaPresude the radnjaPresude to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated radnjaPresude,
     * or with status {@code 400 (Bad Request)} if the radnjaPresude is not valid,
     * or with status {@code 500 (Internal Server Error)} if the radnjaPresude couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/radnja-presudes/{id}")
    public ResponseEntity<RadnjaPresude> updateRadnjaPresude(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody RadnjaPresude radnjaPresude
    ) throws URISyntaxException {
        log.debug("REST request to update RadnjaPresude : {}, {}", id, radnjaPresude);
        if (radnjaPresude.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, radnjaPresude.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!radnjaPresudeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RadnjaPresude result = radnjaPresudeService.update(radnjaPresude);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, radnjaPresude.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /radnja-presudes/:id} : Partial updates given fields of an existing radnjaPresude, field will ignore if it is null
     *
     * @param id the id of the radnjaPresude to save.
     * @param radnjaPresude the radnjaPresude to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated radnjaPresude,
     * or with status {@code 400 (Bad Request)} if the radnjaPresude is not valid,
     * or with status {@code 404 (Not Found)} if the radnjaPresude is not found,
     * or with status {@code 500 (Internal Server Error)} if the radnjaPresude couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/radnja-presudes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RadnjaPresude> partialUpdateRadnjaPresude(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody RadnjaPresude radnjaPresude
    ) throws URISyntaxException {
        log.debug("REST request to partial update RadnjaPresude partially : {}, {}", id, radnjaPresude);
        if (radnjaPresude.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, radnjaPresude.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!radnjaPresudeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RadnjaPresude> result = radnjaPresudeService.partialUpdate(radnjaPresude);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, radnjaPresude.getId())
        );
    }

    /**
     * {@code GET  /radnja-presudes} : get all the radnjaPresudes.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of radnjaPresudes in body.
     */
    @GetMapping("/radnja-presudes")
    public List<RadnjaPresude> getAllRadnjaPresudes(@RequestParam(required = false) String filter) {
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
     * @param id the id of the radnjaPresude to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the radnjaPresude, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/radnja-presudes/{id}")
    public ResponseEntity<RadnjaPresude> getRadnjaPresude(@PathVariable String id) {
        log.debug("REST request to get RadnjaPresude : {}", id);
        Optional<RadnjaPresude> radnjaPresude = radnjaPresudeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(radnjaPresude);
    }

    /**
     * {@code DELETE  /radnja-presudes/:id} : delete the "id" radnjaPresude.
     *
     * @param id the id of the radnjaPresude to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/radnja-presudes/{id}")
    public ResponseEntity<Void> deleteRadnjaPresude(@PathVariable String id) {
        log.debug("REST request to delete RadnjaPresude : {}", id);
        radnjaPresudeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
