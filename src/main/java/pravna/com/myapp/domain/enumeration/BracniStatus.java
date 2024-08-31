package pravna.com.myapp.domain.enumeration;

/**
 * The BracniStatus enumeration.
 */
public enum BracniStatus {
    VAN_BRAKA("Neoženjen/Neudata"),
    U_BRAKU("Oženjen/Udata"),
    PREKINUT_BRAK("Razveden/Razvedena"),
    SMRT_U_BRAKU("Udovac/Udovica");

    private final String value;

    BracniStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
