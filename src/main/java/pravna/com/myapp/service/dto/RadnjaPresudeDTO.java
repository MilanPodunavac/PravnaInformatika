package pravna.com.myapp.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link pravna.com.myapp.domain.RadnjaPresude} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RadnjaPresudeDTO implements Serializable {

    private String id;

    @NotNull
    private LocalDate vremeRadnje;

    @NotNull
    private String mestoRadnje;

    private String bitneNapomene;

    private String mestoSmrti;

    private LocalDate vremeSmrti;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getVremeRadnje() {
        return vremeRadnje;
    }

    public void setVremeRadnje(LocalDate vremeRadnje) {
        this.vremeRadnje = vremeRadnje;
    }

    public String getMestoRadnje() {
        return mestoRadnje;
    }

    public void setMestoRadnje(String mestoRadnje) {
        this.mestoRadnje = mestoRadnje;
    }

    public String getBitneNapomene() {
        return bitneNapomene;
    }

    public void setBitneNapomene(String bitneNapomene) {
        this.bitneNapomene = bitneNapomene;
    }

    public String getMestoSmrti() {
        return mestoSmrti;
    }

    public void setMestoSmrti(String mestoSmrti) {
        this.mestoSmrti = mestoSmrti;
    }

    public LocalDate getVremeSmrti() {
        return vremeSmrti;
    }

    public void setVremeSmrti(LocalDate vremeSmrti) {
        this.vremeSmrti = vremeSmrti;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RadnjaPresudeDTO)) {
            return false;
        }

        RadnjaPresudeDTO radnjaPresudeDTO = (RadnjaPresudeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, radnjaPresudeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RadnjaPresudeDTO{" +
            "id='" + getId() + "'" +
            ", vremeRadnje='" + getVremeRadnje() + "'" +
            ", mestoRadnje='" + getMestoRadnje() + "'" +
            ", bitneNapomene='" + getBitneNapomene() + "'" +
            ", mestoSmrti='" + getMestoSmrti() + "'" +
            ", vremeSmrti='" + getVremeSmrti() + "'" +
            "}";
    }
}
