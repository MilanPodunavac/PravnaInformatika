package pravna.com.myapp.web.rest.extended;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pravna.com.myapp.repository.PresudaRepository;
import pravna.com.myapp.repository.extended.PresudaExtendedRepository;
import pravna.com.myapp.service.PresudaService;
import pravna.com.myapp.service.extended.PresudaExtendedService;
import pravna.com.myapp.web.rest.PresudaResource;

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

    public PresudaExtendedResource(PresudaExtendedService presudaService, PresudaExtendedRepository presudaRepository) {
        super(presudaService, presudaRepository);
        this.presudaService = presudaService;
        this.presudaRepository = presudaRepository;
    }
}
