package pravna.com.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;
import pravna.com.myapp.domain.enumeration.TipSuda;

/**
 * A DTO for the {@link pravna.com.myapp.domain.Sud} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SudDTO implements Serializable {

    private String id;

    @NotNull
    private TipSuda tip;

    @NotNull
    private String naselje;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TipSuda getTip() {
        return tip;
    }

    public void setTip(TipSuda tip) {
        this.tip = tip;
    }

    public String getNaselje() {
        return naselje;
    }

    public void setNaselje(String naselje) {
        this.naselje = naselje;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SudDTO)) {
            return false;
        }

        SudDTO sudDTO = (SudDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, sudDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SudDTO{" +
            "id='" + getId() + "'" +
            ", tip='" + getTip() + "'" +
            ", naselje='" + getNaselje() + "'" +
            "}";
    }
}
