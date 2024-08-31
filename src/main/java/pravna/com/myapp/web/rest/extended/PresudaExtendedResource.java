package pravna.com.myapp.web.rest.extended;

import java.net.URI;
import java.net.URISyntaxException;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pravna.com.myapp.repository.extended.PresudaExtendedRepository;
import pravna.com.myapp.service.dto.KaznaDTO;
import pravna.com.myapp.service.dto.PovredaDTO;
import pravna.com.myapp.service.dto.PresudaDTO;
import pravna.com.myapp.service.dto.PresudaFullDTO;
import pravna.com.myapp.service.extended.KaznaExtendedService;
import pravna.com.myapp.service.extended.PovredaExtendedService;
import pravna.com.myapp.service.extended.PresudaExtendedService;
import pravna.com.myapp.web.rest.PresudaResource;
import tech.jhipster.web.util.HeaderUtil;

@RestController
@Primary
@RequestMapping("/api/xt")
public class PresudaExtendedResource extends PresudaResource {

    private final Logger log = LoggerFactory.getLogger(PresudaResource.class);

    private static final String ENTITY_NAME = "presuda";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PresudaExtendedService presudaService;

    private final PresudaExtendedRepository presudaRepository;

    private final PovredaExtendedService povredaService;

    private final KaznaExtendedService kaznaService;

    public PresudaExtendedResource(
        PresudaExtendedService presudaService,
        PresudaExtendedRepository presudaRepository,
        PovredaExtendedService povredaService,
        KaznaExtendedService kaznaService
    ) {
        super(presudaService, presudaRepository);
        this.presudaService = presudaService;
        this.presudaRepository = presudaRepository;
        this.povredaService = povredaService;
        this.kaznaService = kaznaService;
    }

    @PostMapping("/presudas/full")
    public ResponseEntity<PresudaFullDTO> createPresudaFull(@Valid @RequestBody PresudaFullDTO presudaFullDTO) throws URISyntaxException {
        System.out.println(presudaFullDTO);

        PresudaDTO presudaSaved = presudaService.save(presudaFullDTO.toPresudaDTO());

        for (KaznaDTO kaznaDTO : presudaFullDTO.getKazne()) {
            kaznaDTO.setPresuda(presudaSaved);
            kaznaService.save(kaznaDTO);
        }

        for (PovredaDTO povredaDTO : presudaFullDTO.getRadnja().getPovrede()) {
            povredaDTO.setRadnja(presudaSaved.getRadnja());
            povredaService.save(povredaDTO);
        }

        for (PresudaFullDTO optuzeniPresudaFullDTO : presudaFullDTO.getOptuzeni().getPresude()) {
            PresudaDTO optuzeniPresudaDTO = optuzeniPresudaFullDTO.toPresudaDTO();
            optuzeniPresudaDTO.setOptuzeni(presudaSaved.getOptuzeni());
            presudaService.save(optuzeniPresudaDTO);
        }

        return ResponseEntity
            .created(new URI("/api/presudas"))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, "1"))
            .body(presudaFullDTO);
    }
}
