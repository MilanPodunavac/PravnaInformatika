package pravna.com.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
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
import pravna.com.myapp.domain.Zakon;
import pravna.com.myapp.repository.ZakonRepository;
import pravna.com.myapp.service.dto.ZakonDTO;
import pravna.com.myapp.service.mapper.ZakonMapper;

/**
 * Integration tests for the {@link ZakonResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ZakonResourceIT {

    private static final String DEFAULT_NAZIV = "AAAAAAAAAA";
    private static final String UPDATED_NAZIV = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/zakons";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ZakonRepository zakonRepository;

    @Autowired
    private ZakonMapper zakonMapper;

    @Autowired
    private MockMvc restZakonMockMvc;

    private Zakon zakon;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Zakon createEntity() {
        Zakon zakon = new Zakon().naziv(DEFAULT_NAZIV);
        return zakon;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Zakon createUpdatedEntity() {
        Zakon zakon = new Zakon().naziv(UPDATED_NAZIV);
        return zakon;
    }

    @BeforeEach
    public void initTest() {
        zakonRepository.deleteAll();
        zakon = createEntity();
    }

    @Test
    void createZakon() throws Exception {
        int databaseSizeBeforeCreate = zakonRepository.findAll().size();
        // Create the Zakon
        ZakonDTO zakonDTO = zakonMapper.toDto(zakon);
        restZakonMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(zakonDTO)))
            .andExpect(status().isCreated());

        // Validate the Zakon in the database
        List<Zakon> zakonList = zakonRepository.findAll();
        assertThat(zakonList).hasSize(databaseSizeBeforeCreate + 1);
        Zakon testZakon = zakonList.get(zakonList.size() - 1);
        assertThat(testZakon.getNaziv()).isEqualTo(DEFAULT_NAZIV);
    }

    @Test
    void createZakonWithExistingId() throws Exception {
        // Create the Zakon with an existing ID
        zakon.setId("existing_id");
        ZakonDTO zakonDTO = zakonMapper.toDto(zakon);

        int databaseSizeBeforeCreate = zakonRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restZakonMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(zakonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Zakon in the database
        List<Zakon> zakonList = zakonRepository.findAll();
        assertThat(zakonList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkNazivIsRequired() throws Exception {
        int databaseSizeBeforeTest = zakonRepository.findAll().size();
        // set the field null
        zakon.setNaziv(null);

        // Create the Zakon, which fails.
        ZakonDTO zakonDTO = zakonMapper.toDto(zakon);

        restZakonMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(zakonDTO)))
            .andExpect(status().isBadRequest());

        List<Zakon> zakonList = zakonRepository.findAll();
        assertThat(zakonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllZakons() throws Exception {
        // Initialize the database
        zakonRepository.save(zakon);

        // Get all the zakonList
        restZakonMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(zakon.getId())))
            .andExpect(jsonPath("$.[*].naziv").value(hasItem(DEFAULT_NAZIV)));
    }

    @Test
    void getZakon() throws Exception {
        // Initialize the database
        zakonRepository.save(zakon);

        // Get the zakon
        restZakonMockMvc
            .perform(get(ENTITY_API_URL_ID, zakon.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(zakon.getId()))
            .andExpect(jsonPath("$.naziv").value(DEFAULT_NAZIV));
    }

    @Test
    void getNonExistingZakon() throws Exception {
        // Get the zakon
        restZakonMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingZakon() throws Exception {
        // Initialize the database
        zakonRepository.save(zakon);

        int databaseSizeBeforeUpdate = zakonRepository.findAll().size();

        // Update the zakon
        Zakon updatedZakon = zakonRepository.findById(zakon.getId()).get();
        updatedZakon.naziv(UPDATED_NAZIV);
        ZakonDTO zakonDTO = zakonMapper.toDto(updatedZakon);

        restZakonMockMvc
            .perform(
                put(ENTITY_API_URL_ID, zakonDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(zakonDTO))
            )
            .andExpect(status().isOk());

        // Validate the Zakon in the database
        List<Zakon> zakonList = zakonRepository.findAll();
        assertThat(zakonList).hasSize(databaseSizeBeforeUpdate);
        Zakon testZakon = zakonList.get(zakonList.size() - 1);
        assertThat(testZakon.getNaziv()).isEqualTo(UPDATED_NAZIV);
    }

    @Test
    void putNonExistingZakon() throws Exception {
        int databaseSizeBeforeUpdate = zakonRepository.findAll().size();
        zakon.setId(UUID.randomUUID().toString());

        // Create the Zakon
        ZakonDTO zakonDTO = zakonMapper.toDto(zakon);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restZakonMockMvc
            .perform(
                put(ENTITY_API_URL_ID, zakonDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(zakonDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Zakon in the database
        List<Zakon> zakonList = zakonRepository.findAll();
        assertThat(zakonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchZakon() throws Exception {
        int databaseSizeBeforeUpdate = zakonRepository.findAll().size();
        zakon.setId(UUID.randomUUID().toString());

        // Create the Zakon
        ZakonDTO zakonDTO = zakonMapper.toDto(zakon);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restZakonMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(zakonDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Zakon in the database
        List<Zakon> zakonList = zakonRepository.findAll();
        assertThat(zakonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamZakon() throws Exception {
        int databaseSizeBeforeUpdate = zakonRepository.findAll().size();
        zakon.setId(UUID.randomUUID().toString());

        // Create the Zakon
        ZakonDTO zakonDTO = zakonMapper.toDto(zakon);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restZakonMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(zakonDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Zakon in the database
        List<Zakon> zakonList = zakonRepository.findAll();
        assertThat(zakonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateZakonWithPatch() throws Exception {
        // Initialize the database
        zakonRepository.save(zakon);

        int databaseSizeBeforeUpdate = zakonRepository.findAll().size();

        // Update the zakon using partial update
        Zakon partialUpdatedZakon = new Zakon();
        partialUpdatedZakon.setId(zakon.getId());

        restZakonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedZakon.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedZakon))
            )
            .andExpect(status().isOk());

        // Validate the Zakon in the database
        List<Zakon> zakonList = zakonRepository.findAll();
        assertThat(zakonList).hasSize(databaseSizeBeforeUpdate);
        Zakon testZakon = zakonList.get(zakonList.size() - 1);
        assertThat(testZakon.getNaziv()).isEqualTo(DEFAULT_NAZIV);
    }

    @Test
    void fullUpdateZakonWithPatch() throws Exception {
        // Initialize the database
        zakonRepository.save(zakon);

        int databaseSizeBeforeUpdate = zakonRepository.findAll().size();

        // Update the zakon using partial update
        Zakon partialUpdatedZakon = new Zakon();
        partialUpdatedZakon.setId(zakon.getId());

        partialUpdatedZakon.naziv(UPDATED_NAZIV);

        restZakonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedZakon.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedZakon))
            )
            .andExpect(status().isOk());

        // Validate the Zakon in the database
        List<Zakon> zakonList = zakonRepository.findAll();
        assertThat(zakonList).hasSize(databaseSizeBeforeUpdate);
        Zakon testZakon = zakonList.get(zakonList.size() - 1);
        assertThat(testZakon.getNaziv()).isEqualTo(UPDATED_NAZIV);
    }

    @Test
    void patchNonExistingZakon() throws Exception {
        int databaseSizeBeforeUpdate = zakonRepository.findAll().size();
        zakon.setId(UUID.randomUUID().toString());

        // Create the Zakon
        ZakonDTO zakonDTO = zakonMapper.toDto(zakon);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restZakonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, zakonDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(zakonDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Zakon in the database
        List<Zakon> zakonList = zakonRepository.findAll();
        assertThat(zakonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchZakon() throws Exception {
        int databaseSizeBeforeUpdate = zakonRepository.findAll().size();
        zakon.setId(UUID.randomUUID().toString());

        // Create the Zakon
        ZakonDTO zakonDTO = zakonMapper.toDto(zakon);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restZakonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(zakonDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Zakon in the database
        List<Zakon> zakonList = zakonRepository.findAll();
        assertThat(zakonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamZakon() throws Exception {
        int databaseSizeBeforeUpdate = zakonRepository.findAll().size();
        zakon.setId(UUID.randomUUID().toString());

        // Create the Zakon
        ZakonDTO zakonDTO = zakonMapper.toDto(zakon);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restZakonMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(zakonDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Zakon in the database
        List<Zakon> zakonList = zakonRepository.findAll();
        assertThat(zakonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteZakon() throws Exception {
        // Initialize the database
        zakonRepository.save(zakon);

        int databaseSizeBeforeDelete = zakonRepository.findAll().size();

        // Delete the zakon
        restZakonMockMvc
            .perform(delete(ENTITY_API_URL_ID, zakon.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Zakon> zakonList = zakonRepository.findAll();
        assertThat(zakonList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
