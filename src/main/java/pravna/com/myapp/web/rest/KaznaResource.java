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
import pravna.com.myapp.repository.KaznaRepository;
import pravna.com.myapp.service.KaznaService;
import pravna.com.myapp.service.dto.KaznaDTO;
import pravna.com.myapp.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link pravna.com.myapp.domain.Kazna}.
 */
@RestController
@RequestMapping("/api")
public class KaznaResource {

    private final Logger log = LoggerFactory.getLogger(KaznaResource.class);

    private static final String ENTITY_NAME = "kazna";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KaznaService kaznaService;

    private final KaznaRepository kaznaRepository;

    public KaznaResource(KaznaService kaznaService, KaznaRepository kaznaRepository) {
        this.kaznaService = kaznaService;
        this.kaznaRepository = kaznaRepository;
    }

    /**
     * {@code POST  /kaznas} : Create a new kazna.
     *
     * @param kaznaDTO the kaznaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new kaznaDTO, or with status {@code 400 (Bad Request)} if the kazna has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/kaznas")
    public ResponseEntity<KaznaDTO> createKazna(@Valid @RequestBody KaznaDTO kaznaDTO) throws URISyntaxException {
        log.debug("REST request to save Kazna : {}", kaznaDTO);
        if (kaznaDTO.getId() != null) {
            throw new BadRequestAlertException("A new kazna cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KaznaDTO result = kaznaService.save(kaznaDTO);
        return ResponseEntity
            .created(new URI("/api/kaznas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /kaznas/:id} : Updates an existing kazna.
     *
     * @param id the id of the kaznaDTO to save.
     * @param kaznaDTO the kaznaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated kaznaDTO,
     * or with status {@code 400 (Bad Request)} if the kaznaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the kaznaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/kaznas/{id}")
    public ResponseEntity<KaznaDTO> updateKazna(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody KaznaDTO kaznaDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Kazna : {}, {}", id, kaznaDTO);
        if (kaznaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, kaznaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!kaznaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        KaznaDTO result = kaznaService.update(kaznaDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, kaznaDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /kaznas/:id} : Partial updates given fields of an existing kazna, field will ignore if it is null
     *
     * @param id the id of the kaznaDTO to save.
     * @param kaznaDTO the kaznaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated kaznaDTO,
     * or with status {@code 400 (Bad Request)} if the kaznaDTO is not valid,
     * or with status {@code 404 (Not Found)} if the kaznaDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the kaznaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/kaznas/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<KaznaDTO> partialUpdateKazna(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody KaznaDTO kaznaDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Kazna partially : {}, {}", id, kaznaDTO);
        if (kaznaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, kaznaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!kaznaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<KaznaDTO> result = kaznaService.partialUpdate(kaznaDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, kaznaDTO.getId())
        );
    }

    /**
     * {@code GET  /kaznas} : get all the kaznas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of kaznas in body.
     */
    @GetMapping("/kaznas")
    public List<KaznaDTO> getAllKaznas() {
        log.debug("REST request to get all Kaznas");
        return kaznaService.findAll();
    }

    /**
     * {@code GET  /kaznas/:id} : get the "id" kazna.
     *
     * @param id the id of the kaznaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the kaznaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/kaznas/{id}")
    public ResponseEntity<KaznaDTO> getKazna(@PathVariable String id) {
        log.debug("REST request to get Kazna : {}", id);
        Optional<KaznaDTO> kaznaDTO = kaznaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(kaznaDTO);
    }

    /**
     * {@code DELETE  /kaznas/:id} : delete the "id" kazna.
     *
     * @param id the id of the kaznaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/kaznas/{id}")
    public ResponseEntity<Void> deleteKazna(@PathVariable String id) {
        log.debug("REST request to delete Kazna : {}", id);
        kaznaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
