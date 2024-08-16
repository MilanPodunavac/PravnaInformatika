package pravna.com.myapp.web.rest.extended;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pravna.com.myapp.repository.ZakonRepository;
import pravna.com.myapp.repository.extended.ZakonExtendedRepository;
import pravna.com.myapp.service.ZakonService;
import pravna.com.myapp.service.extended.ZakonExtendedService;
import pravna.com.myapp.web.rest.ZakonResource;

@RestController
@Primary
@RequestMapping("/api/xt")
public class ZakonExtendedResource extends ZakonResource {

    private final Logger log = LoggerFactory.getLogger(ZakonResource.class);

    private static final String ENTITY_NAME = "zakon";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ZakonExtendedService zakonService;

    private final ZakonExtendedRepository zakonRepository;

    public ZakonExtendedResource(ZakonExtendedService zakonService, ZakonExtendedRepository zakonRepository) {
        super(zakonService, zakonRepository);
        this.zakonService = zakonService;
        this.zakonRepository = zakonRepository;
    }
}
