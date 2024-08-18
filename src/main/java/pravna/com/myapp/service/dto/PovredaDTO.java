package pravna.com.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link pravna.com.myapp.domain.Povreda} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PovredaDTO implements Serializable {

    private String id;

    @NotNull
    private String oruzje;

    @NotNull
    private String deoTela;

    @NotNull
    private String povrede;

    private RadnjaPresudeDTO radnja;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOruzje() {
        return oruzje;
    }

    public void setOruzje(String oruzje) {
        this.oruzje = oruzje;
    }

    public String getDeoTela() {
        return deoTela;
    }

    public void setDeoTela(String deoTela) {
        this.deoTela = deoTela;
    }

    public String getPovrede() {
        return povrede;
    }

    public void setPovrede(String povrede) {
        this.povrede = povrede;
    }

    public RadnjaPresudeDTO getRadnja() {
        return radnja;
    }

    public void setRadnja(RadnjaPresudeDTO radnja) {
        this.radnja = radnja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PovredaDTO)) {
            return false;
        }

        PovredaDTO povredaDTO = (PovredaDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, povredaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PovredaDTO{" +
            "id='" + getId() + "'" +
            ", oruzje='" + getOruzje() + "'" +
            ", deoTela='" + getDeoTela() + "'" +
            ", povrede='" + getPovrede() + "'" +
            ", radnja=" + getRadnja() +
            "}";
    }
}
