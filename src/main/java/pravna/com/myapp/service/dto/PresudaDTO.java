package pravna.com.myapp.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;
import pravna.com.myapp.domain.enumeration.TipPresude;
import pravna.com.myapp.domain.enumeration.TipUbistva;

/**
 * A DTO for the {@link pravna.com.myapp.domain.Presuda} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PresudaDTO implements Serializable {

    private String id;

    @NotNull
    private LocalDate datum;

    private LocalDate datumPritvora;

    @NotNull
    private String kod;

    @NotNull
    private TipPresude tip;

    private Integer broj;

    private Integer godina;

    private Boolean pokusaj;

    private Boolean krivica;

    private TipUbistva nacin;

    private RadnjaPresudeDTO radnja;

    private OptuznicaDTO optuznica;

    private Set<OsobaDTO> veces = new HashSet<>();

    private Set<ClanZakonaDTO> clanoviZakonas = new HashSet<>();

    private OptuzeniDTO optuzeni;

    private OsobaDTO sudija;

    private OsobaDTO zapisnicar;

    private OsobaDTO tuzilac;

    private OsobaDTO branilac;

    private OsobaDTO osteceni;

    private SudDTO sud;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public LocalDate getDatumPritvora() {
        return datumPritvora;
    }

    public void setDatumPritvora(LocalDate datumPritvora) {
        this.datumPritvora = datumPritvora;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public TipPresude getTip() {
        return tip;
    }

    public void setTip(TipPresude tip) {
        this.tip = tip;
    }

    public Integer getBroj() {
        return broj;
    }

    public void setBroj(Integer broj) {
        this.broj = broj;
    }

    public Integer getGodina() {
        return godina;
    }

    public void setGodina(Integer godina) {
        this.godina = godina;
    }

    public Boolean getPokusaj() {
        return pokusaj;
    }

    public void setPokusaj(Boolean pokusaj) {
        this.pokusaj = pokusaj;
    }

    public Boolean getKrivica() {
        return krivica;
    }

    public void setKrivica(Boolean krivica) {
        this.krivica = krivica;
    }

    public TipUbistva getNacin() {
        return nacin;
    }

    public void setNacin(TipUbistva nacin) {
        this.nacin = nacin;
    }

    public RadnjaPresudeDTO getRadnja() {
        return radnja;
    }

    public void setRadnja(RadnjaPresudeDTO radnja) {
        this.radnja = radnja;
    }

    public OptuznicaDTO getOptuznica() {
        return optuznica;
    }

    public void setOptuznica(OptuznicaDTO optuznica) {
        this.optuznica = optuznica;
    }

    public Set<OsobaDTO> getVeces() {
        return veces;
    }

    public void setVeces(Set<OsobaDTO> veces) {
        this.veces = veces;
    }

    public Set<ClanZakonaDTO> getClanoviZakonas() {
        return clanoviZakonas;
    }

    public void setClanoviZakonas(Set<ClanZakonaDTO> clanoviZakonas) {
        this.clanoviZakonas = clanoviZakonas;
    }

    public OptuzeniDTO getOptuzeni() {
        return optuzeni;
    }

    public void setOptuzeni(OptuzeniDTO optuzeni) {
        this.optuzeni = optuzeni;
    }

    public OsobaDTO getSudija() {
        return sudija;
    }

    public void setSudija(OsobaDTO sudija) {
        this.sudija = sudija;
    }

    public OsobaDTO getZapisnicar() {
        return zapisnicar;
    }

    public void setZapisnicar(OsobaDTO zapisnicar) {
        this.zapisnicar = zapisnicar;
    }

    public OsobaDTO getTuzilac() {
        return tuzilac;
    }

    public void setTuzilac(OsobaDTO tuzilac) {
        this.tuzilac = tuzilac;
    }

    public OsobaDTO getBranilac() {
        return branilac;
    }

    public void setBranilac(OsobaDTO branilac) {
        this.branilac = branilac;
    }

    public OsobaDTO getOsteceni() {
        return osteceni;
    }

    public void setOsteceni(OsobaDTO osteceni) {
        this.osteceni = osteceni;
    }

    public SudDTO getSud() {
        return sud;
    }

    public void setSud(SudDTO sud) {
        this.sud = sud;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PresudaDTO)) {
            return false;
        }

        PresudaDTO presudaDTO = (PresudaDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, presudaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PresudaDTO{" +
            "id='" + getId() + "'" +
            ", datum='" + getDatum() + "'" +
            ", datumPritvora='" + getDatumPritvora() + "'" +
            ", kod='" + getKod() + "'" +
            ", tip='" + getTip() + "'" +
            ", broj=" + getBroj() +
            ", godina=" + getGodina() +
            ", pokusaj='" + getPokusaj() + "'" +
            ", krivica='" + getKrivica() + "'" +
            ", nacin='" + getNacin() + "'" +
            ", radnja=" + getRadnja() +
            ", optuznica=" + getOptuznica() +
            ", veces=" + getVeces() +
            ", clanoviZakonas=" + getClanoviZakonas() +
            ", optuzeni=" + getOptuzeni() +
            ", sudija=" + getSudija() +
            ", zapisnicar=" + getZapisnicar() +
            ", tuzilac=" + getTuzilac() +
            ", branilac=" + getBranilac() +
            ", osteceni=" + getOsteceni() +
            ", sud=" + getSud() +
            "}";
    }
}
