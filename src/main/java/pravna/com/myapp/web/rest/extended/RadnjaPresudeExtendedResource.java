package pravna.com.myapp.web.rest.extended;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pravna.com.myapp.repository.RadnjaPresudeRepository;
import pravna.com.myapp.repository.extended.RadnjaPresudeExtendedRepository;
import pravna.com.myapp.service.RadnjaPresudeService;
import pravna.com.myapp.service.extended.RadnjaPresudeExtendedService;
import pravna.com.myapp.web.rest.RadnjaPresudeResource;

@RestController
@Primary
@RequestMapping("/api/xt")
public class RadnjaPresudeExtendedResource extends RadnjaPresudeResource {

    private final Logger log = LoggerFactory.getLogger(RadnjaPresudeResource.class);

    private static final String ENTITY_NAME = "radnjaPresude";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RadnjaPresudeExtendedService radnjaPresudeService;

    private final RadnjaPresudeExtendedRepository radnjaPresudeRepository;

    public RadnjaPresudeExtendedResource(
        RadnjaPresudeExtendedService radnjaPresudeService,
        RadnjaPresudeExtendedRepository radnjaPresudeRepository
    ) {
        super(radnjaPresudeService, radnjaPresudeRepository);
        this.radnjaPresudeService = radnjaPresudeService;
        this.radnjaPresudeRepository = radnjaPresudeRepository;
    }
}
