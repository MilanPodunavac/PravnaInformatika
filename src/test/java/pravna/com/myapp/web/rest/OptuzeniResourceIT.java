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
import pravna.com.myapp.domain.enumeration.BracniStatus;
import pravna.com.myapp.domain.enumeration.ImovinskoStanje;
import pravna.com.myapp.domain.enumeration.Pol;
import pravna.com.myapp.domain.enumeration.TipObrazovanja;
import pravna.com.myapp.repository.OptuzeniRepository;

/**
 * Integration tests for the {@link OptuzeniResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OptuzeniResourceIT {

    private static final String DEFAULT_IME = "AAAAAAAAAA";
    private static final String UPDATED_IME = "BBBBBBBBBB";

    private static final String DEFAULT_JMBG = "3177638568160";
    private static final String UPDATED_JMBG = "0244339574024";

    private static final String DEFAULT_IME_OCA = "AAAAAAAAAA";
    private static final String UPDATED_IME_OCA = "BBBBBBBBBB";

    private static final String DEFAULT_IME_MAJKE = "AAAAAAAAAA";
    private static final String UPDATED_IME_MAJKE = "BBBBBBBBBB";

    private static final Pol DEFAULT_POL = Pol.MUSKI;
    private static final Pol UPDATED_POL = Pol.ZENSKI;

    private static final LocalDate DEFAULT_DATUM_RODJENJA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATUM_RODJENJA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_MESTO_RODJENJA = "AAAAAAAAAA";
    private static final String UPDATED_MESTO_RODJENJA = "BBBBBBBBBB";

    private static final String DEFAULT_DRZAVA_RODJENJA = "AAAAAAAAAA";
    private static final String UPDATED_DRZAVA_RODJENJA = "BBBBBBBBBB";

    private static final String DEFAULT_PREBIVALISTE = "AAAAAAAAAA";
    private static final String UPDATED_PREBIVALISTE = "BBBBBBBBBB";

    private static final BracniStatus DEFAULT_BRACNI_STATUS = BracniStatus.VAN_BRAKA;
    private static final BracniStatus UPDATED_BRACNI_STATUS = BracniStatus.U_BRAKU;

    private static final Integer DEFAULT_BROJ_DECE = 1;
    private static final Integer UPDATED_BROJ_DECE = 2;

    private static final Integer DEFAULT_BROJ_MALOLETNE_DECE = 1;
    private static final Integer UPDATED_BROJ_MALOLETNE_DECE = 2;

    private static final ImovinskoStanje DEFAULT_IMOVINSKO_STANJE = ImovinskoStanje.LOSE;
    private static final ImovinskoStanje UPDATED_IMOVINSKO_STANJE = ImovinskoStanje.SREDNJE;

    private static final TipObrazovanja DEFAULT_OBRAZOVANJE = TipObrazovanja.NEOBRAZOVAN;
    private static final TipObrazovanja UPDATED_OBRAZOVANJE = TipObrazovanja.OSNOVNA_SKOLA;

    private static final String DEFAULT_ZAPOSLENJE = "AAAAAAAAAA";
    private static final String UPDATED_ZAPOSLENJE = "BBBBBBBBBB";

    private static final String DEFAULT_MESTO_ZAPOSLENJA = "AAAAAAAAAA";
    private static final String UPDATED_MESTO_ZAPOSLENJA = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/optuzenis";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private OptuzeniRepository optuzeniRepository;

    @Autowired
    private MockMvc restOptuzeniMockMvc;

    private Optuzeni optuzeni;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Optuzeni createEntity() {
        Optuzeni optuzeni = new Optuzeni()
            .ime(DEFAULT_IME)
            .jmbg(DEFAULT_JMBG)
            .imeOca(DEFAULT_IME_OCA)
            .imeMajke(DEFAULT_IME_MAJKE)
            .pol(DEFAULT_POL)
            .datumRodjenja(DEFAULT_DATUM_RODJENJA)
            .mestoRodjenja(DEFAULT_MESTO_RODJENJA)
            .drzavaRodjenja(DEFAULT_DRZAVA_RODJENJA)
            .prebivaliste(DEFAULT_PREBIVALISTE)
            .bracniStatus(DEFAULT_BRACNI_STATUS)
            .brojDece(DEFAULT_BROJ_DECE)
            .brojMaloletneDece(DEFAULT_BROJ_MALOLETNE_DECE)
            .imovinskoStanje(DEFAULT_IMOVINSKO_STANJE)
            .obrazovanje(DEFAULT_OBRAZOVANJE)
            .zaposlenje(DEFAULT_ZAPOSLENJE)
            .mestoZaposlenja(DEFAULT_MESTO_ZAPOSLENJA);
        return optuzeni;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Optuzeni createUpdatedEntity() {
        Optuzeni optuzeni = new Optuzeni()
            .ime(UPDATED_IME)
            .jmbg(UPDATED_JMBG)
            .imeOca(UPDATED_IME_OCA)
            .imeMajke(UPDATED_IME_MAJKE)
            .pol(UPDATED_POL)
            .datumRodjenja(UPDATED_DATUM_RODJENJA)
            .mestoRodjenja(UPDATED_MESTO_RODJENJA)
            .drzavaRodjenja(UPDATED_DRZAVA_RODJENJA)
            .prebivaliste(UPDATED_PREBIVALISTE)
            .bracniStatus(UPDATED_BRACNI_STATUS)
            .brojDece(UPDATED_BROJ_DECE)
            .brojMaloletneDece(UPDATED_BROJ_MALOLETNE_DECE)
            .imovinskoStanje(UPDATED_IMOVINSKO_STANJE)
            .obrazovanje(UPDATED_OBRAZOVANJE)
            .zaposlenje(UPDATED_ZAPOSLENJE)
            .mestoZaposlenja(UPDATED_MESTO_ZAPOSLENJA);
        return optuzeni;
    }

    @BeforeEach
    public void initTest() {
        optuzeniRepository.deleteAll();
        optuzeni = createEntity();
    }

    @Test
    void createOptuzeni() throws Exception {
        int databaseSizeBeforeCreate = optuzeniRepository.findAll().size();
        // Create the Optuzeni
        restOptuzeniMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(optuzeni)))
            .andExpect(status().isCreated());

        // Validate the Optuzeni in the database
        List<Optuzeni> optuzeniList = optuzeniRepository.findAll();
        assertThat(optuzeniList).hasSize(databaseSizeBeforeCreate + 1);
        Optuzeni testOptuzeni = optuzeniList.get(optuzeniList.size() - 1);
        assertThat(testOptuzeni.getIme()).isEqualTo(DEFAULT_IME);
        assertThat(testOptuzeni.getJmbg()).isEqualTo(DEFAULT_JMBG);
        assertThat(testOptuzeni.getImeOca()).isEqualTo(DEFAULT_IME_OCA);
        assertThat(testOptuzeni.getImeMajke()).isEqualTo(DEFAULT_IME_MAJKE);
        assertThat(testOptuzeni.getPol()).isEqualTo(DEFAULT_POL);
        assertThat(testOptuzeni.getDatumRodjenja()).isEqualTo(DEFAULT_DATUM_RODJENJA);
        assertThat(testOptuzeni.getMestoRodjenja()).isEqualTo(DEFAULT_MESTO_RODJENJA);
        assertThat(testOptuzeni.getDrzavaRodjenja()).isEqualTo(DEFAULT_DRZAVA_RODJENJA);
        assertThat(testOptuzeni.getPrebivaliste()).isEqualTo(DEFAULT_PREBIVALISTE);
        assertThat(testOptuzeni.getBracniStatus()).isEqualTo(DEFAULT_BRACNI_STATUS);
        assertThat(testOptuzeni.getBrojDece()).isEqualTo(DEFAULT_BROJ_DECE);
        assertThat(testOptuzeni.getBrojMaloletneDece()).isEqualTo(DEFAULT_BROJ_MALOLETNE_DECE);
        assertThat(testOptuzeni.getImovinskoStanje()).isEqualTo(DEFAULT_IMOVINSKO_STANJE);
        assertThat(testOptuzeni.getObrazovanje()).isEqualTo(DEFAULT_OBRAZOVANJE);
        assertThat(testOptuzeni.getZaposlenje()).isEqualTo(DEFAULT_ZAPOSLENJE);
        assertThat(testOptuzeni.getMestoZaposlenja()).isEqualTo(DEFAULT_MESTO_ZAPOSLENJA);
    }

    @Test
    void createOptuzeniWithExistingId() throws Exception {
        // Create the Optuzeni with an existing ID
        optuzeni.setId("existing_id");

        int databaseSizeBeforeCreate = optuzeniRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOptuzeniMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(optuzeni)))
            .andExpect(status().isBadRequest());

        // Validate the Optuzeni in the database
        List<Optuzeni> optuzeniList = optuzeniRepository.findAll();
        assertThat(optuzeniList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkImeIsRequired() throws Exception {
        int databaseSizeBeforeTest = optuzeniRepository.findAll().size();
        // set the field null
        optuzeni.setIme(null);

        // Create the Optuzeni, which fails.

        restOptuzeniMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(optuzeni)))
            .andExpect(status().isBadRequest());

        List<Optuzeni> optuzeniList = optuzeniRepository.findAll();
        assertThat(optuzeniList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkJmbgIsRequired() throws Exception {
        int databaseSizeBeforeTest = optuzeniRepository.findAll().size();
        // set the field null
        optuzeni.setJmbg(null);

        // Create the Optuzeni, which fails.

        restOptuzeniMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(optuzeni)))
            .andExpect(status().isBadRequest());

        List<Optuzeni> optuzeniList = optuzeniRepository.findAll();
        assertThat(optuzeniList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllOptuzenis() throws Exception {
        // Initialize the database
        optuzeniRepository.save(optuzeni);

        // Get all the optuzeniList
        restOptuzeniMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(optuzeni.getId())))
            .andExpect(jsonPath("$.[*].ime").value(hasItem(DEFAULT_IME)))
            .andExpect(jsonPath("$.[*].jmbg").value(hasItem(DEFAULT_JMBG)))
            .andExpect(jsonPath("$.[*].imeOca").value(hasItem(DEFAULT_IME_OCA)))
            .andExpect(jsonPath("$.[*].imeMajke").value(hasItem(DEFAULT_IME_MAJKE)))
            .andExpect(jsonPath("$.[*].pol").value(hasItem(DEFAULT_POL.toString())))
            .andExpect(jsonPath("$.[*].datumRodjenja").value(hasItem(DEFAULT_DATUM_RODJENJA.toString())))
            .andExpect(jsonPath("$.[*].mestoRodjenja").value(hasItem(DEFAULT_MESTO_RODJENJA)))
            .andExpect(jsonPath("$.[*].drzavaRodjenja").value(hasItem(DEFAULT_DRZAVA_RODJENJA)))
            .andExpect(jsonPath("$.[*].prebivaliste").value(hasItem(DEFAULT_PREBIVALISTE)))
            .andExpect(jsonPath("$.[*].bracniStatus").value(hasItem(DEFAULT_BRACNI_STATUS.toString())))
            .andExpect(jsonPath("$.[*].brojDece").value(hasItem(DEFAULT_BROJ_DECE)))
            .andExpect(jsonPath("$.[*].brojMaloletneDece").value(hasItem(DEFAULT_BROJ_MALOLETNE_DECE)))
            .andExpect(jsonPath("$.[*].imovinskoStanje").value(hasItem(DEFAULT_IMOVINSKO_STANJE.toString())))
            .andExpect(jsonPath("$.[*].obrazovanje").value(hasItem(DEFAULT_OBRAZOVANJE.toString())))
            .andExpect(jsonPath("$.[*].zaposlenje").value(hasItem(DEFAULT_ZAPOSLENJE)))
            .andExpect(jsonPath("$.[*].mestoZaposlenja").value(hasItem(DEFAULT_MESTO_ZAPOSLENJA)));
    }

    @Test
    void getOptuzeni() throws Exception {
        // Initialize the database
        optuzeniRepository.save(optuzeni);

        // Get the optuzeni
        restOptuzeniMockMvc
            .perform(get(ENTITY_API_URL_ID, optuzeni.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(optuzeni.getId()))
            .andExpect(jsonPath("$.ime").value(DEFAULT_IME))
            .andExpect(jsonPath("$.jmbg").value(DEFAULT_JMBG))
            .andExpect(jsonPath("$.imeOca").value(DEFAULT_IME_OCA))
            .andExpect(jsonPath("$.imeMajke").value(DEFAULT_IME_MAJKE))
            .andExpect(jsonPath("$.pol").value(DEFAULT_POL.toString()))
            .andExpect(jsonPath("$.datumRodjenja").value(DEFAULT_DATUM_RODJENJA.toString()))
            .andExpect(jsonPath("$.mestoRodjenja").value(DEFAULT_MESTO_RODJENJA))
            .andExpect(jsonPath("$.drzavaRodjenja").value(DEFAULT_DRZAVA_RODJENJA))
            .andExpect(jsonPath("$.prebivaliste").value(DEFAULT_PREBIVALISTE))
            .andExpect(jsonPath("$.bracniStatus").value(DEFAULT_BRACNI_STATUS.toString()))
            .andExpect(jsonPath("$.brojDece").value(DEFAULT_BROJ_DECE))
            .andExpect(jsonPath("$.brojMaloletneDece").value(DEFAULT_BROJ_MALOLETNE_DECE))
            .andExpect(jsonPath("$.imovinskoStanje").value(DEFAULT_IMOVINSKO_STANJE.toString()))
            .andExpect(jsonPath("$.obrazovanje").value(DEFAULT_OBRAZOVANJE.toString()))
            .andExpect(jsonPath("$.zaposlenje").value(DEFAULT_ZAPOSLENJE))
            .andExpect(jsonPath("$.mestoZaposlenja").value(DEFAULT_MESTO_ZAPOSLENJA));
    }

    @Test
    void getNonExistingOptuzeni() throws Exception {
        // Get the optuzeni
        restOptuzeniMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingOptuzeni() throws Exception {
        // Initialize the database
        optuzeniRepository.save(optuzeni);

        int databaseSizeBeforeUpdate = optuzeniRepository.findAll().size();

        // Update the optuzeni
        Optuzeni updatedOptuzeni = optuzeniRepository.findById(optuzeni.getId()).get();
        updatedOptuzeni
            .ime(UPDATED_IME)
            .jmbg(UPDATED_JMBG)
            .imeOca(UPDATED_IME_OCA)
            .imeMajke(UPDATED_IME_MAJKE)
            .pol(UPDATED_POL)
            .datumRodjenja(UPDATED_DATUM_RODJENJA)
            .mestoRodjenja(UPDATED_MESTO_RODJENJA)
            .drzavaRodjenja(UPDATED_DRZAVA_RODJENJA)
            .prebivaliste(UPDATED_PREBIVALISTE)
            .bracniStatus(UPDATED_BRACNI_STATUS)
            .brojDece(UPDATED_BROJ_DECE)
            .brojMaloletneDece(UPDATED_BROJ_MALOLETNE_DECE)
            .imovinskoStanje(UPDATED_IMOVINSKO_STANJE)
            .obrazovanje(UPDATED_OBRAZOVANJE)
            .zaposlenje(UPDATED_ZAPOSLENJE)
            .mestoZaposlenja(UPDATED_MESTO_ZAPOSLENJA);

        restOptuzeniMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOptuzeni.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedOptuzeni))
            )
            .andExpect(status().isOk());

        // Validate the Optuzeni in the database
        List<Optuzeni> optuzeniList = optuzeniRepository.findAll();
        assertThat(optuzeniList).hasSize(databaseSizeBeforeUpdate);
        Optuzeni testOptuzeni = optuzeniList.get(optuzeniList.size() - 1);
        assertThat(testOptuzeni.getIme()).isEqualTo(UPDATED_IME);
        assertThat(testOptuzeni.getJmbg()).isEqualTo(UPDATED_JMBG);
        assertThat(testOptuzeni.getImeOca()).isEqualTo(UPDATED_IME_OCA);
        assertThat(testOptuzeni.getImeMajke()).isEqualTo(UPDATED_IME_MAJKE);
        assertThat(testOptuzeni.getPol()).isEqualTo(UPDATED_POL);
        assertThat(testOptuzeni.getDatumRodjenja()).isEqualTo(UPDATED_DATUM_RODJENJA);
        assertThat(testOptuzeni.getMestoRodjenja()).isEqualTo(UPDATED_MESTO_RODJENJA);
        assertThat(testOptuzeni.getDrzavaRodjenja()).isEqualTo(UPDATED_DRZAVA_RODJENJA);
        assertThat(testOptuzeni.getPrebivaliste()).isEqualTo(UPDATED_PREBIVALISTE);
        assertThat(testOptuzeni.getBracniStatus()).isEqualTo(UPDATED_BRACNI_STATUS);
        assertThat(testOptuzeni.getBrojDece()).isEqualTo(UPDATED_BROJ_DECE);
        assertThat(testOptuzeni.getBrojMaloletneDece()).isEqualTo(UPDATED_BROJ_MALOLETNE_DECE);
        assertThat(testOptuzeni.getImovinskoStanje()).isEqualTo(UPDATED_IMOVINSKO_STANJE);
        assertThat(testOptuzeni.getObrazovanje()).isEqualTo(UPDATED_OBRAZOVANJE);
        assertThat(testOptuzeni.getZaposlenje()).isEqualTo(UPDATED_ZAPOSLENJE);
        assertThat(testOptuzeni.getMestoZaposlenja()).isEqualTo(UPDATED_MESTO_ZAPOSLENJA);
    }

    @Test
    void putNonExistingOptuzeni() throws Exception {
        int databaseSizeBeforeUpdate = optuzeniRepository.findAll().size();
        optuzeni.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOptuzeniMockMvc
            .perform(
                put(ENTITY_API_URL_ID, optuzeni.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(optuzeni))
            )
            .andExpect(status().isBadRequest());

        // Validate the Optuzeni in the database
        List<Optuzeni> optuzeniList = optuzeniRepository.findAll();
        assertThat(optuzeniList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchOptuzeni() throws Exception {
        int databaseSizeBeforeUpdate = optuzeniRepository.findAll().size();
        optuzeni.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOptuzeniMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(optuzeni))
            )
            .andExpect(status().isBadRequest());

        // Validate the Optuzeni in the database
        List<Optuzeni> optuzeniList = optuzeniRepository.findAll();
        assertThat(optuzeniList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamOptuzeni() throws Exception {
        int databaseSizeBeforeUpdate = optuzeniRepository.findAll().size();
        optuzeni.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOptuzeniMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(optuzeni)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Optuzeni in the database
        List<Optuzeni> optuzeniList = optuzeniRepository.findAll();
        assertThat(optuzeniList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateOptuzeniWithPatch() throws Exception {
        // Initialize the database
        optuzeniRepository.save(optuzeni);

        int databaseSizeBeforeUpdate = optuzeniRepository.findAll().size();

        // Update the optuzeni using partial update
        Optuzeni partialUpdatedOptuzeni = new Optuzeni();
        partialUpdatedOptuzeni.setId(optuzeni.getId());

        partialUpdatedOptuzeni
            .jmbg(UPDATED_JMBG)
            .imeOca(UPDATED_IME_OCA)
            .imeMajke(UPDATED_IME_MAJKE)
            .mestoRodjenja(UPDATED_MESTO_RODJENJA)
            .drzavaRodjenja(UPDATED_DRZAVA_RODJENJA)
            .bracniStatus(UPDATED_BRACNI_STATUS)
            .brojDece(UPDATED_BROJ_DECE)
            .brojMaloletneDece(UPDATED_BROJ_MALOLETNE_DECE)
            .zaposlenje(UPDATED_ZAPOSLENJE);

        restOptuzeniMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOptuzeni.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOptuzeni))
            )
            .andExpect(status().isOk());

        // Validate the Optuzeni in the database
        List<Optuzeni> optuzeniList = optuzeniRepository.findAll();
        assertThat(optuzeniList).hasSize(databaseSizeBeforeUpdate);
        Optuzeni testOptuzeni = optuzeniList.get(optuzeniList.size() - 1);
        assertThat(testOptuzeni.getIme()).isEqualTo(DEFAULT_IME);
        assertThat(testOptuzeni.getJmbg()).isEqualTo(UPDATED_JMBG);
        assertThat(testOptuzeni.getImeOca()).isEqualTo(UPDATED_IME_OCA);
        assertThat(testOptuzeni.getImeMajke()).isEqualTo(UPDATED_IME_MAJKE);
        assertThat(testOptuzeni.getPol()).isEqualTo(DEFAULT_POL);
        assertThat(testOptuzeni.getDatumRodjenja()).isEqualTo(DEFAULT_DATUM_RODJENJA);
        assertThat(testOptuzeni.getMestoRodjenja()).isEqualTo(UPDATED_MESTO_RODJENJA);
        assertThat(testOptuzeni.getDrzavaRodjenja()).isEqualTo(UPDATED_DRZAVA_RODJENJA);
        assertThat(testOptuzeni.getPrebivaliste()).isEqualTo(DEFAULT_PREBIVALISTE);
        assertThat(testOptuzeni.getBracniStatus()).isEqualTo(UPDATED_BRACNI_STATUS);
        assertThat(testOptuzeni.getBrojDece()).isEqualTo(UPDATED_BROJ_DECE);
        assertThat(testOptuzeni.getBrojMaloletneDece()).isEqualTo(UPDATED_BROJ_MALOLETNE_DECE);
        assertThat(testOptuzeni.getImovinskoStanje()).isEqualTo(DEFAULT_IMOVINSKO_STANJE);
        assertThat(testOptuzeni.getObrazovanje()).isEqualTo(DEFAULT_OBRAZOVANJE);
        assertThat(testOptuzeni.getZaposlenje()).isEqualTo(UPDATED_ZAPOSLENJE);
        assertThat(testOptuzeni.getMestoZaposlenja()).isEqualTo(DEFAULT_MESTO_ZAPOSLENJA);
    }

    @Test
    void fullUpdateOptuzeniWithPatch() throws Exception {
        // Initialize the database
        optuzeniRepository.save(optuzeni);

        int databaseSizeBeforeUpdate = optuzeniRepository.findAll().size();

        // Update the optuzeni using partial update
        Optuzeni partialUpdatedOptuzeni = new Optuzeni();
        partialUpdatedOptuzeni.setId(optuzeni.getId());

        partialUpdatedOptuzeni
            .ime(UPDATED_IME)
            .jmbg(UPDATED_JMBG)
            .imeOca(UPDATED_IME_OCA)
            .imeMajke(UPDATED_IME_MAJKE)
            .pol(UPDATED_POL)
            .datumRodjenja(UPDATED_DATUM_RODJENJA)
            .mestoRodjenja(UPDATED_MESTO_RODJENJA)
            .drzavaRodjenja(UPDATED_DRZAVA_RODJENJA)
            .prebivaliste(UPDATED_PREBIVALISTE)
            .bracniStatus(UPDATED_BRACNI_STATUS)
            .brojDece(UPDATED_BROJ_DECE)
            .brojMaloletneDece(UPDATED_BROJ_MALOLETNE_DECE)
            .imovinskoStanje(UPDATED_IMOVINSKO_STANJE)
            .obrazovanje(UPDATED_OBRAZOVANJE)
            .zaposlenje(UPDATED_ZAPOSLENJE)
            .mestoZaposlenja(UPDATED_MESTO_ZAPOSLENJA);

        restOptuzeniMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOptuzeni.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOptuzeni))
            )
            .andExpect(status().isOk());

        // Validate the Optuzeni in the database
        List<Optuzeni> optuzeniList = optuzeniRepository.findAll();
        assertThat(optuzeniList).hasSize(databaseSizeBeforeUpdate);
        Optuzeni testOptuzeni = optuzeniList.get(optuzeniList.size() - 1);
        assertThat(testOptuzeni.getIme()).isEqualTo(UPDATED_IME);
        assertThat(testOptuzeni.getJmbg()).isEqualTo(UPDATED_JMBG);
        assertThat(testOptuzeni.getImeOca()).isEqualTo(UPDATED_IME_OCA);
        assertThat(testOptuzeni.getImeMajke()).isEqualTo(UPDATED_IME_MAJKE);
        assertThat(testOptuzeni.getPol()).isEqualTo(UPDATED_POL);
        assertThat(testOptuzeni.getDatumRodjenja()).isEqualTo(UPDATED_DATUM_RODJENJA);
        assertThat(testOptuzeni.getMestoRodjenja()).isEqualTo(UPDATED_MESTO_RODJENJA);
        assertThat(testOptuzeni.getDrzavaRodjenja()).isEqualTo(UPDATED_DRZAVA_RODJENJA);
        assertThat(testOptuzeni.getPrebivaliste()).isEqualTo(UPDATED_PREBIVALISTE);
        assertThat(testOptuzeni.getBracniStatus()).isEqualTo(UPDATED_BRACNI_STATUS);
        assertThat(testOptuzeni.getBrojDece()).isEqualTo(UPDATED_BROJ_DECE);
        assertThat(testOptuzeni.getBrojMaloletneDece()).isEqualTo(UPDATED_BROJ_MALOLETNE_DECE);
        assertThat(testOptuzeni.getImovinskoStanje()).isEqualTo(UPDATED_IMOVINSKO_STANJE);
        assertThat(testOptuzeni.getObrazovanje()).isEqualTo(UPDATED_OBRAZOVANJE);
        assertThat(testOptuzeni.getZaposlenje()).isEqualTo(UPDATED_ZAPOSLENJE);
        assertThat(testOptuzeni.getMestoZaposlenja()).isEqualTo(UPDATED_MESTO_ZAPOSLENJA);
    }

    @Test
    void patchNonExistingOptuzeni() throws Exception {
        int databaseSizeBeforeUpdate = optuzeniRepository.findAll().size();
        optuzeni.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOptuzeniMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, optuzeni.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(optuzeni))
            )
            .andExpect(status().isBadRequest());

        // Validate the Optuzeni in the database
        List<Optuzeni> optuzeniList = optuzeniRepository.findAll();
        assertThat(optuzeniList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchOptuzeni() throws Exception {
        int databaseSizeBeforeUpdate = optuzeniRepository.findAll().size();
        optuzeni.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOptuzeniMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(optuzeni))
            )
            .andExpect(status().isBadRequest());

        // Validate the Optuzeni in the database
        List<Optuzeni> optuzeniList = optuzeniRepository.findAll();
        assertThat(optuzeniList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamOptuzeni() throws Exception {
        int databaseSizeBeforeUpdate = optuzeniRepository.findAll().size();
        optuzeni.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOptuzeniMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(optuzeni)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Optuzeni in the database
        List<Optuzeni> optuzeniList = optuzeniRepository.findAll();
        assertThat(optuzeniList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteOptuzeni() throws Exception {
        // Initialize the database
        optuzeniRepository.save(optuzeni);

        int databaseSizeBeforeDelete = optuzeniRepository.findAll().size();

        // Delete the optuzeni
        restOptuzeniMockMvc
            .perform(delete(ENTITY_API_URL_ID, optuzeni.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Optuzeni> optuzeniList = optuzeniRepository.findAll();
        assertThat(optuzeniList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
