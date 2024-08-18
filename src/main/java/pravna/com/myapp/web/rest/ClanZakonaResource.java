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
import pravna.com.myapp.repository.ClanZakonaRepository;
import pravna.com.myapp.service.ClanZakonaService;
import pravna.com.myapp.service.dto.ClanZakonaDTO;
import pravna.com.myapp.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link pravna.com.myapp.domain.ClanZakona}.
 */
@RestController
@RequestMapping("/api")
public class ClanZakonaResource {

    private final Logger log = LoggerFactory.getLogger(ClanZakonaResource.class);

    private static final String ENTITY_NAME = "clanZakona";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClanZakonaService clanZakonaService;

    private final ClanZakonaRepository clanZakonaRepository;

    public ClanZakonaResource(ClanZakonaService clanZakonaService, ClanZakonaRepository clanZakonaRepository) {
        this.clanZakonaService = clanZakonaService;
        this.clanZakonaRepository = clanZakonaRepository;
    }

    /**
     * {@code POST  /clan-zakonas} : Create a new clanZakona.
     *
     * @param clanZakonaDTO the clanZakonaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new clanZakonaDTO, or with status {@code 400 (Bad Request)} if the clanZakona has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/clan-zakonas")
    public ResponseEntity<ClanZakonaDTO> createClanZakona(@Valid @RequestBody ClanZakonaDTO clanZakonaDTO) throws URISyntaxException {
        log.debug("REST request to save ClanZakona : {}", clanZakonaDTO);
        if (clanZakonaDTO.getId() != null) {
            throw new BadRequestAlertException("A new clanZakona cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClanZakonaDTO result = clanZakonaService.save(clanZakonaDTO);
        return ResponseEntity
            .created(new URI("/api/clan-zakonas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /clan-zakonas/:id} : Updates an existing clanZakona.
     *
     * @param id the id of the clanZakonaDTO to save.
     * @param clanZakonaDTO the clanZakonaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated clanZakonaDTO,
     * or with status {@code 400 (Bad Request)} if the clanZakonaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the clanZakonaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/clan-zakonas/{id}")
    public ResponseEntity<ClanZakonaDTO> updateClanZakona(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody ClanZakonaDTO clanZakonaDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ClanZakona : {}, {}", id, clanZakonaDTO);
        if (clanZakonaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, clanZakonaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!clanZakonaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ClanZakonaDTO result = clanZakonaService.update(clanZakonaDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, clanZakonaDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /clan-zakonas/:id} : Partial updates given fields of an existing clanZakona, field will ignore if it is null
     *
     * @param id the id of the clanZakonaDTO to save.
     * @param clanZakonaDTO the clanZakonaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated clanZakonaDTO,
     * or with status {@code 400 (Bad Request)} if the clanZakonaDTO is not valid,
     * or with status {@code 404 (Not Found)} if the clanZakonaDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the clanZakonaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/clan-zakonas/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ClanZakonaDTO> partialUpdateClanZakona(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody ClanZakonaDTO clanZakonaDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ClanZakona partially : {}, {}", id, clanZakonaDTO);
        if (clanZakonaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, clanZakonaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!clanZakonaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ClanZakonaDTO> result = clanZakonaService.partialUpdate(clanZakonaDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, clanZakonaDTO.getId())
        );
    }

    /**
     * {@code GET  /clan-zakonas} : get all the clanZakonas.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of clanZakonas in body.
     */
    @GetMapping("/clan-zakonas")
    public ResponseEntity<List<ClanZakonaDTO>> getAllClanZakonas(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of ClanZakonas");
        Page<ClanZakonaDTO> page = clanZakonaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /clan-zakonas/:id} : get the "id" clanZakona.
     *
     * @param id the id of the clanZakonaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the clanZakonaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/clan-zakonas/{id}")
    public ResponseEntity<ClanZakonaDTO> getClanZakona(@PathVariable String id) {
        log.debug("REST request to get ClanZakona : {}", id);
        Optional<ClanZakonaDTO> clanZakonaDTO = clanZakonaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(clanZakonaDTO);
    }

    /**
     * {@code DELETE  /clan-zakonas/:id} : delete the "id" clanZakona.
     *
     * @param id the id of the clanZakonaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/clan-zakonas/{id}")
    public ResponseEntity<Void> deleteClanZakona(@PathVariable String id) {
        log.debug("REST request to delete ClanZakona : {}", id);
        clanZakonaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
