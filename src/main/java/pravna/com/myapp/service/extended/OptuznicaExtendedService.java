package pravna.com.myapp.service.extended;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pravna.com.myapp.repository.OptuznicaRepository;
import pravna.com.myapp.repository.extended.OptuznicaExtendedRepository;
import pravna.com.myapp.service.OptuznicaService;
import pravna.com.myapp.service.mapper.OptuznicaMapper;

@Service
@Primary
public class OptuznicaExtendedService extends OptuznicaService {

    private final Logger log = LoggerFactory.getLogger(OptuznicaService.class);

    private final OptuznicaExtendedRepository optuznicaRepository;

    private final OptuznicaMapper optuznicaMapper;

    public OptuznicaExtendedService(OptuznicaExtendedRepository optuznicaRepository, OptuznicaMapper optuznicaMapper) {
        super(optuznicaRepository, optuznicaMapper);
        this.optuznicaRepository = optuznicaRepository;
        this.optuznicaMapper = optuznicaMapper;
    }
}
