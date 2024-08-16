package pravna.com.myapp.web.rest.extended;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pravna.com.myapp.repository.SudRepository;
import pravna.com.myapp.repository.extended.SudExtendedRepository;
import pravna.com.myapp.service.SudService;
import pravna.com.myapp.service.extended.SudExtendedService;
import pravna.com.myapp.web.rest.SudResource;

@RestController
@Primary
@RequestMapping("/api/xt")
public class SudExtendedResource extends SudResource {

    private final Logger log = LoggerFactory.getLogger(SudResource.class);

    private static final String ENTITY_NAME = "sud";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SudExtendedService sudService;

    private final SudExtendedRepository sudRepository;

    public SudExtendedResource(SudExtendedService sudService, SudExtendedRepository sudRepository) {
        super(sudService, sudRepository);
        this.sudService = sudService;
        this.sudRepository = sudRepository;
    }
}
