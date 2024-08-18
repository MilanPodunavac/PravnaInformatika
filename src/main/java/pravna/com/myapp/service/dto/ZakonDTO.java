package pravna.com.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link pravna.com.myapp.domain.Zakon} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ZakonDTO implements Serializable {

    private String id;

    @NotNull
    private String naziv;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ZakonDTO)) {
            return false;
        }

        ZakonDTO zakonDTO = (ZakonDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, zakonDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ZakonDTO{" +
            "id='" + getId() + "'" +
            ", naziv='" + getNaziv() + "'" +
            "}";
    }
}
