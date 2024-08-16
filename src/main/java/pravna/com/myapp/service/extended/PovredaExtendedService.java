package pravna.com.myapp.service.extended;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pravna.com.myapp.repository.PovredaRepository;
import pravna.com.myapp.repository.extended.PovredaExtendedRepository;
import pravna.com.myapp.service.PovredaService;

@Service
@Primary
public class PovredaExtendedService extends PovredaService {

    private final Logger log = LoggerFactory.getLogger(PovredaService.class);

    private final PovredaExtendedRepository povredaRepository;

    public PovredaExtendedService(PovredaExtendedRepository povredaRepository) {
        super(povredaRepository);
        this.povredaRepository = povredaRepository;
    }
}
