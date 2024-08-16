package pravna.com.myapp.service.extended;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pravna.com.myapp.repository.RadnjaPresudeRepository;
import pravna.com.myapp.repository.extended.RadnjaPresudeExtendedRepository;
import pravna.com.myapp.service.RadnjaPresudeService;

@Service
@Primary
public class RadnjaPresudeExtendedService extends RadnjaPresudeService {

    private final Logger log = LoggerFactory.getLogger(RadnjaPresudeService.class);

    private final RadnjaPresudeExtendedRepository radnjaPresudeRepository;

    public RadnjaPresudeExtendedService(RadnjaPresudeExtendedRepository radnjaPresudeRepository) {
        super(radnjaPresudeRepository);
        this.radnjaPresudeRepository = radnjaPresudeRepository;
    }
}
