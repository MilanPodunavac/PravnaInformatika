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
import pravna.com.myapp.domain.RadnjaPresude;
import pravna.com.myapp.repository.RadnjaPresudeRepository;
import pravna.com.myapp.service.dto.RadnjaPresudeDTO;
import pravna.com.myapp.service.mapper.RadnjaPresudeMapper;

/**
 * Integration tests for the {@link RadnjaPresudeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RadnjaPresudeResourceIT {

    private static final LocalDate DEFAULT_VREME_RADNJE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_VREME_RADNJE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_MESTO_RADNJE = "AAAAAAAAAA";
    private static final String UPDATED_MESTO_RADNJE = "BBBBBBBBBB";

    private static final String DEFAULT_MESTO_SMRTI = "AAAAAAAAAA";
    private static final String UPDATED_MESTO_SMRTI = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_VREME_SMRTI = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_VREME_SMRTI = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/radnja-presudes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private RadnjaPresudeRepository radnjaPresudeRepository;

    @Autowired
    private RadnjaPresudeMapper radnjaPresudeMapper;

    @Autowired
    private MockMvc restRadnjaPresudeMockMvc;

    private RadnjaPresude radnjaPresude;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RadnjaPresude createEntity() {
        RadnjaPresude radnjaPresude = new RadnjaPresude()
            .vremeRadnje(DEFAULT_VREME_RADNJE)
            .mestoRadnje(DEFAULT_MESTO_RADNJE)
            .mestoSmrti(DEFAULT_MESTO_SMRTI)
            .vremeSmrti(DEFAULT_VREME_SMRTI);
        return radnjaPresude;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RadnjaPresude createUpdatedEntity() {
        RadnjaPresude radnjaPresude = new RadnjaPresude()
            .vremeRadnje(UPDATED_VREME_RADNJE)
            .mestoRadnje(UPDATED_MESTO_RADNJE)
            .mestoSmrti(UPDATED_MESTO_SMRTI)
            .vremeSmrti(UPDATED_VREME_SMRTI);
        return radnjaPresude;
    }

    @BeforeEach
    public void initTest() {
        radnjaPresudeRepository.deleteAll();
        radnjaPresude = createEntity();
    }

    @Test
    void createRadnjaPresude() throws Exception {
        int databaseSizeBeforeCreate = radnjaPresudeRepository.findAll().size();
        // Create the RadnjaPresude
        RadnjaPresudeDTO radnjaPresudeDTO = radnjaPresudeMapper.toDto(radnjaPresude);
        restRadnjaPresudeMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(radnjaPresudeDTO))
            )
            .andExpect(status().isCreated());

        // Validate the RadnjaPresude in the database
        List<RadnjaPresude> radnjaPresudeList = radnjaPresudeRepository.findAll();
        assertThat(radnjaPresudeList).hasSize(databaseSizeBeforeCreate + 1);
        RadnjaPresude testRadnjaPresude = radnjaPresudeList.get(radnjaPresudeList.size() - 1);
        assertThat(testRadnjaPresude.getVremeRadnje()).isEqualTo(DEFAULT_VREME_RADNJE);
        assertThat(testRadnjaPresude.getMestoRadnje()).isEqualTo(DEFAULT_MESTO_RADNJE);
        assertThat(testRadnjaPresude.getMestoSmrti()).isEqualTo(DEFAULT_MESTO_SMRTI);
        assertThat(testRadnjaPresude.getVremeSmrti()).isEqualTo(DEFAULT_VREME_SMRTI);
    }

    @Test
    void createRadnjaPresudeWithExistingId() throws Exception {
        // Create the RadnjaPresude with an existing ID
        radnjaPresude.setId("existing_id");
        RadnjaPresudeDTO radnjaPresudeDTO = radnjaPresudeMapper.toDto(radnjaPresude);

        int databaseSizeBeforeCreate = radnjaPresudeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRadnjaPresudeMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(radnjaPresudeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RadnjaPresude in the database
        List<RadnjaPresude> radnjaPresudeList = radnjaPresudeRepository.findAll();
        assertThat(radnjaPresudeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkVremeRadnjeIsRequired() throws Exception {
        int databaseSizeBeforeTest = radnjaPresudeRepository.findAll().size();
        // set the field null
        radnjaPresude.setVremeRadnje(null);

        // Create the RadnjaPresude, which fails.
        RadnjaPresudeDTO radnjaPresudeDTO = radnjaPresudeMapper.toDto(radnjaPresude);

        restRadnjaPresudeMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(radnjaPresudeDTO))
            )
            .andExpect(status().isBadRequest());

        List<RadnjaPresude> radnjaPresudeList = radnjaPresudeRepository.findAll();
        assertThat(radnjaPresudeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkMestoRadnjeIsRequired() throws Exception {
        int databaseSizeBeforeTest = radnjaPresudeRepository.findAll().size();
        // set the field null
        radnjaPresude.setMestoRadnje(null);

        // Create the RadnjaPresude, which fails.
        RadnjaPresudeDTO radnjaPresudeDTO = radnjaPresudeMapper.toDto(radnjaPresude);

        restRadnjaPresudeMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(radnjaPresudeDTO))
            )
            .andExpect(status().isBadRequest());

        List<RadnjaPresude> radnjaPresudeList = radnjaPresudeRepository.findAll();
        assertThat(radnjaPresudeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllRadnjaPresudes() throws Exception {
        // Initialize the database
        radnjaPresudeRepository.save(radnjaPresude);

        // Get all the radnjaPresudeList
        restRadnjaPresudeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(radnjaPresude.getId())))
            .andExpect(jsonPath("$.[*].vremeRadnje").value(hasItem(DEFAULT_VREME_RADNJE.toString())))
            .andExpect(jsonPath("$.[*].mestoRadnje").value(hasItem(DEFAULT_MESTO_RADNJE)))
            .andExpect(jsonPath("$.[*].mestoSmrti").value(hasItem(DEFAULT_MESTO_SMRTI)))
            .andExpect(jsonPath("$.[*].vremeSmrti").value(hasItem(DEFAULT_VREME_SMRTI.toString())));
    }

    @Test
    void getRadnjaPresude() throws Exception {
        // Initialize the database
        radnjaPresudeRepository.save(radnjaPresude);

        // Get the radnjaPresude
        restRadnjaPresudeMockMvc
            .perform(get(ENTITY_API_URL_ID, radnjaPresude.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(radnjaPresude.getId()))
            .andExpect(jsonPath("$.vremeRadnje").value(DEFAULT_VREME_RADNJE.toString()))
            .andExpect(jsonPath("$.mestoRadnje").value(DEFAULT_MESTO_RADNJE))
            .andExpect(jsonPath("$.mestoSmrti").value(DEFAULT_MESTO_SMRTI))
            .andExpect(jsonPath("$.vremeSmrti").value(DEFAULT_VREME_SMRTI.toString()));
    }

    @Test
    void getNonExistingRadnjaPresude() throws Exception {
        // Get the radnjaPresude
        restRadnjaPresudeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingRadnjaPresude() throws Exception {
        // Initialize the database
        radnjaPresudeRepository.save(radnjaPresude);

        int databaseSizeBeforeUpdate = radnjaPresudeRepository.findAll().size();

        // Update the radnjaPresude
        RadnjaPresude updatedRadnjaPresude = radnjaPresudeRepository.findById(radnjaPresude.getId()).get();
        updatedRadnjaPresude
            .vremeRadnje(UPDATED_VREME_RADNJE)
            .mestoRadnje(UPDATED_MESTO_RADNJE)
            .mestoSmrti(UPDATED_MESTO_SMRTI)
            .vremeSmrti(UPDATED_VREME_SMRTI);
        RadnjaPresudeDTO radnjaPresudeDTO = radnjaPresudeMapper.toDto(updatedRadnjaPresude);

        restRadnjaPresudeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, radnjaPresudeDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(radnjaPresudeDTO))
            )
            .andExpect(status().isOk());

        // Validate the RadnjaPresude in the database
        List<RadnjaPresude> radnjaPresudeList = radnjaPresudeRepository.findAll();
        assertThat(radnjaPresudeList).hasSize(databaseSizeBeforeUpdate);
        RadnjaPresude testRadnjaPresude = radnjaPresudeList.get(radnjaPresudeList.size() - 1);
        assertThat(testRadnjaPresude.getVremeRadnje()).isEqualTo(UPDATED_VREME_RADNJE);
        assertThat(testRadnjaPresude.getMestoRadnje()).isEqualTo(UPDATED_MESTO_RADNJE);
        assertThat(testRadnjaPresude.getMestoSmrti()).isEqualTo(UPDATED_MESTO_SMRTI);
        assertThat(testRadnjaPresude.getVremeSmrti()).isEqualTo(UPDATED_VREME_SMRTI);
    }

    @Test
    void putNonExistingRadnjaPresude() throws Exception {
        int databaseSizeBeforeUpdate = radnjaPresudeRepository.findAll().size();
        radnjaPresude.setId(UUID.randomUUID().toString());

        // Create the RadnjaPresude
        RadnjaPresudeDTO radnjaPresudeDTO = radnjaPresudeMapper.toDto(radnjaPresude);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRadnjaPresudeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, radnjaPresudeDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(radnjaPresudeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RadnjaPresude in the database
        List<RadnjaPresude> radnjaPresudeList = radnjaPresudeRepository.findAll();
        assertThat(radnjaPresudeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchRadnjaPresude() throws Exception {
        int databaseSizeBeforeUpdate = radnjaPresudeRepository.findAll().size();
        radnjaPresude.setId(UUID.randomUUID().toString());

        // Create the RadnjaPresude
        RadnjaPresudeDTO radnjaPresudeDTO = radnjaPresudeMapper.toDto(radnjaPresude);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRadnjaPresudeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(radnjaPresudeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RadnjaPresude in the database
        List<RadnjaPresude> radnjaPresudeList = radnjaPresudeRepository.findAll();
        assertThat(radnjaPresudeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamRadnjaPresude() throws Exception {
        int databaseSizeBeforeUpdate = radnjaPresudeRepository.findAll().size();
        radnjaPresude.setId(UUID.randomUUID().toString());

        // Create the RadnjaPresude
        RadnjaPresudeDTO radnjaPresudeDTO = radnjaPresudeMapper.toDto(radnjaPresude);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRadnjaPresudeMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(radnjaPresudeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RadnjaPresude in the database
        List<RadnjaPresude> radnjaPresudeList = radnjaPresudeRepository.findAll();
        assertThat(radnjaPresudeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateRadnjaPresudeWithPatch() throws Exception {
        // Initialize the database
        radnjaPresudeRepository.save(radnjaPresude);

        int databaseSizeBeforeUpdate = radnjaPresudeRepository.findAll().size();

        // Update the radnjaPresude using partial update
        RadnjaPresude partialUpdatedRadnjaPresude = new RadnjaPresude();
        partialUpdatedRadnjaPresude.setId(radnjaPresude.getId());

        restRadnjaPresudeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRadnjaPresude.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRadnjaPresude))
            )
            .andExpect(status().isOk());

        // Validate the RadnjaPresude in the database
        List<RadnjaPresude> radnjaPresudeList = radnjaPresudeRepository.findAll();
        assertThat(radnjaPresudeList).hasSize(databaseSizeBeforeUpdate);
        RadnjaPresude testRadnjaPresude = radnjaPresudeList.get(radnjaPresudeList.size() - 1);
        assertThat(testRadnjaPresude.getVremeRadnje()).isEqualTo(DEFAULT_VREME_RADNJE);
        assertThat(testRadnjaPresude.getMestoRadnje()).isEqualTo(DEFAULT_MESTO_RADNJE);
        assertThat(testRadnjaPresude.getMestoSmrti()).isEqualTo(DEFAULT_MESTO_SMRTI);
        assertThat(testRadnjaPresude.getVremeSmrti()).isEqualTo(DEFAULT_VREME_SMRTI);
    }

    @Test
    void fullUpdateRadnjaPresudeWithPatch() throws Exception {
        // Initialize the database
        radnjaPresudeRepository.save(radnjaPresude);

        int databaseSizeBeforeUpdate = radnjaPresudeRepository.findAll().size();

        // Update the radnjaPresude using partial update
        RadnjaPresude partialUpdatedRadnjaPresude = new RadnjaPresude();
        partialUpdatedRadnjaPresude.setId(radnjaPresude.getId());

        partialUpdatedRadnjaPresude
            .vremeRadnje(UPDATED_VREME_RADNJE)
            .mestoRadnje(UPDATED_MESTO_RADNJE)
            .mestoSmrti(UPDATED_MESTO_SMRTI)
            .vremeSmrti(UPDATED_VREME_SMRTI);

        restRadnjaPresudeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRadnjaPresude.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRadnjaPresude))
            )
            .andExpect(status().isOk());

        // Validate the RadnjaPresude in the database
        List<RadnjaPresude> radnjaPresudeList = radnjaPresudeRepository.findAll();
        assertThat(radnjaPresudeList).hasSize(databaseSizeBeforeUpdate);
        RadnjaPresude testRadnjaPresude = radnjaPresudeList.get(radnjaPresudeList.size() - 1);
        assertThat(testRadnjaPresude.getVremeRadnje()).isEqualTo(UPDATED_VREME_RADNJE);
        assertThat(testRadnjaPresude.getMestoRadnje()).isEqualTo(UPDATED_MESTO_RADNJE);
        assertThat(testRadnjaPresude.getMestoSmrti()).isEqualTo(UPDATED_MESTO_SMRTI);
        assertThat(testRadnjaPresude.getVremeSmrti()).isEqualTo(UPDATED_VREME_SMRTI);
    }

    @Test
    void patchNonExistingRadnjaPresude() throws Exception {
        int databaseSizeBeforeUpdate = radnjaPresudeRepository.findAll().size();
        radnjaPresude.setId(UUID.randomUUID().toString());

        // Create the RadnjaPresude
        RadnjaPresudeDTO radnjaPresudeDTO = radnjaPresudeMapper.toDto(radnjaPresude);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRadnjaPresudeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, radnjaPresudeDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(radnjaPresudeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RadnjaPresude in the database
        List<RadnjaPresude> radnjaPresudeList = radnjaPresudeRepository.findAll();
        assertThat(radnjaPresudeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchRadnjaPresude() throws Exception {
        int databaseSizeBeforeUpdate = radnjaPresudeRepository.findAll().size();
        radnjaPresude.setId(UUID.randomUUID().toString());

        // Create the RadnjaPresude
        RadnjaPresudeDTO radnjaPresudeDTO = radnjaPresudeMapper.toDto(radnjaPresude);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRadnjaPresudeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(radnjaPresudeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RadnjaPresude in the database
        List<RadnjaPresude> radnjaPresudeList = radnjaPresudeRepository.findAll();
        assertThat(radnjaPresudeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamRadnjaPresude() throws Exception {
        int databaseSizeBeforeUpdate = radnjaPresudeRepository.findAll().size();
        radnjaPresude.setId(UUID.randomUUID().toString());

        // Create the RadnjaPresude
        RadnjaPresudeDTO radnjaPresudeDTO = radnjaPresudeMapper.toDto(radnjaPresude);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRadnjaPresudeMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(radnjaPresudeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RadnjaPresude in the database
        List<RadnjaPresude> radnjaPresudeList = radnjaPresudeRepository.findAll();
        assertThat(radnjaPresudeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteRadnjaPresude() throws Exception {
        // Initialize the database
        radnjaPresudeRepository.save(radnjaPresude);

        int databaseSizeBeforeDelete = radnjaPresudeRepository.findAll().size();

        // Delete the radnjaPresude
        restRadnjaPresudeMockMvc
            .perform(delete(ENTITY_API_URL_ID, radnjaPresude.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RadnjaPresude> radnjaPresudeList = radnjaPresudeRepository.findAll();
        assertThat(radnjaPresudeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
