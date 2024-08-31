package pravna.com.myapp.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.NotNull;

public class RadnjaPresudeFullDTO implements Serializable {

    private String id;

    @NotNull
    private LocalDate vremeRadnje;

    @NotNull
    private String mestoRadnje;

    private String mestoSmrti;

    private LocalDate vremeSmrti;

    private PovredaDTO[] povrede;

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

    public PovredaDTO[] getPovrede() {
        return povrede;
    }

    public void setPovrede(PovredaDTO[] povrede) {
        this.povrede = povrede;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RadnjaPresudeFullDTO)) {
            return false;
        }

        RadnjaPresudeFullDTO radnjaPresudeDTO = (RadnjaPresudeFullDTO) o;
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
            ", mestoSmrti='" + getMestoSmrti() + "'" +
            ", vremeSmrti='" + getVremeSmrti() + "'" +
            "}";
    }

    public RadnjaPresudeDTO toRadnjaPresudaDTO() {
        RadnjaPresudeDTO radnjaPresudeDTO = new RadnjaPresudeDTO();
        radnjaPresudeDTO.setVremeRadnje(getVremeRadnje());
        radnjaPresudeDTO.setMestoRadnje(getMestoRadnje());
        radnjaPresudeDTO.setMestoSmrti(getMestoSmrti());
        radnjaPresudeDTO.setVremeSmrti(getVremeSmrti());
        return radnjaPresudeDTO;
    }
}
