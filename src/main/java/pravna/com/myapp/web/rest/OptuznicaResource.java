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
import pravna.com.myapp.repository.OptuznicaRepository;
import pravna.com.myapp.service.OptuznicaService;
import pravna.com.myapp.service.dto.OptuznicaDTO;
import pravna.com.myapp.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link pravna.com.myapp.domain.Optuznica}.
 */
@RestController
@RequestMapping("/api")
public class OptuznicaResource {

    private final Logger log = LoggerFactory.getLogger(OptuznicaResource.class);

    private static final String ENTITY_NAME = "optuznica";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OptuznicaService optuznicaService;

    private final OptuznicaRepository optuznicaRepository;

    public OptuznicaResource(OptuznicaService optuznicaService, OptuznicaRepository optuznicaRepository) {
        this.optuznicaService = optuznicaService;
        this.optuznicaRepository = optuznicaRepository;
    }

    /**
     * {@code POST  /optuznicas} : Create a new optuznica.
     *
     * @param optuznicaDTO the optuznicaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new optuznicaDTO, or with status {@code 400 (Bad Request)} if the optuznica has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/optuznicas")
    public ResponseEntity<OptuznicaDTO> createOptuznica(@Valid @RequestBody OptuznicaDTO optuznicaDTO) throws URISyntaxException {
        log.debug("REST request to save Optuznica : {}", optuznicaDTO);
        if (optuznicaDTO.getId() != null) {
            throw new BadRequestAlertException("A new optuznica cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OptuznicaDTO result = optuznicaService.save(optuznicaDTO);
        return ResponseEntity
            .created(new URI("/api/optuznicas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /optuznicas/:id} : Updates an existing optuznica.
     *
     * @param id the id of the optuznicaDTO to save.
     * @param optuznicaDTO the optuznicaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated optuznicaDTO,
     * or with status {@code 400 (Bad Request)} if the optuznicaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the optuznicaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/optuznicas/{id}")
    public ResponseEntity<OptuznicaDTO> updateOptuznica(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody OptuznicaDTO optuznicaDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Optuznica : {}, {}", id, optuznicaDTO);
        if (optuznicaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, optuznicaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!optuznicaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        OptuznicaDTO result = optuznicaService.update(optuznicaDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, optuznicaDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /optuznicas/:id} : Partial updates given fields of an existing optuznica, field will ignore if it is null
     *
     * @param id the id of the optuznicaDTO to save.
     * @param optuznicaDTO the optuznicaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated optuznicaDTO,
     * or with status {@code 400 (Bad Request)} if the optuznicaDTO is not valid,
     * or with status {@code 404 (Not Found)} if the optuznicaDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the optuznicaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/optuznicas/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OptuznicaDTO> partialUpdateOptuznica(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody OptuznicaDTO optuznicaDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Optuznica partially : {}, {}", id, optuznicaDTO);
        if (optuznicaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, optuznicaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!optuznicaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OptuznicaDTO> result = optuznicaService.partialUpdate(optuznicaDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, optuznicaDTO.getId())
        );
    }

    /**
     * {@code GET  /optuznicas} : get all the optuznicas.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of optuznicas in body.
     */
    @GetMapping("/optuznicas")
    public List<OptuznicaDTO> getAllOptuznicas(@RequestParam(required = false) String filter) {
        if ("presuda-is-null".equals(filter)) {
            log.debug("REST request to get all Optuznicas where presuda is null");
            return optuznicaService.findAllWherePresudaIsNull();
        }
        log.debug("REST request to get all Optuznicas");
        return optuznicaService.findAll();
    }

    /**
     * {@code GET  /optuznicas/:id} : get the "id" optuznica.
     *
     * @param id the id of the optuznicaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the optuznicaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/optuznicas/{id}")
    public ResponseEntity<OptuznicaDTO> getOptuznica(@PathVariable String id) {
        log.debug("REST request to get Optuznica : {}", id);
        Optional<OptuznicaDTO> optuznicaDTO = optuznicaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(optuznicaDTO);
    }

    /**
     * {@code DELETE  /optuznicas/:id} : delete the "id" optuznica.
     *
     * @param id the id of the optuznicaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/optuznicas/{id}")
    public ResponseEntity<Void> deleteOptuznica(@PathVariable String id) {
        log.debug("REST request to delete Optuznica : {}", id);
        optuznicaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
