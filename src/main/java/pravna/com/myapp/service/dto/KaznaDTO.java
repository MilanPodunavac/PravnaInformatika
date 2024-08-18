package pravna.com.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;
import pravna.com.myapp.domain.enumeration.TipKazne;

/**
 * A DTO for the {@link pravna.com.myapp.domain.Kazna} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class KaznaDTO implements Serializable {

    private String id;

    @NotNull
    private TipKazne tip;

    private Integer duzinaPritvora;

    private Boolean uracunavanjePritvora;

    private Integer kolicinaNovca;

    private String primalacNovca;

    private String nazivImovine;

    private PresudaDTO presuda;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TipKazne getTip() {
        return tip;
    }

    public void setTip(TipKazne tip) {
        this.tip = tip;
    }

    public Integer getDuzinaPritvora() {
        return duzinaPritvora;
    }

    public void setDuzinaPritvora(Integer duzinaPritvora) {
        this.duzinaPritvora = duzinaPritvora;
    }

    public Boolean getUracunavanjePritvora() {
        return uracunavanjePritvora;
    }

    public void setUracunavanjePritvora(Boolean uracunavanjePritvora) {
        this.uracunavanjePritvora = uracunavanjePritvora;
    }

    public Integer getKolicinaNovca() {
        return kolicinaNovca;
    }

    public void setKolicinaNovca(Integer kolicinaNovca) {
        this.kolicinaNovca = kolicinaNovca;
    }

    public String getPrimalacNovca() {
        return primalacNovca;
    }

    public void setPrimalacNovca(String primalacNovca) {
        this.primalacNovca = primalacNovca;
    }

    public String getNazivImovine() {
        return nazivImovine;
    }

    public void setNazivImovine(String nazivImovine) {
        this.nazivImovine = nazivImovine;
    }

    public PresudaDTO getPresuda() {
        return presuda;
    }

    public void setPresuda(PresudaDTO presuda) {
        this.presuda = presuda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KaznaDTO)) {
            return false;
        }

        KaznaDTO kaznaDTO = (KaznaDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, kaznaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KaznaDTO{" +
            "id='" + getId() + "'" +
            ", tip='" + getTip() + "'" +
            ", duzinaPritvora=" + getDuzinaPritvora() +
            ", uracunavanjePritvora='" + getUracunavanjePritvora() + "'" +
            ", kolicinaNovca=" + getKolicinaNovca() +
            ", primalacNovca='" + getPrimalacNovca() + "'" +
            ", nazivImovine='" + getNazivImovine() + "'" +
            ", presuda=" + getPresuda() +
            "}";
    }
}
