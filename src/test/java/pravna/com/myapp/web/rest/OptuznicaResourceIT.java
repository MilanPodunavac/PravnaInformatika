package pravna.com.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.ZoneId;
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
import pravna.com.myapp.domain.Optuznica;
import pravna.com.myapp.repository.OptuznicaRepository;
import pravna.com.myapp.service.dto.OptuznicaDTO;
import pravna.com.myapp.service.mapper.OptuznicaMapper;

/**
 * Integration tests for the {@link OptuznicaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OptuznicaResourceIT {

    private static final String DEFAULT_KOD = "AAAAAAAAAA";
    private static final String UPDATED_KOD = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATUM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATUM = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_USTANOVA = "AAAAAAAAAA";
    private static final String UPDATED_USTANOVA = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/optuznicas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private OptuznicaRepository optuznicaRepository;

    @Autowired
    private OptuznicaMapper optuznicaMapper;

    @Autowired
    private MockMvc restOptuznicaMockMvc;

    private Optuznica optuznica;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Optuznica createEntity() {
        Optuznica optuznica = new Optuznica().kod(DEFAULT_KOD).datum(DEFAULT_DATUM).ustanova(DEFAULT_USTANOVA);
        return optuznica;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Optuznica createUpdatedEntity() {
        Optuznica optuznica = new Optuznica().kod(UPDATED_KOD).datum(UPDATED_DATUM).ustanova(UPDATED_USTANOVA);
        return optuznica;
    }

    @BeforeEach
    public void initTest() {
        optuznicaRepository.deleteAll();
        optuznica = createEntity();
    }

    @Test
    void createOptuznica() throws Exception {
        int databaseSizeBeforeCreate = optuznicaRepository.findAll().size();
        // Create the Optuznica
        OptuznicaDTO optuznicaDTO = optuznicaMapper.toDto(optuznica);
        restOptuznicaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(optuznicaDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Optuznica in the database
        List<Optuznica> optuznicaList = optuznicaRepository.findAll();
        assertThat(optuznicaList).hasSize(databaseSizeBeforeCreate + 1);
        Optuznica testOptuznica = optuznicaList.get(optuznicaList.size() - 1);
        assertThat(testOptuznica.getKod()).isEqualTo(DEFAULT_KOD);
        assertThat(testOptuznica.getDatum()).isEqualTo(DEFAULT_DATUM);
        assertThat(testOptuznica.getUstanova()).isEqualTo(DEFAULT_USTANOVA);
    }

    @Test
    void createOptuznicaWithExistingId() throws Exception {
        // Create the Optuznica with an existing ID
        optuznica.setId("existing_id");
        OptuznicaDTO optuznicaDTO = optuznicaMapper.toDto(optuznica);

        int databaseSizeBeforeCreate = optuznicaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOptuznicaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(optuznicaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Optuznica in the database
        List<Optuznica> optuznicaList = optuznicaRepository.findAll();
        assertThat(optuznicaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkKodIsRequired() throws Exception {
        int databaseSizeBeforeTest = optuznicaRepository.findAll().size();
        // set the field null
        optuznica.setKod(null);

        // Create the Optuznica, which fails.
        OptuznicaDTO optuznicaDTO = optuznicaMapper.toDto(optuznica);

        restOptuznicaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(optuznicaDTO))
            )
            .andExpect(status().isBadRequest());

        List<Optuznica> optuznicaList = optuznicaRepository.findAll();
        assertThat(optuznicaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDatumIsRequired() throws Exception {
        int databaseSizeBeforeTest = optuznicaRepository.findAll().size();
        // set the field null
        optuznica.setDatum(null);

        // Create the Optuznica, which fails.
        OptuznicaDTO optuznicaDTO = optuznicaMapper.toDto(optuznica);

        restOptuznicaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(optuznicaDTO))
            )
            .andExpect(status().isBadRequest());

        List<Optuznica> optuznicaList = optuznicaRepository.findAll();
        assertThat(optuznicaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllOptuznicas() throws Exception {
        // Initialize the database
        optuznicaRepository.save(optuznica);

        // Get all the optuznicaList
        restOptuznicaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(optuznica.getId())))
            .andExpect(jsonPath("$.[*].kod").value(hasItem(DEFAULT_KOD)))
            .andExpect(jsonPath("$.[*].datum").value(hasItem(DEFAULT_DATUM.toString())))
            .andExpect(jsonPath("$.[*].ustanova").value(hasItem(DEFAULT_USTANOVA)));
    }

    @Test
    void getOptuznica() throws Exception {
        // Initialize the database
        optuznicaRepository.save(optuznica);

        // Get the optuznica
        restOptuznicaMockMvc
            .perform(get(ENTITY_API_URL_ID, optuznica.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(optuznica.getId()))
            .andExpect(jsonPath("$.kod").value(DEFAULT_KOD))
            .andExpect(jsonPath("$.datum").value(DEFAULT_DATUM.toString()))
            .andExpect(jsonPath("$.ustanova").value(DEFAULT_USTANOVA));
    }

    @Test
    void getNonExistingOptuznica() throws Exception {
        // Get the optuznica
        restOptuznicaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingOptuznica() throws Exception {
        // Initialize the database
        optuznicaRepository.save(optuznica);

        int databaseSizeBeforeUpdate = optuznicaRepository.findAll().size();

        // Update the optuznica
        Optuznica updatedOptuznica = optuznicaRepository.findById(optuznica.getId()).get();
        updatedOptuznica.kod(UPDATED_KOD).datum(UPDATED_DATUM).ustanova(UPDATED_USTANOVA);
        OptuznicaDTO optuznicaDTO = optuznicaMapper.toDto(updatedOptuznica);

        restOptuznicaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, optuznicaDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(optuznicaDTO))
            )
            .andExpect(status().isOk());

        // Validate the Optuznica in the database
        List<Optuznica> optuznicaList = optuznicaRepository.findAll();
        assertThat(optuznicaList).hasSize(databaseSizeBeforeUpdate);
        Optuznica testOptuznica = optuznicaList.get(optuznicaList.size() - 1);
        assertThat(testOptuznica.getKod()).isEqualTo(UPDATED_KOD);
        assertThat(testOptuznica.getDatum()).isEqualTo(UPDATED_DATUM);
        assertThat(testOptuznica.getUstanova()).isEqualTo(UPDATED_USTANOVA);
    }

    @Test
    void putNonExistingOptuznica() throws Exception {
        int databaseSizeBeforeUpdate = optuznicaRepository.findAll().size();
        optuznica.setId(UUID.randomUUID().toString());

        // Create the Optuznica
        OptuznicaDTO optuznicaDTO = optuznicaMapper.toDto(optuznica);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOptuznicaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, optuznicaDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(optuznicaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Optuznica in the database
        List<Optuznica> optuznicaList = optuznicaRepository.findAll();
        assertThat(optuznicaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchOptuznica() throws Exception {
        int databaseSizeBeforeUpdate = optuznicaRepository.findAll().size();
        optuznica.setId(UUID.randomUUID().toString());

        // Create the Optuznica
        OptuznicaDTO optuznicaDTO = optuznicaMapper.toDto(optuznica);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOptuznicaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(optuznicaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Optuznica in the database
        List<Optuznica> optuznicaList = optuznicaRepository.findAll();
        assertThat(optuznicaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamOptuznica() throws Exception {
        int databaseSizeBeforeUpdate = optuznicaRepository.findAll().size();
        optuznica.setId(UUID.randomUUID().toString());

        // Create the Optuznica
        OptuznicaDTO optuznicaDTO = optuznicaMapper.toDto(optuznica);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOptuznicaMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(optuznicaDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Optuznica in the database
        List<Optuznica> optuznicaList = optuznicaRepository.findAll();
        assertThat(optuznicaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateOptuznicaWithPatch() throws Exception {
        // Initialize the database
        optuznicaRepository.save(optuznica);

        int databaseSizeBeforeUpdate = optuznicaRepository.findAll().size();

        // Update the optuznica using partial update
        Optuznica partialUpdatedOptuznica = new Optuznica();
        partialUpdatedOptuznica.setId(optuznica.getId());

        partialUpdatedOptuznica.datum(UPDATED_DATUM).ustanova(UPDATED_USTANOVA);

        restOptuznicaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOptuznica.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOptuznica))
            )
            .andExpect(status().isOk());

        // Validate the Optuznica in the database
        List<Optuznica> optuznicaList = optuznicaRepository.findAll();
        assertThat(optuznicaList).hasSize(databaseSizeBeforeUpdate);
        Optuznica testOptuznica = optuznicaList.get(optuznicaList.size() - 1);
        assertThat(testOptuznica.getKod()).isEqualTo(DEFAULT_KOD);
        assertThat(testOptuznica.getDatum()).isEqualTo(UPDATED_DATUM);
        assertThat(testOptuznica.getUstanova()).isEqualTo(UPDATED_USTANOVA);
    }

    @Test
    void fullUpdateOptuznicaWithPatch() throws Exception {
        // Initialize the database
        optuznicaRepository.save(optuznica);

        int databaseSizeBeforeUpdate = optuznicaRepository.findAll().size();

        // Update the optuznica using partial update
        Optuznica partialUpdatedOptuznica = new Optuznica();
        partialUpdatedOptuznica.setId(optuznica.getId());

        partialUpdatedOptuznica.kod(UPDATED_KOD).datum(UPDATED_DATUM).ustanova(UPDATED_USTANOVA);

        restOptuznicaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOptuznica.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOptuznica))
            )
            .andExpect(status().isOk());

        // Validate the Optuznica in the database
        List<Optuznica> optuznicaList = optuznicaRepository.findAll();
        assertThat(optuznicaList).hasSize(databaseSizeBeforeUpdate);
        Optuznica testOptuznica = optuznicaList.get(optuznicaList.size() - 1);
        assertThat(testOptuznica.getKod()).isEqualTo(UPDATED_KOD);
        assertThat(testOptuznica.getDatum()).isEqualTo(UPDATED_DATUM);
        assertThat(testOptuznica.getUstanova()).isEqualTo(UPDATED_USTANOVA);
    }

    @Test
    void patchNonExistingOptuznica() throws Exception {
        int databaseSizeBeforeUpdate = optuznicaRepository.findAll().size();
        optuznica.setId(UUID.randomUUID().toString());

        // Create the Optuznica
        OptuznicaDTO optuznicaDTO = optuznicaMapper.toDto(optuznica);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOptuznicaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, optuznicaDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(optuznicaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Optuznica in the database
        List<Optuznica> optuznicaList = optuznicaRepository.findAll();
        assertThat(optuznicaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchOptuznica() throws Exception {
        int databaseSizeBeforeUpdate = optuznicaRepository.findAll().size();
        optuznica.setId(UUID.randomUUID().toString());

        // Create the Optuznica
        OptuznicaDTO optuznicaDTO = optuznicaMapper.toDto(optuznica);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOptuznicaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(optuznicaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Optuznica in the database
        List<Optuznica> optuznicaList = optuznicaRepository.findAll();
        assertThat(optuznicaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamOptuznica() throws Exception {
        int databaseSizeBeforeUpdate = optuznicaRepository.findAll().size();
        optuznica.setId(UUID.randomUUID().toString());

        // Create the Optuznica
        OptuznicaDTO optuznicaDTO = optuznicaMapper.toDto(optuznica);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOptuznicaMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(optuznicaDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Optuznica in the database
        List<Optuznica> optuznicaList = optuznicaRepository.findAll();
        assertThat(optuznicaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteOptuznica() throws Exception {
        // Initialize the database
        optuznicaRepository.save(optuznica);

        int databaseSizeBeforeDelete = optuznicaRepository.findAll().size();

        // Delete the optuznica
        restOptuznicaMockMvc
            .perform(delete(ENTITY_API_URL_ID, optuznica.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Optuznica> optuznicaList = optuznicaRepository.findAll();
        assertThat(optuznicaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
