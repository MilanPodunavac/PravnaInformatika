package pravna.com.myapp.service.extended;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pravna.com.myapp.repository.OptuzeniRepository;
import pravna.com.myapp.repository.extended.OptuzeniExtendedRepository;
import pravna.com.myapp.service.OptuzeniService;

@Service
@Primary
public class OptuzeniExtendedService extends OptuzeniService {

    private final Logger log = LoggerFactory.getLogger(OptuzeniService.class);

    private final OptuzeniExtendedRepository optuzeniRepository;

    public OptuzeniExtendedService(OptuzeniExtendedRepository optuzeniRepository) {
        super(optuzeniRepository);
        this.optuzeniRepository = optuzeniRepository;
    }
}
