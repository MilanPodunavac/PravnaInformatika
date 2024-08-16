package pravna.com.myapp.web.rest.extended;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pravna.com.myapp.repository.OptuzeniRepository;
import pravna.com.myapp.repository.extended.OptuzeniExtendedRepository;
import pravna.com.myapp.service.OptuzeniService;
import pravna.com.myapp.service.extended.OptuzeniExtendedService;
import pravna.com.myapp.web.rest.OptuzeniResource;

@RestController
@Primary
@RequestMapping("/api/xt")
public class OptuzeniExtendedResource extends OptuzeniResource {

    private final Logger log = LoggerFactory.getLogger(OptuzeniResource.class);

    private static final String ENTITY_NAME = "optuzeni";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OptuzeniExtendedService optuzeniService;

    private final OptuzeniExtendedRepository optuzeniRepository;

    public OptuzeniExtendedResource(OptuzeniExtendedService optuzeniService, OptuzeniExtendedRepository optuzeniRepository) {
        super(optuzeniService, optuzeniRepository);
        this.optuzeniService = optuzeniService;
        this.optuzeniRepository = optuzeniRepository;
    }
}
