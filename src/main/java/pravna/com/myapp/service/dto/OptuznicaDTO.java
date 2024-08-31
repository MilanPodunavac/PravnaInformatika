package pravna.com.myapp.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link pravna.com.myapp.domain.Optuznica} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OptuznicaDTO implements Serializable {

    private String id;

    @NotNull
    private String kod;

    @NotNull
    private LocalDate datum;

    private String ustanova;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public String getUstanova() {
        return ustanova;
    }

    public void setUstanova(String ustanova) {
        this.ustanova = ustanova;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OptuznicaDTO)) {
            return false;
        }

        OptuznicaDTO optuznicaDTO = (OptuznicaDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, optuznicaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OptuznicaDTO{" +
            "id='" + getId() + "'" +
            ", kod='" + getKod() + "'" +
            ", datum='" + getDatum() + "'" +
            ", ustanova='" + getUstanova() + "'" +
            "}";
    }
}
