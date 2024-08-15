package pravna.com.myapp.domain.enumeration;

/**
 * The TipPresude enumeration.
 */
public enum TipPresude {
    PRVOSTEPENI_KRIVICNI_PREDMET("K - Prvostepeni krivični predmet"),
    SPECIJALNI_KRIVICNI_PREDMET("K-S - Specijalni krivični predmet"),
    PREDMET_SUDIJE_ZA_ISTRAGU("Ki - Predmet sudije za istragu"),
    SPECIJALNI_ISTRAZNI_PREDMET("Ki-S - Specijalni istražni predmet"),
    PRIPREMNI_POSTUPAK_PREMA_MALOLETNICIMA("Kim - Pripremni postupak prema maloletnicima"),
    KRIVICNI_POSTUPAK_PREMA_MALOLETNICIMA("Km - Krivični postupak prema maloletnicima"),
    PREDMETI_POMILOVANJA("Kp - Predmeti pomilovanja"),
    RAZNA_KRIVICNA_STVAR("Kr - Razna krivična stvar"),
    KRIVICNI_SPECIJALNI("Kr-S - Krivični specijalni"),
    ISTRAZNA_RADNJA("Kri - Istražna radnja"),
    DISCIPLINSKA_ZALBA("Dž - Disciplinarska žalba"),
    ZASTITA_ZAKONITOSTI_U_PARNICNOM_POSTUPKU("Gzz - Zaštita zakonitosti u parničnom postupku"),
    DRUGOSTEPENSKI_GRADJANSKI_PREDMET("Gž - Drugostepeni građanski predmet"),
    PREDMET_IZVRSENJA("I - Predmet izvršenja"),
    PREDMET_O_IZVRSENJU_PREKRSAJNIH_SANKCIJA("IPS - Predmet o izvršenju prekršajnih sankcija i postupaka"),
    IZVRSENJE_PO_VERODOSTOJNOJ_ISPRAVI("IV - Izvršenje po verodostojnoj ispravi"),
    KONTROLNI_ZAHTEV("IV-Su-2 - Kontrolni zahtev"),
    IZUZECE("IV-Su-3 - Izuzeće"),
    IZVRSENJE_KRIVICNIH_SANKCIJA("Iks - Izvršenje krivičnih sankcija");

    private final String value;

    TipPresude(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
