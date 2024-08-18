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
import pravna.com.myapp.domain.Povreda;
import pravna.com.myapp.domain.RadnjaPresude;
import pravna.com.myapp.repository.PovredaRepository;
import pravna.com.myapp.service.dto.PovredaDTO;
import pravna.com.myapp.service.mapper.PovredaMapper;

/**
 * Integration tests for the {@link PovredaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PovredaResourceIT {

    private static final String DEFAULT_ORUZJE = "AAAAAAAAAA";
    private static final String UPDATED_ORUZJE = "BBBBBBBBBB";

    private static final String DEFAULT_DEO_TELA = "AAAAAAAAAA";
    private static final String UPDATED_DEO_TELA = "BBBBBBBBBB";

    private static final String DEFAULT_POVREDE = "AAAAAAAAAA";
    private static final String UPDATED_POVREDE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/povredas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private PovredaRepository povredaRepository;

    @Autowired
    private PovredaMapper povredaMapper;

    @Autowired
    private MockMvc restPovredaMockMvc;

    private Povreda povreda;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Povreda createEntity() {
        Povreda povreda = new Povreda().oruzje(DEFAULT_ORUZJE).deoTela(DEFAULT_DEO_TELA).povrede(DEFAULT_POVREDE);
        // Add required entity
        RadnjaPresude radnjaPresude;
        radnjaPresude = RadnjaPresudeResourceIT.createEntity();
        radnjaPresude.setId("fixed-id-for-tests");
        povreda.setRadnja(radnjaPresude);
        return povreda;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Povreda createUpdatedEntity() {
        Povreda povreda = new Povreda().oruzje(UPDATED_ORUZJE).deoTela(UPDATED_DEO_TELA).povrede(UPDATED_POVREDE);
        // Add required entity
        RadnjaPresude radnjaPresude;
        radnjaPresude = RadnjaPresudeResourceIT.createUpdatedEntity();
        radnjaPresude.setId("fixed-id-for-tests");
        povreda.setRadnja(radnjaPresude);
        return povreda;
    }

    @BeforeEach
    public void initTest() {
        povredaRepository.deleteAll();
        povreda = createEntity();
    }

    @Test
    void createPovreda() throws Exception {
        int databaseSizeBeforeCreate = povredaRepository.findAll().size();
        // Create the Povreda
        PovredaDTO povredaDTO = povredaMapper.toDto(povreda);
        restPovredaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(povredaDTO)))
            .andExpect(status().isCreated());

        // Validate the Povreda in the database
        List<Povreda> povredaList = povredaRepository.findAll();
        assertThat(povredaList).hasSize(databaseSizeBeforeCreate + 1);
        Povreda testPovreda = povredaList.get(povredaList.size() - 1);
        assertThat(testPovreda.getOruzje()).isEqualTo(DEFAULT_ORUZJE);
        assertThat(testPovreda.getDeoTela()).isEqualTo(DEFAULT_DEO_TELA);
        assertThat(testPovreda.getPovrede()).isEqualTo(DEFAULT_POVREDE);
    }

    @Test
    void createPovredaWithExistingId() throws Exception {
        // Create the Povreda with an existing ID
        povreda.setId("existing_id");
        PovredaDTO povredaDTO = povredaMapper.toDto(povreda);

        int databaseSizeBeforeCreate = povredaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPovredaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(povredaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Povreda in the database
        List<Povreda> povredaList = povredaRepository.findAll();
        assertThat(povredaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkOruzjeIsRequired() throws Exception {
        int databaseSizeBeforeTest = povredaRepository.findAll().size();
        // set the field null
        povreda.setOruzje(null);

        // Create the Povreda, which fails.
        PovredaDTO povredaDTO = povredaMapper.toDto(povreda);

        restPovredaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(povredaDTO)))
            .andExpect(status().isBadRequest());

        List<Povreda> povredaList = povredaRepository.findAll();
        assertThat(povredaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDeoTelaIsRequired() throws Exception {
        int databaseSizeBeforeTest = povredaRepository.findAll().size();
        // set the field null
        povreda.setDeoTela(null);

        // Create the Povreda, which fails.
        PovredaDTO povredaDTO = povredaMapper.toDto(povreda);

        restPovredaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(povredaDTO)))
            .andExpect(status().isBadRequest());

        List<Povreda> povredaList = povredaRepository.findAll();
        assertThat(povredaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkPovredeIsRequired() throws Exception {
        int databaseSizeBeforeTest = povredaRepository.findAll().size();
        // set the field null
        povreda.setPovrede(null);

        // Create the Povreda, which fails.
        PovredaDTO povredaDTO = povredaMapper.toDto(povreda);

        restPovredaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(povredaDTO)))
            .andExpect(status().isBadRequest());

        List<Povreda> povredaList = povredaRepository.findAll();
        assertThat(povredaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllPovredas() throws Exception {
        // Initialize the database
        povredaRepository.save(povreda);

        // Get all the povredaList
        restPovredaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(povreda.getId())))
            .andExpect(jsonPath("$.[*].oruzje").value(hasItem(DEFAULT_ORUZJE)))
            .andExpect(jsonPath("$.[*].deoTela").value(hasItem(DEFAULT_DEO_TELA)))
            .andExpect(jsonPath("$.[*].povrede").value(hasItem(DEFAULT_POVREDE)));
    }

    @Test
    void getPovreda() throws Exception {
        // Initialize the database
        povredaRepository.save(povreda);

        // Get the povreda
        restPovredaMockMvc
            .perform(get(ENTITY_API_URL_ID, povreda.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(povreda.getId()))
            .andExpect(jsonPath("$.oruzje").value(DEFAULT_ORUZJE))
            .andExpect(jsonPath("$.deoTela").value(DEFAULT_DEO_TELA))
            .andExpect(jsonPath("$.povrede").value(DEFAULT_POVREDE));
    }

    @Test
    void getNonExistingPovreda() throws Exception {
        // Get the povreda
        restPovredaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingPovreda() throws Exception {
        // Initialize the database
        povredaRepository.save(povreda);

        int databaseSizeBeforeUpdate = povredaRepository.findAll().size();

        // Update the povreda
        Povreda updatedPovreda = povredaRepository.findById(povreda.getId()).get();
        updatedPovreda.oruzje(UPDATED_ORUZJE).deoTela(UPDATED_DEO_TELA).povrede(UPDATED_POVREDE);
        PovredaDTO povredaDTO = povredaMapper.toDto(updatedPovreda);

        restPovredaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, povredaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(povredaDTO))
            )
            .andExpect(status().isOk());

        // Validate the Povreda in the database
        List<Povreda> povredaList = povredaRepository.findAll();
        assertThat(povredaList).hasSize(databaseSizeBeforeUpdate);
        Povreda testPovreda = povredaList.get(povredaList.size() - 1);
        assertThat(testPovreda.getOruzje()).isEqualTo(UPDATED_ORUZJE);
        assertThat(testPovreda.getDeoTela()).isEqualTo(UPDATED_DEO_TELA);
        assertThat(testPovreda.getPovrede()).isEqualTo(UPDATED_POVREDE);
    }

    @Test
    void putNonExistingPovreda() throws Exception {
        int databaseSizeBeforeUpdate = povredaRepository.findAll().size();
        povreda.setId(UUID.randomUUID().toString());

        // Create the Povreda
        PovredaDTO povredaDTO = povredaMapper.toDto(povreda);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPovredaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, povredaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(povredaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Povreda in the database
        List<Povreda> povredaList = povredaRepository.findAll();
        assertThat(povredaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPovreda() throws Exception {
        int databaseSizeBeforeUpdate = povredaRepository.findAll().size();
        povreda.setId(UUID.randomUUID().toString());

        // Create the Povreda
        PovredaDTO povredaDTO = povredaMapper.toDto(povreda);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPovredaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(povredaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Povreda in the database
        List<Povreda> povredaList = povredaRepository.findAll();
        assertThat(povredaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPovreda() throws Exception {
        int databaseSizeBeforeUpdate = povredaRepository.findAll().size();
        povreda.setId(UUID.randomUUID().toString());

        // Create the Povreda
        PovredaDTO povredaDTO = povredaMapper.toDto(povreda);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPovredaMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(povredaDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Povreda in the database
        List<Povreda> povredaList = povredaRepository.findAll();
        assertThat(povredaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePovredaWithPatch() throws Exception {
        // Initialize the database
        povredaRepository.save(povreda);

        int databaseSizeBeforeUpdate = povredaRepository.findAll().size();

        // Update the povreda using partial update
        Povreda partialUpdatedPovreda = new Povreda();
        partialUpdatedPovreda.setId(povreda.getId());

        partialUpdatedPovreda.oruzje(UPDATED_ORUZJE).deoTela(UPDATED_DEO_TELA).povrede(UPDATED_POVREDE);

        restPovredaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPovreda.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPovreda))
            )
            .andExpect(status().isOk());

        // Validate the Povreda in the database
        List<Povreda> povredaList = povredaRepository.findAll();
        assertThat(povredaList).hasSize(databaseSizeBeforeUpdate);
        Povreda testPovreda = povredaList.get(povredaList.size() - 1);
        assertThat(testPovreda.getOruzje()).isEqualTo(UPDATED_ORUZJE);
        assertThat(testPovreda.getDeoTela()).isEqualTo(UPDATED_DEO_TELA);
        assertThat(testPovreda.getPovrede()).isEqualTo(UPDATED_POVREDE);
    }

    @Test
    void fullUpdatePovredaWithPatch() throws Exception {
        // Initialize the database
        povredaRepository.save(povreda);

        int databaseSizeBeforeUpdate = povredaRepository.findAll().size();

        // Update the povreda using partial update
        Povreda partialUpdatedPovreda = new Povreda();
        partialUpdatedPovreda.setId(povreda.getId());

        partialUpdatedPovreda.oruzje(UPDATED_ORUZJE).deoTela(UPDATED_DEO_TELA).povrede(UPDATED_POVREDE);

        restPovredaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPovreda.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPovreda))
            )
            .andExpect(status().isOk());

        // Validate the Povreda in the database
        List<Povreda> povredaList = povredaRepository.findAll();
        assertThat(povredaList).hasSize(databaseSizeBeforeUpdate);
        Povreda testPovreda = povredaList.get(povredaList.size() - 1);
        assertThat(testPovreda.getOruzje()).isEqualTo(UPDATED_ORUZJE);
        assertThat(testPovreda.getDeoTela()).isEqualTo(UPDATED_DEO_TELA);
        assertThat(testPovreda.getPovrede()).isEqualTo(UPDATED_POVREDE);
    }

    @Test
    void patchNonExistingPovreda() throws Exception {
        int databaseSizeBeforeUpdate = povredaRepository.findAll().size();
        povreda.setId(UUID.randomUUID().toString());

        // Create the Povreda
        PovredaDTO povredaDTO = povredaMapper.toDto(povreda);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPovredaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, povredaDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(povredaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Povreda in the database
        List<Povreda> povredaList = povredaRepository.findAll();
        assertThat(povredaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPovreda() throws Exception {
        int databaseSizeBeforeUpdate = povredaRepository.findAll().size();
        povreda.setId(UUID.randomUUID().toString());

        // Create the Povreda
        PovredaDTO povredaDTO = povredaMapper.toDto(povreda);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPovredaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(povredaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Povreda in the database
        List<Povreda> povredaList = povredaRepository.findAll();
        assertThat(povredaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPovreda() throws Exception {
        int databaseSizeBeforeUpdate = povredaRepository.findAll().size();
        povreda.setId(UUID.randomUUID().toString());

        // Create the Povreda
        PovredaDTO povredaDTO = povredaMapper.toDto(povreda);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPovredaMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(povredaDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Povreda in the database
        List<Povreda> povredaList = povredaRepository.findAll();
        assertThat(povredaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePovreda() throws Exception {
        // Initialize the database
        povredaRepository.save(povreda);

        int databaseSizeBeforeDelete = povredaRepository.findAll().size();

        // Delete the povreda
        restPovredaMockMvc
            .perform(delete(ENTITY_API_URL_ID, povreda.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Povreda> povredaList = povredaRepository.findAll();
        assertThat(povredaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
