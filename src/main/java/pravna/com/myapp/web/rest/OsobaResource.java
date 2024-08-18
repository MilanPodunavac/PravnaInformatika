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
import pravna.com.myapp.repository.OsobaRepository;
import pravna.com.myapp.service.OsobaService;
import pravna.com.myapp.service.dto.OsobaDTO;
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
     * @param osobaDTO the osobaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new osobaDTO, or with status {@code 400 (Bad Request)} if the osoba has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/osobas")
    public ResponseEntity<OsobaDTO> createOsoba(@Valid @RequestBody OsobaDTO osobaDTO) throws URISyntaxException {
        log.debug("REST request to save Osoba : {}", osobaDTO);
        if (osobaDTO.getId() != null) {
            throw new BadRequestAlertException("A new osoba cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OsobaDTO result = osobaService.save(osobaDTO);
        return ResponseEntity
            .created(new URI("/api/osobas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /osobas/:id} : Updates an existing osoba.
     *
     * @param id the id of the osobaDTO to save.
     * @param osobaDTO the osobaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated osobaDTO,
     * or with status {@code 400 (Bad Request)} if the osobaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the osobaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/osobas/{id}")
    public ResponseEntity<OsobaDTO> updateOsoba(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody OsobaDTO osobaDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Osoba : {}, {}", id, osobaDTO);
        if (osobaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, osobaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!osobaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        OsobaDTO result = osobaService.update(osobaDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, osobaDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /osobas/:id} : Partial updates given fields of an existing osoba, field will ignore if it is null
     *
     * @param id the id of the osobaDTO to save.
     * @param osobaDTO the osobaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated osobaDTO,
     * or with status {@code 400 (Bad Request)} if the osobaDTO is not valid,
     * or with status {@code 404 (Not Found)} if the osobaDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the osobaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/osobas/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OsobaDTO> partialUpdateOsoba(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody OsobaDTO osobaDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Osoba partially : {}, {}", id, osobaDTO);
        if (osobaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, osobaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!osobaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OsobaDTO> result = osobaService.partialUpdate(osobaDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, osobaDTO.getId())
        );
    }

    /**
     * {@code GET  /osobas} : get all the osobas.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of osobas in body.
     */
    @GetMapping("/osobas")
    public ResponseEntity<List<OsobaDTO>> getAllOsobas(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Osobas");
        Page<OsobaDTO> page = osobaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /osobas/:id} : get the "id" osoba.
     *
     * @param id the id of the osobaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the osobaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/osobas/{id}")
    public ResponseEntity<OsobaDTO> getOsoba(@PathVariable String id) {
        log.debug("REST request to get Osoba : {}", id);
        Optional<OsobaDTO> osobaDTO = osobaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(osobaDTO);
    }

    /**
     * {@code DELETE  /osobas/:id} : delete the "id" osoba.
     *
     * @param id the id of the osobaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/osobas/{id}")
    public ResponseEntity<Void> deleteOsoba(@PathVariable String id) {
        log.debug("REST request to delete Osoba : {}", id);
        osobaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
