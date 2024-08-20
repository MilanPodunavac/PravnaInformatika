package pravna.com.myapp.service.extended;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pravna.com.myapp.repository.extended.OsobaExtendedRepository;
import pravna.com.myapp.service.OsobaService;
import pravna.com.myapp.service.mapper.OsobaMapper;

@Service
@Primary
public class OsobaExtendedService extends OsobaService {

    private final Logger log = LoggerFactory.getLogger(OsobaService.class);

    private final OsobaExtendedRepository osobaRepository;

    private final OsobaMapper osobaMapper;

    public OsobaExtendedService(OsobaExtendedRepository osobaRepository, OsobaMapper osobaMapper) {
        super(osobaRepository, osobaMapper);
        this.osobaRepository = osobaRepository;
        this.osobaMapper = osobaMapper;
    }
}
