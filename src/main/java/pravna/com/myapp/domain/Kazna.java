package pravna.com.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import pravna.com.myapp.domain.enumeration.TipKazne;

/**
 * A Kazna.
 */
@Document(collection = "kazna")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Kazna implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("tip")
    private TipKazne tip;

    @Field("duzina_pritvora")
    private Integer duzinaPritvora;

    @Field("uracunavanje_pritvora")
    private Boolean uracunavanjePritvora;

    @Field("kolicina_novca")
    private Integer kolicinaNovca;

    @Field("primalac_novca")
    private String primalacNovca;

    @Field("naziv_imovine")
    private String nazivImovine;

    @DBRef
    @Field("presuda")
    @JsonIgnoreProperties(
        value = {
            "radnja",
            "optuznica",
            "kaznes",
            "veces",
            "clanoviZakonas",
            "optuzeni",
            "sudija",
            "zapisnicar",
            "tuzilac",
            "branilac",
            "osteceni",
            "sud",
        },
        allowSetters = true
    )
    private Presuda presuda;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Kazna id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TipKazne getTip() {
        return this.tip;
    }

    public Kazna tip(TipKazne tip) {
        this.setTip(tip);
        return this;
    }

    public void setTip(TipKazne tip) {
        this.tip = tip;
    }

    public Integer getDuzinaPritvora() {
        return this.duzinaPritvora;
    }

    public Kazna duzinaPritvora(Integer duzinaPritvora) {
        this.setDuzinaPritvora(duzinaPritvora);
        return this;
    }

    public void setDuzinaPritvora(Integer duzinaPritvora) {
        this.duzinaPritvora = duzinaPritvora;
    }

    public Boolean getUracunavanjePritvora() {
        return this.uracunavanjePritvora;
    }

    public Kazna uracunavanjePritvora(Boolean uracunavanjePritvora) {
        this.setUracunavanjePritvora(uracunavanjePritvora);
        return this;
    }

    public void setUracunavanjePritvora(Boolean uracunavanjePritvora) {
        this.uracunavanjePritvora = uracunavanjePritvora;
    }

    public Integer getKolicinaNovca() {
        return this.kolicinaNovca;
    }

    public Kazna kolicinaNovca(Integer kolicinaNovca) {
        this.setKolicinaNovca(kolicinaNovca);
        return this;
    }

    public void setKolicinaNovca(Integer kolicinaNovca) {
        this.kolicinaNovca = kolicinaNovca;
    }

    public String getPrimalacNovca() {
        return this.primalacNovca;
    }

    public Kazna primalacNovca(String primalacNovca) {
        this.setPrimalacNovca(primalacNovca);
        return this;
    }

    public void setPrimalacNovca(String primalacNovca) {
        this.primalacNovca = primalacNovca;
    }

    public String getNazivImovine() {
        return this.nazivImovine;
    }

    public Kazna nazivImovine(String nazivImovine) {
        this.setNazivImovine(nazivImovine);
        return this;
    }

    public void setNazivImovine(String nazivImovine) {
        this.nazivImovine = nazivImovine;
    }

    public Presuda getPresuda() {
        return this.presuda;
    }

    public void setPresuda(Presuda presuda) {
        this.presuda = presuda;
    }

    public Kazna presuda(Presuda presuda) {
        this.setPresuda(presuda);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Kazna)) {
            return false;
        }
        return id != null && id.equals(((Kazna) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Kazna{" +
            "id=" + getId() +
            ", tip='" + getTip() + "'" +
            ", duzinaPritvora=" + getDuzinaPritvora() +
            ", uracunavanjePritvora='" + getUracunavanjePritvora() + "'" +
            ", kolicinaNovca=" + getKolicinaNovca() +
            ", primalacNovca='" + getPrimalacNovca() + "'" +
            ", nazivImovine='" + getNazivImovine() + "'" +
            "}";
    }
}
