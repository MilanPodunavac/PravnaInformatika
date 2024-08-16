package pravna.com.myapp.service.extended;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pravna.com.myapp.domain.Kazna;
import pravna.com.myapp.repository.KaznaRepository;
import pravna.com.myapp.repository.extended.KaznaExtendedRepository;
import pravna.com.myapp.service.KaznaService;

@Service
@Primary
public class KaznaExtendedService extends KaznaService {

    private final Logger log = LoggerFactory.getLogger(KaznaService.class);

    private final KaznaExtendedRepository kaznaRepository;

    public KaznaExtendedService(KaznaExtendedRepository kaznaRepository) {
        super(kaznaRepository);
        this.kaznaRepository = kaznaRepository;
    }
}
