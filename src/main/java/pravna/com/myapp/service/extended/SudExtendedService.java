package pravna.com.myapp.service.extended;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pravna.com.myapp.repository.extended.SudExtendedRepository;
import pravna.com.myapp.service.SudService;
import pravna.com.myapp.service.mapper.SudMapper;

@Service
@Primary
public class SudExtendedService extends SudService {

    private final Logger log = LoggerFactory.getLogger(SudService.class);

    private final SudExtendedRepository sudRepository;

    private final SudMapper sudMapper;

    public SudExtendedService(SudExtendedRepository sudRepository, SudMapper sudMapper) {
        super(sudRepository, sudMapper);
        this.sudRepository = sudRepository;
        this.sudMapper = sudMapper;
    }
}
