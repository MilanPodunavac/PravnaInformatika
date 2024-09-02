package pravna.com.myapp.service.cbr;

import java.util.*;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import pravna.com.myapp.repository.extended.ClanZakonaExtendedRepository;
import pravna.com.myapp.repository.extended.KaznaExtendedRepository;
import pravna.com.myapp.repository.extended.PovredaExtendedRepository;
import pravna.com.myapp.repository.extended.PresudaExtendedRepository;
import pravna.com.myapp.service.dto.CbrDTO;
import pravna.com.myapp.service.dto.KaznaDTO;
import pravna.com.myapp.service.dto.PresudaFullDTO;
import pravna.com.myapp.service.extended.KaznaExtendedService;
import pravna.com.myapp.service.extended.PresudaExtendedService;
import ucm.gaia.jcolibri.casebase.LinealCaseBase;
import ucm.gaia.jcolibri.cbraplications.StandardCBRApplication;
import ucm.gaia.jcolibri.cbrcore.*;
import ucm.gaia.jcolibri.exception.ExecutionException;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNConfig;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import ucm.gaia.jcolibri.method.retrieve.RetrievalResult;
import ucm.gaia.jcolibri.method.retrieve.selection.SelectCases;

@Service
public class CbrService implements StandardCBRApplication {

    Connector _connector;/** Connector object */
    CBRCaseBase _caseBase;/** CaseBase object */

    NNConfig simConfig;/** KNN configuration */

    private CbrDTO cbrDTO = new CbrDTO();

    private final PresudaExtendedRepository presudaExtendedRepository;

    private final PresudaExtendedService presudaExtendedService;

    private final KaznaExtendedRepository kaznaExtendedRepository;

    private final KaznaExtendedService kaznaExtendedService;

    private final PovredaExtendedRepository povredaExtendedRepository;

    private final ClanZakonaExtendedRepository clanZakonaExtendedRepository;

    public CbrService(
        PresudaExtendedRepository presudaExtendedRepository,
        PresudaExtendedService presudaExtendedService,
        KaznaExtendedRepository kaznaExtendedRepository,
        KaznaExtendedService kaznaExtendedService,
        PovredaExtendedRepository povredaExtendedRepository,
        ClanZakonaExtendedRepository clanZakonaExtendedRepository
    ) {
        this.presudaExtendedRepository = presudaExtendedRepository;
        this.presudaExtendedService = presudaExtendedService;
        this.kaznaExtendedRepository = kaznaExtendedRepository;
        this.kaznaExtendedService = kaznaExtendedService;
        this.povredaExtendedRepository = povredaExtendedRepository;
        this.clanZakonaExtendedRepository = clanZakonaExtendedRepository;
    }

    public CbrDTO calculate(PresudaFullDTO presudaDTO) {
        try {
            configure();

            preCycle();

            CBRQuery query = new CBRQuery();
            CbrPresuda cbrPresuda = CbrPresuda.create(presudaDTO);

            query.setDescription(cbrPresuda);

            cycle(query);

            postCycle();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cbrDTO;
    }

    @Override
    public void configure() throws ExecutionException {
        _connector =
            new CbrConnector(presudaExtendedRepository, kaznaExtendedRepository, povredaExtendedRepository, clanZakonaExtendedRepository);

        _caseBase = new LinealCaseBase(); // Create a Lineal case base for in-memory organization

        cbrDTO.getPresude().clear();
        cbrDTO.getKazne().clear();

        simConfig = new NNConfig(); // KNN configuration
        simConfig.setDescriptionSimFunction(new Average()); // global similarity function = average

        simConfig.addMapping(new Attribute("pokusaj", CbrPresuda.class), new Equal());
        simConfig.addMapping(new Attribute("tip", CbrPresuda.class), new Equal());
        simConfig.addMapping(new Attribute("povrede", CbrPresuda.class), new Equal());

        TabularSimilarity slicnostClanova = new TabularSimilarity(
            Arrays.asList("cl. 42 st. 1 ZOBSNP", "143", "144", "145", "146", "147", "148")
        );
        slicnostClanova.setSimilarity("143", "144", .5);
        slicnostClanova.setSimilarity("143", "145", .5);
        slicnostClanova.setSimilarity("143", "146", .2);
        slicnostClanova.setSimilarity("143", "147", .2);
        slicnostClanova.setSimilarity("143", "148", .2);

        slicnostClanova.setSimilarity("144", "145", .2);
        slicnostClanova.setSimilarity("144", "146", 0);
        slicnostClanova.setSimilarity("144", "147", 0);
        slicnostClanova.setSimilarity("144", "148", 0);

        slicnostClanova.setSimilarity("145", "146", .5);
        slicnostClanova.setSimilarity("145", "147", .5);
        slicnostClanova.setSimilarity("145", "148", .5);

        slicnostClanova.setSimilarity("146", "147", .8);
        slicnostClanova.setSimilarity("146", "148", .8);

        slicnostClanova.setSimilarity("147", "148", .8);

        simConfig.addMapping(new Attribute("clanovi", CbrPresuda.class), slicnostClanova);
    }

    @Override
    public CBRCaseBase preCycle() throws ExecutionException {
        _caseBase.init(_connector);
        java.util.Collection<CBRCase> cases = _caseBase.getCases();
        //		for (CBRCase c: cases)
        //			System.out.println(c.getDescription());
        return _caseBase;
    }

    @Override
    public void cycle(CBRQuery cbrQuery) throws ExecutionException {
        Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), cbrQuery, simConfig);
        List<KaznaDTO> kazne = kaznaExtendedService.findAll();
        Collection<RetrievalResult> eval_top = SelectCases.selectTopKRR(eval, 5);
        System.out.println("Retrieved cases:");
        for (RetrievalResult nse : eval_top) {
            System.out.println(nse.get_case().getDescription() + " -> " + nse.getEval());
            cbrDTO.getPresude().add(presudaExtendedService.findOne((String) nse.get_case().getID()).get());
        }
        Collection<RetrievalResult> best_eval = SelectCases.selectTopKRR(eval, 1);
        for (RetrievalResult nse : best_eval) {
            System.out.println(nse.get_case().getID());
            System.out.println(
                kazne
                    .stream()
                    .filter(kaznaDTO -> Objects.equals(kaznaDTO.getPresuda().getId(), nse.get_case().getID().toString()))
                    .collect(Collectors.toSet())
                    .size()
            );
            cbrDTO.setKazne(
                kazne
                    .stream()
                    .filter(kaznaDTO -> Objects.equals(kaznaDTO.getPresuda().getId(), nse.get_case().getID().toString()))
                    .collect(Collectors.toSet())
            );
        }
    }

    @Override
    public void postCycle() throws ExecutionException {}
}
