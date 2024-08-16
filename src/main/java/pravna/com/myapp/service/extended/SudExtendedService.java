package pravna.com.myapp.service.extended;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pravna.com.myapp.repository.SudRepository;
import pravna.com.myapp.repository.extended.SudExtendedRepository;
import pravna.com.myapp.service.SudService;

@Service
@Primary
public class SudExtendedService extends SudService {

    private final Logger log = LoggerFactory.getLogger(SudService.class);

    private final SudExtendedRepository sudRepository;

    public SudExtendedService(SudExtendedRepository sudRepository) {
        super(sudRepository);
        this.sudRepository = sudRepository;
    }
}
