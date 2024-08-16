package pravna.com.myapp.web.rest.extended;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pravna.com.myapp.repository.OsobaRepository;
import pravna.com.myapp.repository.extended.OsobaExtendedRepository;
import pravna.com.myapp.service.OsobaService;
import pravna.com.myapp.service.extended.OsobaExtendedService;
import pravna.com.myapp.web.rest.OsobaResource;

@RestController
@Primary
@RequestMapping("/api/xt")
public class OsobaExtendedResource extends OsobaResource {

    private final Logger log = LoggerFactory.getLogger(OsobaResource.class);

    private static final String ENTITY_NAME = "osoba";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OsobaExtendedService osobaService;

    private final OsobaExtendedRepository osobaRepository;

    public OsobaExtendedResource(OsobaExtendedService osobaService, OsobaExtendedRepository osobaRepository) {
        super(osobaService, osobaRepository);
        this.osobaService = osobaService;
        this.osobaRepository = osobaRepository;
    }
}
