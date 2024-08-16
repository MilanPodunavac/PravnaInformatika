package pravna.com.myapp.web.rest.extended;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pravna.com.myapp.repository.ClanZakonaRepository;
import pravna.com.myapp.repository.extended.ClanZakonaExtendedRepository;
import pravna.com.myapp.service.ClanZakonaService;
import pravna.com.myapp.service.extended.ClanZakonaExtendedService;
import pravna.com.myapp.web.rest.ClanZakonaResource;

@RestController
@Primary
@RequestMapping("/api/xt")
public class ClanZakonaExtendedResource extends ClanZakonaResource {

    private final Logger log = LoggerFactory.getLogger(ClanZakonaResource.class);

    private static final String ENTITY_NAME = "clanZakona";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClanZakonaExtendedService clanZakonaService;

    private final ClanZakonaExtendedRepository clanZakonaRepository;

    public ClanZakonaExtendedResource(ClanZakonaExtendedService clanZakonaService, ClanZakonaExtendedRepository clanZakonaRepository) {
        super(clanZakonaService, clanZakonaRepository);
        this.clanZakonaService = clanZakonaService;
        this.clanZakonaRepository = clanZakonaRepository;
    }
}
