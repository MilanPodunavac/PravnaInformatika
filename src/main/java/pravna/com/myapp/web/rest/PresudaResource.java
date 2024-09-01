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
import pravna.com.myapp.repository.PresudaRepository;
import pravna.com.myapp.service.PresudaService;
import pravna.com.myapp.service.dto.PresudaDTO;
import pravna.com.myapp.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link pravna.com.myapp.domain.Presuda}.
 */
@RestController
@RequestMapping("/api")
public class PresudaResource {

    private final Logger log = LoggerFactory.getLogger(PresudaResource.class);

    private static final String ENTITY_NAME = "presuda";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PresudaService presudaService;

    private final PresudaRepository presudaRepository;

    public PresudaResource(PresudaService presudaService, PresudaRepository presudaRepository) {
        this.presudaService = presudaService;
        this.presudaRepository = presudaRepository;
    }

    /**
     * {@code POST  /presudas} : Create a new presuda.
     *
     * @param presudaDTO the presudaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new presudaDTO, or with status {@code 400 (Bad Request)} if the presuda has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/presudas")
    public ResponseEntity<PresudaDTO> createPresuda(@Valid @RequestBody PresudaDTO presudaDTO) throws URISyntaxException {
        log.debug("REST request to save Presuda : {}", presudaDTO);
        if (presudaDTO.getId() != null) {
            throw new BadRequestAlertException("A new presuda cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PresudaDTO result = presudaService.save(presudaDTO);
        return ResponseEntity
            .created(new URI("/api/presudas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /presudas/:id} : Updates an existing presuda.
     *
     * @param id the id of the presudaDTO to save.
     * @param presudaDTO the presudaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated presudaDTO,
     * or with status {@code 400 (Bad Request)} if the presudaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the presudaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/presudas/{id}")
    public ResponseEntity<PresudaDTO> updatePresuda(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody PresudaDTO presudaDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Presuda : {}, {}", id, presudaDTO);
        if (presudaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, presudaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!presudaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PresudaDTO result = presudaService.update(presudaDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, presudaDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /presudas/:id} : Partial updates given fields of an existing presuda, field will ignore if it is null
     *
     * @param id the id of the presudaDTO to save.
     * @param presudaDTO the presudaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated presudaDTO,
     * or with status {@code 400 (Bad Request)} if the presudaDTO is not valid,
     * or with status {@code 404 (Not Found)} if the presudaDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the presudaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/presudas/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PresudaDTO> partialUpdatePresuda(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody PresudaDTO presudaDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Presuda partially : {}, {}", id, presudaDTO);
        if (presudaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, presudaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!presudaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PresudaDTO> result = presudaService.partialUpdate(presudaDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, presudaDTO.getId())
        );
    }

    /**
     * {@code GET  /presudas} : get all the presudas.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of presudas in body.
     */
    @GetMapping("/presudas")
    public ResponseEntity<List<PresudaDTO>> getAllPresudas(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        @RequestParam(required = false, defaultValue = "false") boolean eagerload
    ) {
        log.debug("REST request to get a page of Presudas");
        Page<PresudaDTO> page;
        if (eagerload) {
            page = presudaService.findAllWithEagerRelationships(pageable);
        } else {
            page = presudaService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /presudas/:id} : get the "id" presuda.
     *
     * @param id the id of the presudaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the presudaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/presudas/{id}")
    public ResponseEntity<PresudaDTO> getPresuda(@PathVariable String id) {
        log.debug("REST request to get Presuda : {}", id);
        Optional<PresudaDTO> presudaDTO = presudaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(presudaDTO);
    }

    /**
     * {@code DELETE  /presudas/:id} : delete the "id" presuda.
     *
     * @param id the id of the presudaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/presudas/{id}")
    public ResponseEntity<Void> deletePresuda(@PathVariable String id) {
        log.debug("REST request to delete Presuda : {}", id);
        presudaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
