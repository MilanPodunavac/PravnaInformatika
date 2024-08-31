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
import pravna.com.myapp.domain.Sud;
import pravna.com.myapp.domain.enumeration.TipSuda;
import pravna.com.myapp.repository.SudRepository;
import pravna.com.myapp.service.dto.SudDTO;
import pravna.com.myapp.service.mapper.SudMapper;

/**
 * Integration tests for the {@link SudResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SudResourceIT {

    private static final String DEFAULT_NAZIV = "AAAAAAAAAA";
    private static final String UPDATED_NAZIV = "BBBBBBBBBB";

    private static final TipSuda DEFAULT_TIP = TipSuda.OSNOVNI;
    private static final TipSuda UPDATED_TIP = TipSuda.VISI;

    private static final String DEFAULT_MESTO = "AAAAAAAAAA";
    private static final String UPDATED_MESTO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/suds";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private SudRepository sudRepository;

    @Autowired
    private SudMapper sudMapper;

    @Autowired
    private MockMvc restSudMockMvc;

    private Sud sud;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sud createEntity() {
        Sud sud = new Sud().naziv(DEFAULT_NAZIV).tip(DEFAULT_TIP).mesto(DEFAULT_MESTO);
        return sud;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sud createUpdatedEntity() {
        Sud sud = new Sud().naziv(UPDATED_NAZIV).tip(UPDATED_TIP).mesto(UPDATED_MESTO);
        return sud;
    }

    @BeforeEach
    public void initTest() {
        sudRepository.deleteAll();
        sud = createEntity();
    }

    @Test
    void createSud() throws Exception {
        int databaseSizeBeforeCreate = sudRepository.findAll().size();
        // Create the Sud
        SudDTO sudDTO = sudMapper.toDto(sud);
        restSudMockMvc
            .perform(
                post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sudDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Sud in the database
        List<Sud> sudList = sudRepository.findAll();
        assertThat(sudList).hasSize(databaseSizeBeforeCreate + 1);
        Sud testSud = sudList.get(sudList.size() - 1);
        assertThat(testSud.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testSud.getTip()).isEqualTo(DEFAULT_TIP);
        assertThat(testSud.getMesto()).isEqualTo(DEFAULT_MESTO);
    }

    @Test
    void createSudWithExistingId() throws Exception {
        // Create the Sud with an existing ID
        sud.setId("existing_id");
        SudDTO sudDTO = sudMapper.toDto(sud);

        int databaseSizeBeforeCreate = sudRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSudMockMvc
            .perform(
                post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sudDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Sud in the database
        List<Sud> sudList = sudRepository.findAll();
        assertThat(sudList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkNazivIsRequired() throws Exception {
        int databaseSizeBeforeTest = sudRepository.findAll().size();
        // set the field null
        sud.setNaziv(null);

        // Create the Sud, which fails.
        SudDTO sudDTO = sudMapper.toDto(sud);

        restSudMockMvc
            .perform(
                post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sudDTO))
            )
            .andExpect(status().isBadRequest());

        List<Sud> sudList = sudRepository.findAll();
        assertThat(sudList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTipIsRequired() throws Exception {
        int databaseSizeBeforeTest = sudRepository.findAll().size();
        // set the field null
        sud.setTip(null);

        // Create the Sud, which fails.
        SudDTO sudDTO = sudMapper.toDto(sud);

        restSudMockMvc
            .perform(
                post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sudDTO))
            )
            .andExpect(status().isBadRequest());

        List<Sud> sudList = sudRepository.findAll();
        assertThat(sudList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkMestoIsRequired() throws Exception {
        int databaseSizeBeforeTest = sudRepository.findAll().size();
        // set the field null
        sud.setMesto(null);

        // Create the Sud, which fails.
        SudDTO sudDTO = sudMapper.toDto(sud);

        restSudMockMvc
            .perform(
                post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sudDTO))
            )
            .andExpect(status().isBadRequest());

        List<Sud> sudList = sudRepository.findAll();
        assertThat(sudList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllSuds() throws Exception {
        // Initialize the database
        sudRepository.save(sud);

        // Get all the sudList
        restSudMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sud.getId())))
            .andExpect(jsonPath("$.[*].naziv").value(hasItem(DEFAULT_NAZIV)))
            .andExpect(jsonPath("$.[*].tip").value(hasItem(DEFAULT_TIP.toString())))
            .andExpect(jsonPath("$.[*].mesto").value(hasItem(DEFAULT_MESTO)));
    }

    @Test
    void getSud() throws Exception {
        // Initialize the database
        sudRepository.save(sud);

        // Get the sud
        restSudMockMvc
            .perform(get(ENTITY_API_URL_ID, sud.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sud.getId()))
            .andExpect(jsonPath("$.naziv").value(DEFAULT_NAZIV))
            .andExpect(jsonPath("$.tip").value(DEFAULT_TIP.toString()))
            .andExpect(jsonPath("$.mesto").value(DEFAULT_MESTO));
    }

    @Test
    void getNonExistingSud() throws Exception {
        // Get the sud
        restSudMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingSud() throws Exception {
        // Initialize the database
        sudRepository.save(sud);

        int databaseSizeBeforeUpdate = sudRepository.findAll().size();

        // Update the sud
        Sud updatedSud = sudRepository.findById(sud.getId()).get();
        updatedSud.naziv(UPDATED_NAZIV).tip(UPDATED_TIP).mesto(UPDATED_MESTO);
        SudDTO sudDTO = sudMapper.toDto(updatedSud);

        restSudMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sudDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sudDTO))
            )
            .andExpect(status().isOk());

        // Validate the Sud in the database
        List<Sud> sudList = sudRepository.findAll();
        assertThat(sudList).hasSize(databaseSizeBeforeUpdate);
        Sud testSud = sudList.get(sudList.size() - 1);
        assertThat(testSud.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testSud.getTip()).isEqualTo(UPDATED_TIP);
        assertThat(testSud.getMesto()).isEqualTo(UPDATED_MESTO);
    }

    @Test
    void putNonExistingSud() throws Exception {
        int databaseSizeBeforeUpdate = sudRepository.findAll().size();
        sud.setId(UUID.randomUUID().toString());

        // Create the Sud
        SudDTO sudDTO = sudMapper.toDto(sud);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSudMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sudDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sudDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Sud in the database
        List<Sud> sudList = sudRepository.findAll();
        assertThat(sudList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchSud() throws Exception {
        int databaseSizeBeforeUpdate = sudRepository.findAll().size();
        sud.setId(UUID.randomUUID().toString());

        // Create the Sud
        SudDTO sudDTO = sudMapper.toDto(sud);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSudMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sudDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Sud in the database
        List<Sud> sudList = sudRepository.findAll();
        assertThat(sudList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamSud() throws Exception {
        int databaseSizeBeforeUpdate = sudRepository.findAll().size();
        sud.setId(UUID.randomUUID().toString());

        // Create the Sud
        SudDTO sudDTO = sudMapper.toDto(sud);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSudMockMvc
            .perform(
                put(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sudDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Sud in the database
        List<Sud> sudList = sudRepository.findAll();
        assertThat(sudList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateSudWithPatch() throws Exception {
        // Initialize the database
        sudRepository.save(sud);

        int databaseSizeBeforeUpdate = sudRepository.findAll().size();

        // Update the sud using partial update
        Sud partialUpdatedSud = new Sud();
        partialUpdatedSud.setId(sud.getId());

        partialUpdatedSud.mesto(UPDATED_MESTO);

        restSudMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSud.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSud))
            )
            .andExpect(status().isOk());

        // Validate the Sud in the database
        List<Sud> sudList = sudRepository.findAll();
        assertThat(sudList).hasSize(databaseSizeBeforeUpdate);
        Sud testSud = sudList.get(sudList.size() - 1);
        assertThat(testSud.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testSud.getTip()).isEqualTo(DEFAULT_TIP);
        assertThat(testSud.getMesto()).isEqualTo(UPDATED_MESTO);
    }

    @Test
    void fullUpdateSudWithPatch() throws Exception {
        // Initialize the database
        sudRepository.save(sud);

        int databaseSizeBeforeUpdate = sudRepository.findAll().size();

        // Update the sud using partial update
        Sud partialUpdatedSud = new Sud();
        partialUpdatedSud.setId(sud.getId());

        partialUpdatedSud.naziv(UPDATED_NAZIV).tip(UPDATED_TIP).mesto(UPDATED_MESTO);

        restSudMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSud.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSud))
            )
            .andExpect(status().isOk());

        // Validate the Sud in the database
        List<Sud> sudList = sudRepository.findAll();
        assertThat(sudList).hasSize(databaseSizeBeforeUpdate);
        Sud testSud = sudList.get(sudList.size() - 1);
        assertThat(testSud.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testSud.getTip()).isEqualTo(UPDATED_TIP);
        assertThat(testSud.getMesto()).isEqualTo(UPDATED_MESTO);
    }

    @Test
    void patchNonExistingSud() throws Exception {
        int databaseSizeBeforeUpdate = sudRepository.findAll().size();
        sud.setId(UUID.randomUUID().toString());

        // Create the Sud
        SudDTO sudDTO = sudMapper.toDto(sud);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSudMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sudDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sudDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Sud in the database
        List<Sud> sudList = sudRepository.findAll();
        assertThat(sudList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchSud() throws Exception {
        int databaseSizeBeforeUpdate = sudRepository.findAll().size();
        sud.setId(UUID.randomUUID().toString());

        // Create the Sud
        SudDTO sudDTO = sudMapper.toDto(sud);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSudMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sudDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Sud in the database
        List<Sud> sudList = sudRepository.findAll();
        assertThat(sudList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamSud() throws Exception {
        int databaseSizeBeforeUpdate = sudRepository.findAll().size();
        sud.setId(UUID.randomUUID().toString());

        // Create the Sud
        SudDTO sudDTO = sudMapper.toDto(sud);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSudMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sudDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Sud in the database
        List<Sud> sudList = sudRepository.findAll();
        assertThat(sudList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteSud() throws Exception {
        // Initialize the database
        sudRepository.save(sud);

        int databaseSizeBeforeDelete = sudRepository.findAll().size();

        // Delete the sud
        restSudMockMvc
            .perform(delete(ENTITY_API_URL_ID, sud.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Sud> sudList = sudRepository.findAll();
        assertThat(sudList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
