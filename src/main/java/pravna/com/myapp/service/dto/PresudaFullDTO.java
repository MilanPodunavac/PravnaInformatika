package pravna.com.myapp.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import pravna.com.myapp.domain.enumeration.TipPresude;
import pravna.com.myapp.domain.enumeration.TipUbistva;

public class PresudaFullDTO implements Serializable {

    private String id;

    private String tekst;

    @NotNull
    private LocalDate datum;

    private LocalDate datumObjave;

    private LocalDate datumPritvora;

    @NotNull
    private TipPresude tip;

    @NotNull
    private Integer broj;

    @NotNull
    private Integer godina;

    private String optuznica;

    private LocalDate datumOptuznice;

    private Boolean pokusaj;

    private Boolean krivica;

    private TipUbistva nacin;

    private RadnjaPresudeDTO radnja;

    private OptuzeniDTO optuzeni;

    private OsobaDTO sudija;

    private OsobaDTO zapisnicar;

    private OsobaDTO tuzilac;

    private OsobaDTO branilac;

    private String povrede;

    private String kazne;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public LocalDate getDatumObjave() {
        return datumObjave;
    }

    public void setDatumObjave(LocalDate datumObjave) {
        this.datumObjave = datumObjave;
    }

    public LocalDate getDatumPritvora() {
        return datumPritvora;
    }

    public void setDatumPritvora(LocalDate datumPritvora) {
        this.datumPritvora = datumPritvora;
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

    public String getOptuznica() {
        return optuznica;
    }

    public void setOptuznica(String optuznica) {
        this.optuznica = optuznica;
    }

    public LocalDate getDatumOptuznice() {
        return datumOptuznice;
    }

    public void setDatumOptuznice(LocalDate datumOptuznice) {
        this.datumOptuznice = datumOptuznice;
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

    public String getPovrede() {
        return povrede;
    }

    public void setPovrede(String povrede) {
        this.povrede = povrede;
    }

    public String getKazne() {
        return kazne;
    }

    public void setKazne(String kazne) {
        this.kazne = kazne;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PresudaFullDTO)) {
            return false;
        }

        PresudaFullDTO presudaDTO = (PresudaFullDTO) o;
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
            ", tekst='" + getTekst() + "'" +
            ", datum='" + getDatum() + "'" +
            ", datumObjave='" + getDatumObjave() + "'" +
            ", datumPritvora='" + getDatumPritvora() + "'" +
            ", tip='" + getTip() + "'" +
            ", broj=" + getBroj() +
            ", godina=" + getGodina() +
            ", optuznica='" + getOptuznica() + "'" +
            ", datumOptuznice='" + getDatumOptuznice() + "'" +
            ", pokusaj='" + getPokusaj() + "'" +
            ", krivica='" + getKrivica() + "'" +
            ", nacin='" + getNacin() + "'" +
            ", radnja=" + getRadnja() +
            ", optuzeni=" + getOptuzeni() +
            ", sudija=" + getSudija() +
            ", zapisnicar=" + getZapisnicar() +
            ", tuzilac=" + getTuzilac() +
            ", branilac=" + getBranilac() +
            ", povrede=" + getPovrede() +
            ", kazne=" + getKazne() +
            "}";
    }

    public PresudaDTO toPresudaDTO() {
        PresudaDTO presudaDTO = new PresudaDTO();
        presudaDTO.setTekst(getTekst());
        presudaDTO.setDatum(getDatum());
        presudaDTO.setDatumObjave(getDatumObjave());
        presudaDTO.setDatumPritvora(getDatumPritvora());
        presudaDTO.setTip(getTip());
        presudaDTO.setBroj(getBroj());
        presudaDTO.setGodina(getGodina());
        presudaDTO.setOptuznica(getOptuznica());
        presudaDTO.setDatumOptuznice(getDatumOptuznice());
        presudaDTO.setPokusaj(getPokusaj());
        presudaDTO.setKrivica(getKrivica());
        presudaDTO.setNacin(getNacin());
        presudaDTO.setRadnja(getRadnja());
        presudaDTO.setOptuzeni(getOptuzeni());
        presudaDTO.setSudija(getSudija());
        presudaDTO.setZapisnicar(getZapisnicar());
        presudaDTO.setTuzilac(getTuzilac());
        presudaDTO.setBranilac(getBranilac());
        return presudaDTO;
    }
}
