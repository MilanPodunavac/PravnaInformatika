package pravna.com.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link pravna.com.myapp.domain.Osoba} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OsobaDTO implements Serializable {

    private String id;

    @NotNull
    private String ime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OsobaDTO)) {
            return false;
        }

        OsobaDTO osobaDTO = (OsobaDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, osobaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OsobaDTO{" +
            "id='" + getId() + "'" +
            ", ime='" + getIme() + "'" +
            "}";
    }
}
