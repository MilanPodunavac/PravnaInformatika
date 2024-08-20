package pravna.com.myapp.service.extended;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pravna.com.myapp.repository.extended.KaznaExtendedRepository;
import pravna.com.myapp.service.KaznaService;
import pravna.com.myapp.service.mapper.KaznaMapper;

@Service
@Primary
public class KaznaExtendedService extends KaznaService {

    private final Logger log = LoggerFactory.getLogger(KaznaService.class);

    private final KaznaExtendedRepository kaznaRepository;

    private final KaznaMapper kaznaMapper;

    public KaznaExtendedService(KaznaExtendedRepository kaznaRepository, KaznaMapper kaznaMapper) {
        super(kaznaRepository, kaznaMapper);
        this.kaznaRepository = kaznaRepository;
        this.kaznaMapper = kaznaMapper;
    }
}
