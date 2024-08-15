package pravna.com.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import pravna.com.myapp.IntegrationTest;
import pravna.com.myapp.domain.Osoba;
import pravna.com.myapp.repository.OsobaRepository;
import pravna.com.myapp.service.OsobaService;

/**
 * Integration tests for the {@link OsobaResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class OsobaResourceIT {

    private static final String DEFAULT_IME = "AAAAAAAAAA";
    private static final String UPDATED_IME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/osobas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private OsobaRepository osobaRepository;

    @Mock
    private OsobaRepository osobaRepositoryMock;

    @Mock
    private OsobaService osobaServiceMock;

    @Autowired
    private MockMvc restOsobaMockMvc;

    private Osoba osoba;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Osoba createEntity() {
        Osoba osoba = new Osoba().ime(DEFAULT_IME);
        return osoba;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Osoba createUpdatedEntity() {
        Osoba osoba = new Osoba().ime(UPDATED_IME);
        return osoba;
    }

    @BeforeEach
    public void initTest() {
        osobaRepository.deleteAll();
        osoba = createEntity();
    }

    @Test
    void createOsoba() throws Exception {
        int databaseSizeBeforeCreate = osobaRepository.findAll().size();
        // Create the Osoba
        restOsobaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(osoba)))
            .andExpect(status().isCreated());

        // Validate the Osoba in the database
        List<Osoba> osobaList = osobaRepository.findAll();
        assertThat(osobaList).hasSize(databaseSizeBeforeCreate + 1);
        Osoba testOsoba = osobaList.get(osobaList.size() - 1);
        assertThat(testOsoba.getIme()).isEqualTo(DEFAULT_IME);
    }

    @Test
    void createOsobaWithExistingId() throws Exception {
        // Create the Osoba with an existing ID
        osoba.setId("existing_id");

        int databaseSizeBeforeCreate = osobaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOsobaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(osoba)))
            .andExpect(status().isBadRequest());

        // Validate the Osoba in the database
        List<Osoba> osobaList = osobaRepository.findAll();
        assertThat(osobaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkImeIsRequired() throws Exception {
        int databaseSizeBeforeTest = osobaRepository.findAll().size();
        // set the field null
        osoba.setIme(null);

        // Create the Osoba, which fails.

        restOsobaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(osoba)))
            .andExpect(status().isBadRequest());

        List<Osoba> osobaList = osobaRepository.findAll();
        assertThat(osobaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllOsobas() throws Exception {
        // Initialize the database
        osobaRepository.save(osoba);

        // Get all the osobaList
        restOsobaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(osoba.getId())))
            .andExpect(jsonPath("$.[*].ime").value(hasItem(DEFAULT_IME)));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllOsobasWithEagerRelationshipsIsEnabled() throws Exception {
        when(osobaServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restOsobaMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(osobaServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllOsobasWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(osobaServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restOsobaMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(osobaRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void getOsoba() throws Exception {
        // Initialize the database
        osobaRepository.save(osoba);

        // Get the osoba
        restOsobaMockMvc
            .perform(get(ENTITY_API_URL_ID, osoba.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(osoba.getId()))
            .andExpect(jsonPath("$.ime").value(DEFAULT_IME));
    }

    @Test
    void getNonExistingOsoba() throws Exception {
        // Get the osoba
        restOsobaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingOsoba() throws Exception {
        // Initialize the database
        osobaRepository.save(osoba);

        int databaseSizeBeforeUpdate = osobaRepository.findAll().size();

        // Update the osoba
        Osoba updatedOsoba = osobaRepository.findById(osoba.getId()).get();
        updatedOsoba.ime(UPDATED_IME);

        restOsobaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOsoba.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedOsoba))
            )
            .andExpect(status().isOk());

        // Validate the Osoba in the database
        List<Osoba> osobaList = osobaRepository.findAll();
        assertThat(osobaList).hasSize(databaseSizeBeforeUpdate);
        Osoba testOsoba = osobaList.get(osobaList.size() - 1);
        assertThat(testOsoba.getIme()).isEqualTo(UPDATED_IME);
    }

    @Test
    void putNonExistingOsoba() throws Exception {
        int databaseSizeBeforeUpdate = osobaRepository.findAll().size();
        osoba.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOsobaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, osoba.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(osoba))
            )
            .andExpect(status().isBadRequest());

        // Validate the Osoba in the database
        List<Osoba> osobaList = osobaRepository.findAll();
        assertThat(osobaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchOsoba() throws Exception {
        int databaseSizeBeforeUpdate = osobaRepository.findAll().size();
        osoba.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOsobaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(osoba))
            )
            .andExpect(status().isBadRequest());

        // Validate the Osoba in the database
        List<Osoba> osobaList = osobaRepository.findAll();
        assertThat(osobaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamOsoba() throws Exception {
        int databaseSizeBeforeUpdate = osobaRepository.findAll().size();
        osoba.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOsobaMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(osoba)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Osoba in the database
        List<Osoba> osobaList = osobaRepository.findAll();
        assertThat(osobaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateOsobaWithPatch() throws Exception {
        // Initialize the database
        osobaRepository.save(osoba);

        int databaseSizeBeforeUpdate = osobaRepository.findAll().size();

        // Update the osoba using partial update
        Osoba partialUpdatedOsoba = new Osoba();
        partialUpdatedOsoba.setId(osoba.getId());

        partialUpdatedOsoba.ime(UPDATED_IME);

        restOsobaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOsoba.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOsoba))
            )
            .andExpect(status().isOk());

        // Validate the Osoba in the database
        List<Osoba> osobaList = osobaRepository.findAll();
        assertThat(osobaList).hasSize(databaseSizeBeforeUpdate);
        Osoba testOsoba = osobaList.get(osobaList.size() - 1);
        assertThat(testOsoba.getIme()).isEqualTo(UPDATED_IME);
    }

    @Test
    void fullUpdateOsobaWithPatch() throws Exception {
        // Initialize the database
        osobaRepository.save(osoba);

        int databaseSizeBeforeUpdate = osobaRepository.findAll().size();

        // Update the osoba using partial update
        Osoba partialUpdatedOsoba = new Osoba();
        partialUpdatedOsoba.setId(osoba.getId());

        partialUpdatedOsoba.ime(UPDATED_IME);

        restOsobaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOsoba.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOsoba))
            )
            .andExpect(status().isOk());

        // Validate the Osoba in the database
        List<Osoba> osobaList = osobaRepository.findAll();
        assertThat(osobaList).hasSize(databaseSizeBeforeUpdate);
        Osoba testOsoba = osobaList.get(osobaList.size() - 1);
        assertThat(testOsoba.getIme()).isEqualTo(UPDATED_IME);
    }

    @Test
    void patchNonExistingOsoba() throws Exception {
        int databaseSizeBeforeUpdate = osobaRepository.findAll().size();
        osoba.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOsobaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, osoba.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(osoba))
            )
            .andExpect(status().isBadRequest());

        // Validate the Osoba in the database
        List<Osoba> osobaList = osobaRepository.findAll();
        assertThat(osobaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchOsoba() throws Exception {
        int databaseSizeBeforeUpdate = osobaRepository.findAll().size();
        osoba.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOsobaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(osoba))
            )
            .andExpect(status().isBadRequest());

        // Validate the Osoba in the database
        List<Osoba> osobaList = osobaRepository.findAll();
        assertThat(osobaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamOsoba() throws Exception {
        int databaseSizeBeforeUpdate = osobaRepository.findAll().size();
        osoba.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOsobaMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(osoba)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Osoba in the database
        List<Osoba> osobaList = osobaRepository.findAll();
        assertThat(osobaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteOsoba() throws Exception {
        // Initialize the database
        osobaRepository.save(osoba);

        int databaseSizeBeforeDelete = osobaRepository.findAll().size();

        // Delete the osoba
        restOsobaMockMvc
            .perform(delete(ENTITY_API_URL_ID, osoba.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Osoba> osobaList = osobaRepository.findAll();
        assertThat(osobaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
