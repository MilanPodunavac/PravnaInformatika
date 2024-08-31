package pravna.com.myapp.web.rest.extended;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pravna.com.myapp.repository.extended.OptuznicaExtendedRepository;
import pravna.com.myapp.service.extended.OptuznicaExtendedService;
import pravna.com.myapp.web.rest.OptuznicaResource;

@RestController
@Primary
@RequestMapping("/api/xt")
public class OptuznicaExtendedResource extends OptuznicaResource {

    private final Logger log = LoggerFactory.getLogger(OptuznicaResource.class);

    private static final String ENTITY_NAME = "optuznica";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OptuznicaExtendedService optuznicaService;

    private final OptuznicaExtendedRepository optuznicaRepository;

    public OptuznicaExtendedResource(OptuznicaExtendedService optuznicaService, OptuznicaExtendedRepository optuznicaRepository) {
        super(optuznicaService, optuznicaRepository);
        this.optuznicaService = optuznicaService;
        this.optuznicaRepository = optuznicaRepository;
    }
}
