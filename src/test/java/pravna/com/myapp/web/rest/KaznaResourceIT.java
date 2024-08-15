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
import pravna.com.myapp.domain.Kazna;
import pravna.com.myapp.domain.enumeration.TipKazne;
import pravna.com.myapp.repository.KaznaRepository;

/**
 * Integration tests for the {@link KaznaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class KaznaResourceIT {

    private static final TipKazne DEFAULT_TIP = TipKazne.ZATVORSKA_KAZNA;
    private static final TipKazne UPDATED_TIP = TipKazne.USLOVNA_KAZNA;

    private static final Integer DEFAULT_DUZINA_PRITVORA = 1;
    private static final Integer UPDATED_DUZINA_PRITVORA = 2;

    private static final Boolean DEFAULT_URACUNAVANJE_PRITVORA = false;
    private static final Boolean UPDATED_URACUNAVANJE_PRITVORA = true;

    private static final Integer DEFAULT_KOLICINA_NOVCA = 1;
    private static final Integer UPDATED_KOLICINA_NOVCA = 2;

    private static final String DEFAULT_PRIMALAC_NOVCA = "AAAAAAAAAA";
    private static final String UPDATED_PRIMALAC_NOVCA = "BBBBBBBBBB";

    private static final String DEFAULT_NAZIV_IMOVINE = "AAAAAAAAAA";
    private static final String UPDATED_NAZIV_IMOVINE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/kaznas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private KaznaRepository kaznaRepository;

    @Autowired
    private MockMvc restKaznaMockMvc;

    private Kazna kazna;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Kazna createEntity() {
        Kazna kazna = new Kazna()
            .tip(DEFAULT_TIP)
            .duzinaPritvora(DEFAULT_DUZINA_PRITVORA)
            .uracunavanjePritvora(DEFAULT_URACUNAVANJE_PRITVORA)
            .kolicinaNovca(DEFAULT_KOLICINA_NOVCA)
            .primalacNovca(DEFAULT_PRIMALAC_NOVCA)
            .nazivImovine(DEFAULT_NAZIV_IMOVINE);
        return kazna;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Kazna createUpdatedEntity() {
        Kazna kazna = new Kazna()
            .tip(UPDATED_TIP)
            .duzinaPritvora(UPDATED_DUZINA_PRITVORA)
            .uracunavanjePritvora(UPDATED_URACUNAVANJE_PRITVORA)
            .kolicinaNovca(UPDATED_KOLICINA_NOVCA)
            .primalacNovca(UPDATED_PRIMALAC_NOVCA)
            .nazivImovine(UPDATED_NAZIV_IMOVINE);
        return kazna;
    }

    @BeforeEach
    public void initTest() {
        kaznaRepository.deleteAll();
        kazna = createEntity();
    }

    @Test
    void createKazna() throws Exception {
        int databaseSizeBeforeCreate = kaznaRepository.findAll().size();
        // Create the Kazna
        restKaznaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(kazna)))
            .andExpect(status().isCreated());

        // Validate the Kazna in the database
        List<Kazna> kaznaList = kaznaRepository.findAll();
        assertThat(kaznaList).hasSize(databaseSizeBeforeCreate + 1);
        Kazna testKazna = kaznaList.get(kaznaList.size() - 1);
        assertThat(testKazna.getTip()).isEqualTo(DEFAULT_TIP);
        assertThat(testKazna.getDuzinaPritvora()).isEqualTo(DEFAULT_DUZINA_PRITVORA);
        assertThat(testKazna.getUracunavanjePritvora()).isEqualTo(DEFAULT_URACUNAVANJE_PRITVORA);
        assertThat(testKazna.getKolicinaNovca()).isEqualTo(DEFAULT_KOLICINA_NOVCA);
        assertThat(testKazna.getPrimalacNovca()).isEqualTo(DEFAULT_PRIMALAC_NOVCA);
        assertThat(testKazna.getNazivImovine()).isEqualTo(DEFAULT_NAZIV_IMOVINE);
    }

    @Test
    void createKaznaWithExistingId() throws Exception {
        // Create the Kazna with an existing ID
        kazna.setId("existing_id");

        int databaseSizeBeforeCreate = kaznaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restKaznaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(kazna)))
            .andExpect(status().isBadRequest());

        // Validate the Kazna in the database
        List<Kazna> kaznaList = kaznaRepository.findAll();
        assertThat(kaznaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkTipIsRequired() throws Exception {
        int databaseSizeBeforeTest = kaznaRepository.findAll().size();
        // set the field null
        kazna.setTip(null);

        // Create the Kazna, which fails.

        restKaznaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(kazna)))
            .andExpect(status().isBadRequest());

        List<Kazna> kaznaList = kaznaRepository.findAll();
        assertThat(kaznaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllKaznas() throws Exception {
        // Initialize the database
        kaznaRepository.save(kazna);

        // Get all the kaznaList
        restKaznaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kazna.getId())))
            .andExpect(jsonPath("$.[*].tip").value(hasItem(DEFAULT_TIP.toString())))
            .andExpect(jsonPath("$.[*].duzinaPritvora").value(hasItem(DEFAULT_DUZINA_PRITVORA)))
            .andExpect(jsonPath("$.[*].uracunavanjePritvora").value(hasItem(DEFAULT_URACUNAVANJE_PRITVORA.booleanValue())))
            .andExpect(jsonPath("$.[*].kolicinaNovca").value(hasItem(DEFAULT_KOLICINA_NOVCA)))
            .andExpect(jsonPath("$.[*].primalacNovca").value(hasItem(DEFAULT_PRIMALAC_NOVCA)))
            .andExpect(jsonPath("$.[*].nazivImovine").value(hasItem(DEFAULT_NAZIV_IMOVINE)));
    }

    @Test
    void getKazna() throws Exception {
        // Initialize the database
        kaznaRepository.save(kazna);

        // Get the kazna
        restKaznaMockMvc
            .perform(get(ENTITY_API_URL_ID, kazna.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(kazna.getId()))
            .andExpect(jsonPath("$.tip").value(DEFAULT_TIP.toString()))
            .andExpect(jsonPath("$.duzinaPritvora").value(DEFAULT_DUZINA_PRITVORA))
            .andExpect(jsonPath("$.uracunavanjePritvora").value(DEFAULT_URACUNAVANJE_PRITVORA.booleanValue()))
            .andExpect(jsonPath("$.kolicinaNovca").value(DEFAULT_KOLICINA_NOVCA))
            .andExpect(jsonPath("$.primalacNovca").value(DEFAULT_PRIMALAC_NOVCA))
            .andExpect(jsonPath("$.nazivImovine").value(DEFAULT_NAZIV_IMOVINE));
    }

    @Test
    void getNonExistingKazna() throws Exception {
        // Get the kazna
        restKaznaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingKazna() throws Exception {
        // Initialize the database
        kaznaRepository.save(kazna);

        int databaseSizeBeforeUpdate = kaznaRepository.findAll().size();

        // Update the kazna
        Kazna updatedKazna = kaznaRepository.findById(kazna.getId()).get();
        updatedKazna
            .tip(UPDATED_TIP)
            .duzinaPritvora(UPDATED_DUZINA_PRITVORA)
            .uracunavanjePritvora(UPDATED_URACUNAVANJE_PRITVORA)
            .kolicinaNovca(UPDATED_KOLICINA_NOVCA)
            .primalacNovca(UPDATED_PRIMALAC_NOVCA)
            .nazivImovine(UPDATED_NAZIV_IMOVINE);

        restKaznaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedKazna.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedKazna))
            )
            .andExpect(status().isOk());

        // Validate the Kazna in the database
        List<Kazna> kaznaList = kaznaRepository.findAll();
        assertThat(kaznaList).hasSize(databaseSizeBeforeUpdate);
        Kazna testKazna = kaznaList.get(kaznaList.size() - 1);
        assertThat(testKazna.getTip()).isEqualTo(UPDATED_TIP);
        assertThat(testKazna.getDuzinaPritvora()).isEqualTo(UPDATED_DUZINA_PRITVORA);
        assertThat(testKazna.getUracunavanjePritvora()).isEqualTo(UPDATED_URACUNAVANJE_PRITVORA);
        assertThat(testKazna.getKolicinaNovca()).isEqualTo(UPDATED_KOLICINA_NOVCA);
        assertThat(testKazna.getPrimalacNovca()).isEqualTo(UPDATED_PRIMALAC_NOVCA);
        assertThat(testKazna.getNazivImovine()).isEqualTo(UPDATED_NAZIV_IMOVINE);
    }

    @Test
    void putNonExistingKazna() throws Exception {
        int databaseSizeBeforeUpdate = kaznaRepository.findAll().size();
        kazna.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKaznaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, kazna.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kazna))
            )
            .andExpect(status().isBadRequest());

        // Validate the Kazna in the database
        List<Kazna> kaznaList = kaznaRepository.findAll();
        assertThat(kaznaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchKazna() throws Exception {
        int databaseSizeBeforeUpdate = kaznaRepository.findAll().size();
        kazna.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restKaznaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kazna))
            )
            .andExpect(status().isBadRequest());

        // Validate the Kazna in the database
        List<Kazna> kaznaList = kaznaRepository.findAll();
        assertThat(kaznaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamKazna() throws Exception {
        int databaseSizeBeforeUpdate = kaznaRepository.findAll().size();
        kazna.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restKaznaMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(kazna)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Kazna in the database
        List<Kazna> kaznaList = kaznaRepository.findAll();
        assertThat(kaznaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateKaznaWithPatch() throws Exception {
        // Initialize the database
        kaznaRepository.save(kazna);

        int databaseSizeBeforeUpdate = kaznaRepository.findAll().size();

        // Update the kazna using partial update
        Kazna partialUpdatedKazna = new Kazna();
        partialUpdatedKazna.setId(kazna.getId());

        partialUpdatedKazna.primalacNovca(UPDATED_PRIMALAC_NOVCA).nazivImovine(UPDATED_NAZIV_IMOVINE);

        restKaznaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedKazna.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedKazna))
            )
            .andExpect(status().isOk());

        // Validate the Kazna in the database
        List<Kazna> kaznaList = kaznaRepository.findAll();
        assertThat(kaznaList).hasSize(databaseSizeBeforeUpdate);
        Kazna testKazna = kaznaList.get(kaznaList.size() - 1);
        assertThat(testKazna.getTip()).isEqualTo(DEFAULT_TIP);
        assertThat(testKazna.getDuzinaPritvora()).isEqualTo(DEFAULT_DUZINA_PRITVORA);
        assertThat(testKazna.getUracunavanjePritvora()).isEqualTo(DEFAULT_URACUNAVANJE_PRITVORA);
        assertThat(testKazna.getKolicinaNovca()).isEqualTo(DEFAULT_KOLICINA_NOVCA);
        assertThat(testKazna.getPrimalacNovca()).isEqualTo(UPDATED_PRIMALAC_NOVCA);
        assertThat(testKazna.getNazivImovine()).isEqualTo(UPDATED_NAZIV_IMOVINE);
    }

    @Test
    void fullUpdateKaznaWithPatch() throws Exception {
        // Initialize the database
        kaznaRepository.save(kazna);

        int databaseSizeBeforeUpdate = kaznaRepository.findAll().size();

        // Update the kazna using partial update
        Kazna partialUpdatedKazna = new Kazna();
        partialUpdatedKazna.setId(kazna.getId());

        partialUpdatedKazna
            .tip(UPDATED_TIP)
            .duzinaPritvora(UPDATED_DUZINA_PRITVORA)
            .uracunavanjePritvora(UPDATED_URACUNAVANJE_PRITVORA)
            .kolicinaNovca(UPDATED_KOLICINA_NOVCA)
            .primalacNovca(UPDATED_PRIMALAC_NOVCA)
            .nazivImovine(UPDATED_NAZIV_IMOVINE);

        restKaznaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedKazna.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedKazna))
            )
            .andExpect(status().isOk());

        // Validate the Kazna in the database
        List<Kazna> kaznaList = kaznaRepository.findAll();
        assertThat(kaznaList).hasSize(databaseSizeBeforeUpdate);
        Kazna testKazna = kaznaList.get(kaznaList.size() - 1);
        assertThat(testKazna.getTip()).isEqualTo(UPDATED_TIP);
        assertThat(testKazna.getDuzinaPritvora()).isEqualTo(UPDATED_DUZINA_PRITVORA);
        assertThat(testKazna.getUracunavanjePritvora()).isEqualTo(UPDATED_URACUNAVANJE_PRITVORA);
        assertThat(testKazna.getKolicinaNovca()).isEqualTo(UPDATED_KOLICINA_NOVCA);
        assertThat(testKazna.getPrimalacNovca()).isEqualTo(UPDATED_PRIMALAC_NOVCA);
        assertThat(testKazna.getNazivImovine()).isEqualTo(UPDATED_NAZIV_IMOVINE);
    }

    @Test
    void patchNonExistingKazna() throws Exception {
        int databaseSizeBeforeUpdate = kaznaRepository.findAll().size();
        kazna.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKaznaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, kazna.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(kazna))
            )
            .andExpect(status().isBadRequest());

        // Validate the Kazna in the database
        List<Kazna> kaznaList = kaznaRepository.findAll();
        assertThat(kaznaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchKazna() throws Exception {
        int databaseSizeBeforeUpdate = kaznaRepository.findAll().size();
        kazna.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restKaznaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(kazna))
            )
            .andExpect(status().isBadRequest());

        // Validate the Kazna in the database
        List<Kazna> kaznaList = kaznaRepository.findAll();
        assertThat(kaznaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamKazna() throws Exception {
        int databaseSizeBeforeUpdate = kaznaRepository.findAll().size();
        kazna.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restKaznaMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(kazna)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Kazna in the database
        List<Kazna> kaznaList = kaznaRepository.findAll();
        assertThat(kaznaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteKazna() throws Exception {
        // Initialize the database
        kaznaRepository.save(kazna);

        int databaseSizeBeforeDelete = kaznaRepository.findAll().size();

        // Delete the kazna
        restKaznaMockMvc
            .perform(delete(ENTITY_API_URL_ID, kazna.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Kazna> kaznaList = kaznaRepository.findAll();
        assertThat(kaznaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
