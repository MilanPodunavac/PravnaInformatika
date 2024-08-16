package pravna.com.myapp.web.rest.extended;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pravna.com.myapp.repository.PovredaRepository;
import pravna.com.myapp.repository.extended.PovredaExtendedRepository;
import pravna.com.myapp.service.PovredaService;
import pravna.com.myapp.service.extended.PovredaExtendedService;
import pravna.com.myapp.web.rest.PovredaResource;

@RestController
@Primary
@RequestMapping("/api/xt")
public class PovredaExtendedResource extends PovredaResource {

    private final Logger log = LoggerFactory.getLogger(PovredaResource.class);

    private static final String ENTITY_NAME = "povreda";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PovredaExtendedService povredaService;

    private final PovredaExtendedRepository povredaRepository;

    public PovredaExtendedResource(PovredaExtendedService povredaService, PovredaExtendedRepository povredaRepository) {
        super(povredaService, povredaRepository);
        this.povredaService = povredaService;
        this.povredaRepository = povredaRepository;
    }
}
