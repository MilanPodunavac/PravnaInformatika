package pravna.com.myapp.service.cbr;

import java.net.URL;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import pravna.com.myapp.domain.ClanZakona;
import pravna.com.myapp.domain.Kazna;
import pravna.com.myapp.domain.Povreda;
import pravna.com.myapp.domain.Presuda;
import pravna.com.myapp.repository.extended.ClanZakonaExtendedRepository;
import pravna.com.myapp.repository.extended.KaznaExtendedRepository;
import pravna.com.myapp.repository.extended.PovredaExtendedRepository;
import pravna.com.myapp.repository.extended.PresudaExtendedRepository;
import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CaseBaseFilter;
import ucm.gaia.jcolibri.cbrcore.Connector;
import ucm.gaia.jcolibri.exception.InitializingException;

@Service
public class CbrConnector implements Connector {

    private final PresudaExtendedRepository presudaExtendedRepository;

    private final KaznaExtendedRepository kaznaExtendedRepository;

    private final PovredaExtendedRepository povredaExtendedRepository;

    private final ClanZakonaExtendedRepository clanZakonaExtendedRepository;

    public CbrConnector(
        PresudaExtendedRepository presudaExtendedRepository,
        KaznaExtendedRepository kaznaExtendedRepository,
        PovredaExtendedRepository povredaExtendedRepository,
        ClanZakonaExtendedRepository clanZakonaExtendedRepository
    ) {
        this.presudaExtendedRepository = presudaExtendedRepository;
        this.kaznaExtendedRepository = kaznaExtendedRepository;
        this.povredaExtendedRepository = povredaExtendedRepository;
        this.clanZakonaExtendedRepository = clanZakonaExtendedRepository;
    }

    @Override
    public void initFromXMLfile(URL url) throws InitializingException {}

    @Override
    public void close() {}

    @Override
    public void storeCases(Collection<CBRCase> collection) {}

    @Override
    public void deleteCases(Collection<CBRCase> collection) {}

    @Override
    public Collection<CBRCase> retrieveAllCases() {
        LinkedList<CBRCase> cases = new LinkedList<CBRCase>();

        List<Presuda> presude = presudaExtendedRepository
            .findAllWithEagerRelationships()
            .stream()
            .filter(presuda ->
                presuda.getKrivica() != null &&
                presuda.getSudija() != null &&
                presuda.getKrivica() &&
                presuda.getDatum().isAfter(LocalDate.of(2010, 1, 1))
            )
            .collect(Collectors.toList());
        List<Kazna> kazne = kaznaExtendedRepository.findAll();
        List<Povreda> povrede = povredaExtendedRepository.findAll();
        List<ClanZakona> clanovi = clanZakonaExtendedRepository.findAll();

        for (Presuda presuda : presude) {
            CBRCase cbrCase = new CBRCase();

            List<Kazna> kazneFiltered = kazne
                .stream()
                .filter(kazna -> kazna.getPresuda().getId() == presuda.getId())
                .collect(Collectors.toList());
            List<Povreda> povredeFiltered = povrede
                .stream()
                .filter(povreda -> povreda.getRadnja().getId() == presuda.getRadnja().getId())
                .collect(Collectors.toList());
            List<ClanZakona> clanoviFiltered = clanovi
                .stream()
                .filter(clanZakona -> clanZakona.getPresudes().contains(presuda))
                .collect(Collectors.toList());
            CbrPresuda cbrPresuda = CbrPresuda.create(presuda, povredeFiltered, clanoviFiltered, kazneFiltered);

            cbrCase.setDescription(cbrPresuda);
            cases.add(cbrCase);
        }

        return cases;
    }

    @Override
    public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter caseBaseFilter) {
        return null;
    }
}
