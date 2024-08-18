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
import pravna.com.myapp.repository.PovredaRepository;
import pravna.com.myapp.service.PovredaService;
import pravna.com.myapp.service.dto.PovredaDTO;
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
     * @param povredaDTO the povredaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new povredaDTO, or with status {@code 400 (Bad Request)} if the povreda has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/povredas")
    public ResponseEntity<PovredaDTO> createPovreda(@Valid @RequestBody PovredaDTO povredaDTO) throws URISyntaxException {
        log.debug("REST request to save Povreda : {}", povredaDTO);
        if (povredaDTO.getId() != null) {
            throw new BadRequestAlertException("A new povreda cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PovredaDTO result = povredaService.save(povredaDTO);
        return ResponseEntity
            .created(new URI("/api/povredas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /povredas/:id} : Updates an existing povreda.
     *
     * @param id the id of the povredaDTO to save.
     * @param povredaDTO the povredaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated povredaDTO,
     * or with status {@code 400 (Bad Request)} if the povredaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the povredaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/povredas/{id}")
    public ResponseEntity<PovredaDTO> updatePovreda(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody PovredaDTO povredaDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Povreda : {}, {}", id, povredaDTO);
        if (povredaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, povredaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!povredaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PovredaDTO result = povredaService.update(povredaDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, povredaDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /povredas/:id} : Partial updates given fields of an existing povreda, field will ignore if it is null
     *
     * @param id the id of the povredaDTO to save.
     * @param povredaDTO the povredaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated povredaDTO,
     * or with status {@code 400 (Bad Request)} if the povredaDTO is not valid,
     * or with status {@code 404 (Not Found)} if the povredaDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the povredaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/povredas/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PovredaDTO> partialUpdatePovreda(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody PovredaDTO povredaDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Povreda partially : {}, {}", id, povredaDTO);
        if (povredaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, povredaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!povredaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PovredaDTO> result = povredaService.partialUpdate(povredaDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, povredaDTO.getId())
        );
    }

    /**
     * {@code GET  /povredas} : get all the povredas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of povredas in body.
     */
    @GetMapping("/povredas")
    public List<PovredaDTO> getAllPovredas() {
        log.debug("REST request to get all Povredas");
        return povredaService.findAll();
    }

    /**
     * {@code GET  /povredas/:id} : get the "id" povreda.
     *
     * @param id the id of the povredaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the povredaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/povredas/{id}")
    public ResponseEntity<PovredaDTO> getPovreda(@PathVariable String id) {
        log.debug("REST request to get Povreda : {}", id);
        Optional<PovredaDTO> povredaDTO = povredaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(povredaDTO);
    }

    /**
     * {@code DELETE  /povredas/:id} : delete the "id" povreda.
     *
     * @param id the id of the povredaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/povredas/{id}")
    public ResponseEntity<Void> deletePovreda(@PathVariable String id) {
        log.debug("REST request to delete Povreda : {}", id);
        povredaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
