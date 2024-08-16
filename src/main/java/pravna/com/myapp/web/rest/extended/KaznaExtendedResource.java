package pravna.com.myapp.web.rest.extended;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pravna.com.myapp.repository.KaznaRepository;
import pravna.com.myapp.repository.extended.KaznaExtendedRepository;
import pravna.com.myapp.service.KaznaService;
import pravna.com.myapp.service.extended.KaznaExtendedService;
import pravna.com.myapp.web.rest.KaznaResource;

@RestController
@Primary
@RequestMapping("/api/xt")
public class KaznaExtendedResource extends KaznaResource {

    private final Logger log = LoggerFactory.getLogger(KaznaResource.class);

    private static final String ENTITY_NAME = "kazna";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KaznaExtendedService kaznaService;

    private final KaznaExtendedRepository kaznaRepository;

    public KaznaExtendedResource(KaznaExtendedService kaznaService, KaznaExtendedRepository kaznaRepository) {
        super(kaznaService, kaznaRepository);
        this.kaznaService = kaznaService;
        this.kaznaRepository = kaznaRepository;
    }
}
