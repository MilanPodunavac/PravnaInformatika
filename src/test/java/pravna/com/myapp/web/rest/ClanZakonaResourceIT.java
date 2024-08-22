package pravna.com.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import pravna.com.myapp.IntegrationTest;
import pravna.com.myapp.domain.ClanZakona;
import pravna.com.myapp.domain.Zakon;
import pravna.com.myapp.repository.ClanZakonaRepository;
import pravna.com.myapp.service.dto.ClanZakonaDTO;
import pravna.com.myapp.service.mapper.ClanZakonaMapper;

/**
 * Integration tests for the {@link ClanZakonaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ClanZakonaResourceIT {

    private static final Integer DEFAULT_BROJ = 1;
    private static final Integer UPDATED_BROJ = 2;

    private static final Integer DEFAULT_GLAVA = 1;
    private static final Integer UPDATED_GLAVA = 2;

    private static final String DEFAULT_NAZIV = "AAAAAAAAAA";
    private static final String UPDATED_NAZIV = "BBBBBBBBBB";

    private static final String DEFAULT_TEKST = "AAAAAAAAAA";
    private static final String UPDATED_TEKST = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/clan-zakonas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ClanZakonaRepository clanZakonaRepository;

    @Autowired
    private ClanZakonaMapper clanZakonaMapper;

    @Autowired
    private MockMvc restClanZakonaMockMvc;

    private ClanZakona clanZakona;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClanZakona createEntity() {
        ClanZakona clanZakona = new ClanZakona().broj(DEFAULT_BROJ).glava(DEFAULT_GLAVA).naziv(DEFAULT_NAZIV).tekst(DEFAULT_TEKST);
        // Add required entity
        Zakon zakon;
        zakon = ZakonResourceIT.createEntity();
        zakon.setId("fixed-id-for-tests");
        clanZakona.setZakon(zakon);
        return clanZakona;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClanZakona createUpdatedEntity() {
        ClanZakona clanZakona = new ClanZakona().broj(UPDATED_BROJ).glava(UPDATED_GLAVA).naziv(UPDATED_NAZIV).tekst(UPDATED_TEKST);
        // Add required entity
        Zakon zakon;
        zakon = ZakonResourceIT.createUpdatedEntity();
        zakon.setId("fixed-id-for-tests");
        clanZakona.setZakon(zakon);
        return clanZakona;
    }

    @BeforeEach
    public void initTest() {
        clanZakonaRepository.deleteAll();
        clanZakona = createEntity();
    }

    @Test
    void createClanZakona() throws Exception {
        int databaseSizeBeforeCreate = clanZakonaRepository.findAll().size();
        // Create the ClanZakona
        ClanZakonaDTO clanZakonaDTO = clanZakonaMapper.toDto(clanZakona);
        restClanZakonaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clanZakonaDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ClanZakona in the database
        List<ClanZakona> clanZakonaList = clanZakonaRepository.findAll();
        assertThat(clanZakonaList).hasSize(databaseSizeBeforeCreate + 1);
        ClanZakona testClanZakona = clanZakonaList.get(clanZakonaList.size() - 1);
        assertThat(testClanZakona.getBroj()).isEqualTo(DEFAULT_BROJ);
        assertThat(testClanZakona.getGlava()).isEqualTo(DEFAULT_GLAVA);
        assertThat(testClanZakona.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testClanZakona.getTekst()).isEqualTo(DEFAULT_TEKST);
    }

    @Test
    void createClanZakonaWithExistingId() throws Exception {
        // Create the ClanZakona with an existing ID
        clanZakona.setId("existing_id");
        ClanZakonaDTO clanZakonaDTO = clanZakonaMapper.toDto(clanZakona);

        int databaseSizeBeforeCreate = clanZakonaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restClanZakonaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clanZakonaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClanZakona in the database
        List<ClanZakona> clanZakonaList = clanZakonaRepository.findAll();
        assertThat(clanZakonaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkBrojIsRequired() throws Exception {
        int databaseSizeBeforeTest = clanZakonaRepository.findAll().size();
        // set the field null
        clanZakona.setBroj(null);

        // Create the ClanZakona, which fails.
        ClanZakonaDTO clanZakonaDTO = clanZakonaMapper.toDto(clanZakona);

        restClanZakonaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clanZakonaDTO))
            )
            .andExpect(status().isBadRequest());

        List<ClanZakona> clanZakonaList = clanZakonaRepository.findAll();
        assertThat(clanZakonaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkGlavaIsRequired() throws Exception {
        int databaseSizeBeforeTest = clanZakonaRepository.findAll().size();
        // set the field null
        clanZakona.setGlava(null);

        // Create the ClanZakona, which fails.
        ClanZakonaDTO clanZakonaDTO = clanZakonaMapper.toDto(clanZakona);

        restClanZakonaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clanZakonaDTO))
            )
            .andExpect(status().isBadRequest());

        List<ClanZakona> clanZakonaList = clanZakonaRepository.findAll();
        assertThat(clanZakonaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkNazivIsRequired() throws Exception {
        int databaseSizeBeforeTest = clanZakonaRepository.findAll().size();
        // set the field null
        clanZakona.setNaziv(null);

        // Create the ClanZakona, which fails.
        ClanZakonaDTO clanZakonaDTO = clanZakonaMapper.toDto(clanZakona);

        restClanZakonaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clanZakonaDTO))
            )
            .andExpect(status().isBadRequest());

        List<ClanZakona> clanZakonaList = clanZakonaRepository.findAll();
        assertThat(clanZakonaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllClanZakonas() throws Exception {
        // Initialize the database
        clanZakonaRepository.save(clanZakona);

        // Get all the clanZakonaList
        restClanZakonaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(clanZakona.getId())))
            .andExpect(jsonPath("$.[*].broj").value(hasItem(DEFAULT_BROJ)))
            .andExpect(jsonPath("$.[*].glava").value(hasItem(DEFAULT_GLAVA)))
            .andExpect(jsonPath("$.[*].naziv").value(hasItem(DEFAULT_NAZIV)))
            .andExpect(jsonPath("$.[*].tekst").value(hasItem(DEFAULT_TEKST)));
    }

    @Test
    void getClanZakona() throws Exception {
        // Initialize the database
        clanZakonaRepository.save(clanZakona);

        // Get the clanZakona
        restClanZakonaMockMvc
            .perform(get(ENTITY_API_URL_ID, clanZakona.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(clanZakona.getId()))
            .andExpect(jsonPath("$.broj").value(DEFAULT_BROJ))
            .andExpect(jsonPath("$.glava").value(DEFAULT_GLAVA))
            .andExpect(jsonPath("$.naziv").value(DEFAULT_NAZIV))
            .andExpect(jsonPath("$.tekst").value(DEFAULT_TEKST));
    }

    @Test
    void getNonExistingClanZakona() throws Exception {
        // Get the clanZakona
        restClanZakonaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingClanZakona() throws Exception {
        // Initialize the database
        clanZakonaRepository.save(clanZakona);

        int databaseSizeBeforeUpdate = clanZakonaRepository.findAll().size();

        // Update the clanZakona
        ClanZakona updatedClanZakona = clanZakonaRepository.findById(clanZakona.getId()).get();
        updatedClanZakona.broj(UPDATED_BROJ).glava(UPDATED_GLAVA).naziv(UPDATED_NAZIV).tekst(UPDATED_TEKST);
        ClanZakonaDTO clanZakonaDTO = clanZakonaMapper.toDto(updatedClanZakona);

        restClanZakonaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, clanZakonaDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clanZakonaDTO))
            )
            .andExpect(status().isOk());

        // Validate the ClanZakona in the database
        List<ClanZakona> clanZakonaList = clanZakonaRepository.findAll();
        assertThat(clanZakonaList).hasSize(databaseSizeBeforeUpdate);
        ClanZakona testClanZakona = clanZakonaList.get(clanZakonaList.size() - 1);
        assertThat(testClanZakona.getBroj()).isEqualTo(UPDATED_BROJ);
        assertThat(testClanZakona.getGlava()).isEqualTo(UPDATED_GLAVA);
        assertThat(testClanZakona.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testClanZakona.getTekst()).isEqualTo(UPDATED_TEKST);
    }

    @Test
    void putNonExistingClanZakona() throws Exception {
        int databaseSizeBeforeUpdate = clanZakonaRepository.findAll().size();
        clanZakona.setId(UUID.randomUUID().toString());

        // Create the ClanZakona
        ClanZakonaDTO clanZakonaDTO = clanZakonaMapper.toDto(clanZakona);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClanZakonaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, clanZakonaDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clanZakonaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClanZakona in the database
        List<ClanZakona> clanZakonaList = clanZakonaRepository.findAll();
        assertThat(clanZakonaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchClanZakona() throws Exception {
        int databaseSizeBeforeUpdate = clanZakonaRepository.findAll().size();
        clanZakona.setId(UUID.randomUUID().toString());

        // Create the ClanZakona
        ClanZakonaDTO clanZakonaDTO = clanZakonaMapper.toDto(clanZakona);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClanZakonaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clanZakonaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClanZakona in the database
        List<ClanZakona> clanZakonaList = clanZakonaRepository.findAll();
        assertThat(clanZakonaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamClanZakona() throws Exception {
        int databaseSizeBeforeUpdate = clanZakonaRepository.findAll().size();
        clanZakona.setId(UUID.randomUUID().toString());

        // Create the ClanZakona
        ClanZakonaDTO clanZakonaDTO = clanZakonaMapper.toDto(clanZakona);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClanZakonaMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clanZakonaDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClanZakona in the database
        List<ClanZakona> clanZakonaList = clanZakonaRepository.findAll();
        assertThat(clanZakonaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateClanZakonaWithPatch() throws Exception {
        // Initialize the database
        clanZakonaRepository.save(clanZakona);

        int databaseSizeBeforeUpdate = clanZakonaRepository.findAll().size();

        // Update the clanZakona using partial update
        ClanZakona partialUpdatedClanZakona = new ClanZakona();
        partialUpdatedClanZakona.setId(clanZakona.getId());

        partialUpdatedClanZakona.glava(UPDATED_GLAVA).naziv(UPDATED_NAZIV);

        restClanZakonaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClanZakona.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClanZakona))
            )
            .andExpect(status().isOk());

        // Validate the ClanZakona in the database
        List<ClanZakona> clanZakonaList = clanZakonaRepository.findAll();
        assertThat(clanZakonaList).hasSize(databaseSizeBeforeUpdate);
        ClanZakona testClanZakona = clanZakonaList.get(clanZakonaList.size() - 1);
        assertThat(testClanZakona.getBroj()).isEqualTo(DEFAULT_BROJ);
        assertThat(testClanZakona.getGlava()).isEqualTo(UPDATED_GLAVA);
        assertThat(testClanZakona.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testClanZakona.getTekst()).isEqualTo(DEFAULT_TEKST);
    }

    @Test
    void fullUpdateClanZakonaWithPatch() throws Exception {
        // Initialize the database
        clanZakonaRepository.save(clanZakona);

        int databaseSizeBeforeUpdate = clanZakonaRepository.findAll().size();

        // Update the clanZakona using partial update
        ClanZakona partialUpdatedClanZakona = new ClanZakona();
        partialUpdatedClanZakona.setId(clanZakona.getId());

        partialUpdatedClanZakona.broj(UPDATED_BROJ).glava(UPDATED_GLAVA).naziv(UPDATED_NAZIV).tekst(UPDATED_TEKST);

        restClanZakonaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClanZakona.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClanZakona))
            )
            .andExpect(status().isOk());

        // Validate the ClanZakona in the database
        List<ClanZakona> clanZakonaList = clanZakonaRepository.findAll();
        assertThat(clanZakonaList).hasSize(databaseSizeBeforeUpdate);
        ClanZakona testClanZakona = clanZakonaList.get(clanZakonaList.size() - 1);
        assertThat(testClanZakona.getBroj()).isEqualTo(UPDATED_BROJ);
        assertThat(testClanZakona.getGlava()).isEqualTo(UPDATED_GLAVA);
        assertThat(testClanZakona.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testClanZakona.getTekst()).isEqualTo(UPDATED_TEKST);
    }

    @Test
    void patchNonExistingClanZakona() throws Exception {
        int databaseSizeBeforeUpdate = clanZakonaRepository.findAll().size();
        clanZakona.setId(UUID.randomUUID().toString());

        // Create the ClanZakona
        ClanZakonaDTO clanZakonaDTO = clanZakonaMapper.toDto(clanZakona);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClanZakonaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, clanZakonaDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(clanZakonaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClanZakona in the database
        List<ClanZakona> clanZakonaList = clanZakonaRepository.findAll();
        assertThat(clanZakonaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchClanZakona() throws Exception {
        int databaseSizeBeforeUpdate = clanZakonaRepository.findAll().size();
        clanZakona.setId(UUID.randomUUID().toString());

        // Create the ClanZakona
        ClanZakonaDTO clanZakonaDTO = clanZakonaMapper.toDto(clanZakona);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClanZakonaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(clanZakonaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClanZakona in the database
        List<ClanZakona> clanZakonaList = clanZakonaRepository.findAll();
        assertThat(clanZakonaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamClanZakona() throws Exception {
        int databaseSizeBeforeUpdate = clanZakonaRepository.findAll().size();
        clanZakona.setId(UUID.randomUUID().toString());

        // Create the ClanZakona
        ClanZakonaDTO clanZakonaDTO = clanZakonaMapper.toDto(clanZakona);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClanZakonaMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(clanZakonaDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClanZakona in the database
        List<ClanZakona> clanZakonaList = clanZakonaRepository.findAll();
        assertThat(clanZakonaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteClanZakona() throws Exception {
        // Initialize the database
        clanZakonaRepository.save(clanZakona);

        int databaseSizeBeforeDelete = clanZakonaRepository.findAll().size();

        // Delete the clanZakona
        restClanZakonaMockMvc
            .perform(delete(ENTITY_API_URL_ID, clanZakona.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ClanZakona> clanZakonaList = clanZakonaRepository.findAll();
        assertThat(clanZakonaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
