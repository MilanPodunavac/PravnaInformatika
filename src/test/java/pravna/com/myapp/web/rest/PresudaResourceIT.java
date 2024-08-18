package pravna.com.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
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
import pravna.com.myapp.domain.Optuzeni;
import pravna.com.myapp.domain.Osoba;
import pravna.com.myapp.domain.Presuda;
import pravna.com.myapp.domain.RadnjaPresude;
import pravna.com.myapp.domain.enumeration.TipPresude;
import pravna.com.myapp.domain.enumeration.TipUbistva;
import pravna.com.myapp.repository.PresudaRepository;
import pravna.com.myapp.service.dto.PresudaDTO;
import pravna.com.myapp.service.mapper.PresudaMapper;

/**
 * Integration tests for the {@link PresudaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PresudaResourceIT {

    private static final String DEFAULT_TEKST = "AAAAAAAAAA";
    private static final String UPDATED_TEKST = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATUM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATUM = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATUM_OBJAVE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATUM_OBJAVE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATUM_PRITVORA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATUM_PRITVORA = LocalDate.now(ZoneId.systemDefault());

    private static final TipPresude DEFAULT_TIP = TipPresude.PRVOSTEPENI_KRIVICNI_PREDMET;
    private static final TipPresude UPDATED_TIP = TipPresude.SPECIJALNI_KRIVICNI_PREDMET;

    private static final Integer DEFAULT_BROJ = 1;
    private static final Integer UPDATED_BROJ = 2;

    private static final Integer DEFAULT_GODINA = 1;
    private static final Integer UPDATED_GODINA = 2;

    private static final String DEFAULT_OPTUZNICA = "AAAAAAAAAA";
    private static final String UPDATED_OPTUZNICA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATUM_OPTUZNICE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATUM_OPTUZNICE = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_POKUSAJ = false;
    private static final Boolean UPDATED_POKUSAJ = true;

    private static final Boolean DEFAULT_KRIVICA = false;
    private static final Boolean UPDATED_KRIVICA = true;

    private static final TipUbistva DEFAULT_NACIN = TipUbistva.SA_PREDUMISLJANJEM;
    private static final TipUbistva UPDATED_NACIN = TipUbistva.BEZ_PREDUMISLJAJA;

    private static final String ENTITY_API_URL = "/api/presudas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private PresudaRepository presudaRepository;

    @Autowired
    private PresudaMapper presudaMapper;

    @Autowired
    private MockMvc restPresudaMockMvc;

    private Presuda presuda;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Presuda createEntity() {
        Presuda presuda = new Presuda()
            .tekst(DEFAULT_TEKST)
            .datum(DEFAULT_DATUM)
            .datumObjave(DEFAULT_DATUM_OBJAVE)
            .datumPritvora(DEFAULT_DATUM_PRITVORA)
            .tip(DEFAULT_TIP)
            .broj(DEFAULT_BROJ)
            .godina(DEFAULT_GODINA)
            .optuznica(DEFAULT_OPTUZNICA)
            .datumOptuznice(DEFAULT_DATUM_OPTUZNICE)
            .pokusaj(DEFAULT_POKUSAJ)
            .krivica(DEFAULT_KRIVICA)
            .nacin(DEFAULT_NACIN);
        // Add required entity
        RadnjaPresude radnjaPresude;
        radnjaPresude = RadnjaPresudeResourceIT.createEntity();
        radnjaPresude.setId("fixed-id-for-tests");
        presuda.setRadnja(radnjaPresude);
        // Add required entity
        Optuzeni optuzeni;
        optuzeni = OptuzeniResourceIT.createEntity();
        optuzeni.setId("fixed-id-for-tests");
        presuda.setOptuzeni(optuzeni);
        // Add required entity
        Osoba osoba;
        osoba = OsobaResourceIT.createEntity();
        osoba.setId("fixed-id-for-tests");
        presuda.setSudija(osoba);
        // Add required entity
        presuda.setZapisnicar(osoba);
        // Add required entity
        presuda.setTuzilac(osoba);
        // Add required entity
        presuda.setBranilac(osoba);
        return presuda;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Presuda createUpdatedEntity() {
        Presuda presuda = new Presuda()
            .tekst(UPDATED_TEKST)
            .datum(UPDATED_DATUM)
            .datumObjave(UPDATED_DATUM_OBJAVE)
            .datumPritvora(UPDATED_DATUM_PRITVORA)
            .tip(UPDATED_TIP)
            .broj(UPDATED_BROJ)
            .godina(UPDATED_GODINA)
            .optuznica(UPDATED_OPTUZNICA)
            .datumOptuznice(UPDATED_DATUM_OPTUZNICE)
            .pokusaj(UPDATED_POKUSAJ)
            .krivica(UPDATED_KRIVICA)
            .nacin(UPDATED_NACIN);
        // Add required entity
        RadnjaPresude radnjaPresude;
        radnjaPresude = RadnjaPresudeResourceIT.createUpdatedEntity();
        radnjaPresude.setId("fixed-id-for-tests");
        presuda.setRadnja(radnjaPresude);
        // Add required entity
        Optuzeni optuzeni;
        optuzeni = OptuzeniResourceIT.createUpdatedEntity();
        optuzeni.setId("fixed-id-for-tests");
        presuda.setOptuzeni(optuzeni);
        // Add required entity
        Osoba osoba;
        osoba = OsobaResourceIT.createUpdatedEntity();
        osoba.setId("fixed-id-for-tests");
        presuda.setSudija(osoba);
        // Add required entity
        presuda.setZapisnicar(osoba);
        // Add required entity
        presuda.setTuzilac(osoba);
        // Add required entity
        presuda.setBranilac(osoba);
        return presuda;
    }

    @BeforeEach
    public void initTest() {
        presudaRepository.deleteAll();
        presuda = createEntity();
    }

    @Test
    void createPresuda() throws Exception {
        int databaseSizeBeforeCreate = presudaRepository.findAll().size();
        // Create the Presuda
        PresudaDTO presudaDTO = presudaMapper.toDto(presuda);
        restPresudaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(presudaDTO)))
            .andExpect(status().isCreated());

        // Validate the Presuda in the database
        List<Presuda> presudaList = presudaRepository.findAll();
        assertThat(presudaList).hasSize(databaseSizeBeforeCreate + 1);
        Presuda testPresuda = presudaList.get(presudaList.size() - 1);
        assertThat(testPresuda.getTekst()).isEqualTo(DEFAULT_TEKST);
        assertThat(testPresuda.getDatum()).isEqualTo(DEFAULT_DATUM);
        assertThat(testPresuda.getDatumObjave()).isEqualTo(DEFAULT_DATUM_OBJAVE);
        assertThat(testPresuda.getDatumPritvora()).isEqualTo(DEFAULT_DATUM_PRITVORA);
        assertThat(testPresuda.getTip()).isEqualTo(DEFAULT_TIP);
        assertThat(testPresuda.getBroj()).isEqualTo(DEFAULT_BROJ);
        assertThat(testPresuda.getGodina()).isEqualTo(DEFAULT_GODINA);
        assertThat(testPresuda.getOptuznica()).isEqualTo(DEFAULT_OPTUZNICA);
        assertThat(testPresuda.getDatumOptuznice()).isEqualTo(DEFAULT_DATUM_OPTUZNICE);
        assertThat(testPresuda.getPokusaj()).isEqualTo(DEFAULT_POKUSAJ);
        assertThat(testPresuda.getKrivica()).isEqualTo(DEFAULT_KRIVICA);
        assertThat(testPresuda.getNacin()).isEqualTo(DEFAULT_NACIN);
    }

    @Test
    void createPresudaWithExistingId() throws Exception {
        // Create the Presuda with an existing ID
        presuda.setId("existing_id");
        PresudaDTO presudaDTO = presudaMapper.toDto(presuda);

        int databaseSizeBeforeCreate = presudaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPresudaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(presudaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Presuda in the database
        List<Presuda> presudaList = presudaRepository.findAll();
        assertThat(presudaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkDatumIsRequired() throws Exception {
        int databaseSizeBeforeTest = presudaRepository.findAll().size();
        // set the field null
        presuda.setDatum(null);

        // Create the Presuda, which fails.
        PresudaDTO presudaDTO = presudaMapper.toDto(presuda);

        restPresudaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(presudaDTO)))
            .andExpect(status().isBadRequest());

        List<Presuda> presudaList = presudaRepository.findAll();
        assertThat(presudaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTipIsRequired() throws Exception {
        int databaseSizeBeforeTest = presudaRepository.findAll().size();
        // set the field null
        presuda.setTip(null);

        // Create the Presuda, which fails.
        PresudaDTO presudaDTO = presudaMapper.toDto(presuda);

        restPresudaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(presudaDTO)))
            .andExpect(status().isBadRequest());

        List<Presuda> presudaList = presudaRepository.findAll();
        assertThat(presudaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkBrojIsRequired() throws Exception {
        int databaseSizeBeforeTest = presudaRepository.findAll().size();
        // set the field null
        presuda.setBroj(null);

        // Create the Presuda, which fails.
        PresudaDTO presudaDTO = presudaMapper.toDto(presuda);

        restPresudaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(presudaDTO)))
            .andExpect(status().isBadRequest());

        List<Presuda> presudaList = presudaRepository.findAll();
        assertThat(presudaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkGodinaIsRequired() throws Exception {
        int databaseSizeBeforeTest = presudaRepository.findAll().size();
        // set the field null
        presuda.setGodina(null);

        // Create the Presuda, which fails.
        PresudaDTO presudaDTO = presudaMapper.toDto(presuda);

        restPresudaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(presudaDTO)))
            .andExpect(status().isBadRequest());

        List<Presuda> presudaList = presudaRepository.findAll();
        assertThat(presudaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllPresudas() throws Exception {
        // Initialize the database
        presudaRepository.save(presuda);

        // Get all the presudaList
        restPresudaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(presuda.getId())))
            .andExpect(jsonPath("$.[*].tekst").value(hasItem(DEFAULT_TEKST)))
            .andExpect(jsonPath("$.[*].datum").value(hasItem(DEFAULT_DATUM.toString())))
            .andExpect(jsonPath("$.[*].datumObjave").value(hasItem(DEFAULT_DATUM_OBJAVE.toString())))
            .andExpect(jsonPath("$.[*].datumPritvora").value(hasItem(DEFAULT_DATUM_PRITVORA.toString())))
            .andExpect(jsonPath("$.[*].tip").value(hasItem(DEFAULT_TIP.toString())))
            .andExpect(jsonPath("$.[*].broj").value(hasItem(DEFAULT_BROJ)))
            .andExpect(jsonPath("$.[*].godina").value(hasItem(DEFAULT_GODINA)))
            .andExpect(jsonPath("$.[*].optuznica").value(hasItem(DEFAULT_OPTUZNICA)))
            .andExpect(jsonPath("$.[*].datumOptuznice").value(hasItem(DEFAULT_DATUM_OPTUZNICE.toString())))
            .andExpect(jsonPath("$.[*].pokusaj").value(hasItem(DEFAULT_POKUSAJ.booleanValue())))
            .andExpect(jsonPath("$.[*].krivica").value(hasItem(DEFAULT_KRIVICA.booleanValue())))
            .andExpect(jsonPath("$.[*].nacin").value(hasItem(DEFAULT_NACIN.toString())));
    }

    @Test
    void getPresuda() throws Exception {
        // Initialize the database
        presudaRepository.save(presuda);

        // Get the presuda
        restPresudaMockMvc
            .perform(get(ENTITY_API_URL_ID, presuda.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(presuda.getId()))
            .andExpect(jsonPath("$.tekst").value(DEFAULT_TEKST))
            .andExpect(jsonPath("$.datum").value(DEFAULT_DATUM.toString()))
            .andExpect(jsonPath("$.datumObjave").value(DEFAULT_DATUM_OBJAVE.toString()))
            .andExpect(jsonPath("$.datumPritvora").value(DEFAULT_DATUM_PRITVORA.toString()))
            .andExpect(jsonPath("$.tip").value(DEFAULT_TIP.toString()))
            .andExpect(jsonPath("$.broj").value(DEFAULT_BROJ))
            .andExpect(jsonPath("$.godina").value(DEFAULT_GODINA))
            .andExpect(jsonPath("$.optuznica").value(DEFAULT_OPTUZNICA))
            .andExpect(jsonPath("$.datumOptuznice").value(DEFAULT_DATUM_OPTUZNICE.toString()))
            .andExpect(jsonPath("$.pokusaj").value(DEFAULT_POKUSAJ.booleanValue()))
            .andExpect(jsonPath("$.krivica").value(DEFAULT_KRIVICA.booleanValue()))
            .andExpect(jsonPath("$.nacin").value(DEFAULT_NACIN.toString()));
    }

    @Test
    void getNonExistingPresuda() throws Exception {
        // Get the presuda
        restPresudaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingPresuda() throws Exception {
        // Initialize the database
        presudaRepository.save(presuda);

        int databaseSizeBeforeUpdate = presudaRepository.findAll().size();

        // Update the presuda
        Presuda updatedPresuda = presudaRepository.findById(presuda.getId()).get();
        updatedPresuda
            .tekst(UPDATED_TEKST)
            .datum(UPDATED_DATUM)
            .datumObjave(UPDATED_DATUM_OBJAVE)
            .datumPritvora(UPDATED_DATUM_PRITVORA)
            .tip(UPDATED_TIP)
            .broj(UPDATED_BROJ)
            .godina(UPDATED_GODINA)
            .optuznica(UPDATED_OPTUZNICA)
            .datumOptuznice(UPDATED_DATUM_OPTUZNICE)
            .pokusaj(UPDATED_POKUSAJ)
            .krivica(UPDATED_KRIVICA)
            .nacin(UPDATED_NACIN);
        PresudaDTO presudaDTO = presudaMapper.toDto(updatedPresuda);

        restPresudaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, presudaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(presudaDTO))
            )
            .andExpect(status().isOk());

        // Validate the Presuda in the database
        List<Presuda> presudaList = presudaRepository.findAll();
        assertThat(presudaList).hasSize(databaseSizeBeforeUpdate);
        Presuda testPresuda = presudaList.get(presudaList.size() - 1);
        assertThat(testPresuda.getTekst()).isEqualTo(UPDATED_TEKST);
        assertThat(testPresuda.getDatum()).isEqualTo(UPDATED_DATUM);
        assertThat(testPresuda.getDatumObjave()).isEqualTo(UPDATED_DATUM_OBJAVE);
        assertThat(testPresuda.getDatumPritvora()).isEqualTo(UPDATED_DATUM_PRITVORA);
        assertThat(testPresuda.getTip()).isEqualTo(UPDATED_TIP);
        assertThat(testPresuda.getBroj()).isEqualTo(UPDATED_BROJ);
        assertThat(testPresuda.getGodina()).isEqualTo(UPDATED_GODINA);
        assertThat(testPresuda.getOptuznica()).isEqualTo(UPDATED_OPTUZNICA);
        assertThat(testPresuda.getDatumOptuznice()).isEqualTo(UPDATED_DATUM_OPTUZNICE);
        assertThat(testPresuda.getPokusaj()).isEqualTo(UPDATED_POKUSAJ);
        assertThat(testPresuda.getKrivica()).isEqualTo(UPDATED_KRIVICA);
        assertThat(testPresuda.getNacin()).isEqualTo(UPDATED_NACIN);
    }

    @Test
    void putNonExistingPresuda() throws Exception {
        int databaseSizeBeforeUpdate = presudaRepository.findAll().size();
        presuda.setId(UUID.randomUUID().toString());

        // Create the Presuda
        PresudaDTO presudaDTO = presudaMapper.toDto(presuda);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPresudaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, presudaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(presudaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Presuda in the database
        List<Presuda> presudaList = presudaRepository.findAll();
        assertThat(presudaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPresuda() throws Exception {
        int databaseSizeBeforeUpdate = presudaRepository.findAll().size();
        presuda.setId(UUID.randomUUID().toString());

        // Create the Presuda
        PresudaDTO presudaDTO = presudaMapper.toDto(presuda);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPresudaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(presudaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Presuda in the database
        List<Presuda> presudaList = presudaRepository.findAll();
        assertThat(presudaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPresuda() throws Exception {
        int databaseSizeBeforeUpdate = presudaRepository.findAll().size();
        presuda.setId(UUID.randomUUID().toString());

        // Create the Presuda
        PresudaDTO presudaDTO = presudaMapper.toDto(presuda);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPresudaMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(presudaDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Presuda in the database
        List<Presuda> presudaList = presudaRepository.findAll();
        assertThat(presudaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePresudaWithPatch() throws Exception {
        // Initialize the database
        presudaRepository.save(presuda);

        int databaseSizeBeforeUpdate = presudaRepository.findAll().size();

        // Update the presuda using partial update
        Presuda partialUpdatedPresuda = new Presuda();
        partialUpdatedPresuda.setId(presuda.getId());

        partialUpdatedPresuda.datumObjave(UPDATED_DATUM_OBJAVE).tip(UPDATED_TIP).broj(UPDATED_BROJ).nacin(UPDATED_NACIN);

        restPresudaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPresuda.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPresuda))
            )
            .andExpect(status().isOk());

        // Validate the Presuda in the database
        List<Presuda> presudaList = presudaRepository.findAll();
        assertThat(presudaList).hasSize(databaseSizeBeforeUpdate);
        Presuda testPresuda = presudaList.get(presudaList.size() - 1);
        assertThat(testPresuda.getTekst()).isEqualTo(DEFAULT_TEKST);
        assertThat(testPresuda.getDatum()).isEqualTo(DEFAULT_DATUM);
        assertThat(testPresuda.getDatumObjave()).isEqualTo(UPDATED_DATUM_OBJAVE);
        assertThat(testPresuda.getDatumPritvora()).isEqualTo(DEFAULT_DATUM_PRITVORA);
        assertThat(testPresuda.getTip()).isEqualTo(UPDATED_TIP);
        assertThat(testPresuda.getBroj()).isEqualTo(UPDATED_BROJ);
        assertThat(testPresuda.getGodina()).isEqualTo(DEFAULT_GODINA);
        assertThat(testPresuda.getOptuznica()).isEqualTo(DEFAULT_OPTUZNICA);
        assertThat(testPresuda.getDatumOptuznice()).isEqualTo(DEFAULT_DATUM_OPTUZNICE);
        assertThat(testPresuda.getPokusaj()).isEqualTo(DEFAULT_POKUSAJ);
        assertThat(testPresuda.getKrivica()).isEqualTo(DEFAULT_KRIVICA);
        assertThat(testPresuda.getNacin()).isEqualTo(UPDATED_NACIN);
    }

    @Test
    void fullUpdatePresudaWithPatch() throws Exception {
        // Initialize the database
        presudaRepository.save(presuda);

        int databaseSizeBeforeUpdate = presudaRepository.findAll().size();

        // Update the presuda using partial update
        Presuda partialUpdatedPresuda = new Presuda();
        partialUpdatedPresuda.setId(presuda.getId());

        partialUpdatedPresuda
            .tekst(UPDATED_TEKST)
            .datum(UPDATED_DATUM)
            .datumObjave(UPDATED_DATUM_OBJAVE)
            .datumPritvora(UPDATED_DATUM_PRITVORA)
            .tip(UPDATED_TIP)
            .broj(UPDATED_BROJ)
            .godina(UPDATED_GODINA)
            .optuznica(UPDATED_OPTUZNICA)
            .datumOptuznice(UPDATED_DATUM_OPTUZNICE)
            .pokusaj(UPDATED_POKUSAJ)
            .krivica(UPDATED_KRIVICA)
            .nacin(UPDATED_NACIN);

        restPresudaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPresuda.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPresuda))
            )
            .andExpect(status().isOk());

        // Validate the Presuda in the database
        List<Presuda> presudaList = presudaRepository.findAll();
        assertThat(presudaList).hasSize(databaseSizeBeforeUpdate);
        Presuda testPresuda = presudaList.get(presudaList.size() - 1);
        assertThat(testPresuda.getTekst()).isEqualTo(UPDATED_TEKST);
        assertThat(testPresuda.getDatum()).isEqualTo(UPDATED_DATUM);
        assertThat(testPresuda.getDatumObjave()).isEqualTo(UPDATED_DATUM_OBJAVE);
        assertThat(testPresuda.getDatumPritvora()).isEqualTo(UPDATED_DATUM_PRITVORA);
        assertThat(testPresuda.getTip()).isEqualTo(UPDATED_TIP);
        assertThat(testPresuda.getBroj()).isEqualTo(UPDATED_BROJ);
        assertThat(testPresuda.getGodina()).isEqualTo(UPDATED_GODINA);
        assertThat(testPresuda.getOptuznica()).isEqualTo(UPDATED_OPTUZNICA);
        assertThat(testPresuda.getDatumOptuznice()).isEqualTo(UPDATED_DATUM_OPTUZNICE);
        assertThat(testPresuda.getPokusaj()).isEqualTo(UPDATED_POKUSAJ);
        assertThat(testPresuda.getKrivica()).isEqualTo(UPDATED_KRIVICA);
        assertThat(testPresuda.getNacin()).isEqualTo(UPDATED_NACIN);
    }

    @Test
    void patchNonExistingPresuda() throws Exception {
        int databaseSizeBeforeUpdate = presudaRepository.findAll().size();
        presuda.setId(UUID.randomUUID().toString());

        // Create the Presuda
        PresudaDTO presudaDTO = presudaMapper.toDto(presuda);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPresudaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, presudaDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(presudaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Presuda in the database
        List<Presuda> presudaList = presudaRepository.findAll();
        assertThat(presudaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPresuda() throws Exception {
        int databaseSizeBeforeUpdate = presudaRepository.findAll().size();
        presuda.setId(UUID.randomUUID().toString());

        // Create the Presuda
        PresudaDTO presudaDTO = presudaMapper.toDto(presuda);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPresudaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(presudaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Presuda in the database
        List<Presuda> presudaList = presudaRepository.findAll();
        assertThat(presudaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPresuda() throws Exception {
        int databaseSizeBeforeUpdate = presudaRepository.findAll().size();
        presuda.setId(UUID.randomUUID().toString());

        // Create the Presuda
        PresudaDTO presudaDTO = presudaMapper.toDto(presuda);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPresudaMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(presudaDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Presuda in the database
        List<Presuda> presudaList = presudaRepository.findAll();
        assertThat(presudaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePresuda() throws Exception {
        // Initialize the database
        presudaRepository.save(presuda);

        int databaseSizeBeforeDelete = presudaRepository.findAll().size();

        // Delete the presuda
        restPresudaMockMvc
            .perform(delete(ENTITY_API_URL_ID, presuda.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Presuda> presudaList = presudaRepository.findAll();
        assertThat(presudaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
