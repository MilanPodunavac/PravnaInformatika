package pravna.com.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link pravna.com.myapp.domain.ClanZakona} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ClanZakonaDTO implements Serializable {

    private String id;

    @NotNull
    private Integer broj;

    @NotNull
    private Integer glava;

    @NotNull
    private String naziv;

    private String tekst;

    private ZakonDTO zakon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getBroj() {
        return broj;
    }

    public void setBroj(Integer broj) {
        this.broj = broj;
    }

    public Integer getGlava() {
        return glava;
    }

    public void setGlava(Integer glava) {
        this.glava = glava;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public ZakonDTO getZakon() {
        return zakon;
    }

    public void setZakon(ZakonDTO zakon) {
        this.zakon = zakon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClanZakonaDTO)) {
            return false;
        }

        ClanZakonaDTO clanZakonaDTO = (ClanZakonaDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, clanZakonaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClanZakonaDTO{" +
            "id='" + getId() + "'" +
            ", broj=" + getBroj() +
            ", glava=" + getGlava() +
            ", naziv='" + getNaziv() + "'" +
            ", tekst='" + getTekst() + "'" +
            ", zakon=" + getZakon() +
            "}";
    }
}
