package pravna.com.myapp.domain.enumeration;

/**
 * The TipUbistva enumeration.
 */
public enum TipUbistva {
    SA_PREDUMISLJANJEM("Ubistvo sa predumišljajem"),
    BEZ_PREDUMISLJAJA("Ubistvo bez predumišljaja"),
    IZ_NEHATA("Ubistvo iz nehata"),
    NAKON_PORODJAJA("Ubistvo nakon porođaja"),
    IZ_SAMILOSTI("Ubistvo iz samilosti");

    private final String value;

    TipUbistva(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
