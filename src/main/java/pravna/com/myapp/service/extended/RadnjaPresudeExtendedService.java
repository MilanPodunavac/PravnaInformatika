package pravna.com.myapp.service.extended;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pravna.com.myapp.repository.extended.RadnjaPresudeExtendedRepository;
import pravna.com.myapp.service.RadnjaPresudeService;
import pravna.com.myapp.service.mapper.RadnjaPresudeMapper;

@Service
@Primary
public class RadnjaPresudeExtendedService extends RadnjaPresudeService {

    private final Logger log = LoggerFactory.getLogger(RadnjaPresudeService.class);

    private final RadnjaPresudeExtendedRepository radnjaPresudeRepository;

    private final RadnjaPresudeMapper radnjaPresudeMapper;

    public RadnjaPresudeExtendedService(RadnjaPresudeExtendedRepository radnjaPresudeRepository, RadnjaPresudeMapper radnjaPresudeMapper) {
        super(radnjaPresudeRepository, radnjaPresudeMapper);
        this.radnjaPresudeRepository = radnjaPresudeRepository;
        this.radnjaPresudeMapper = radnjaPresudeMapper;
    }
}
