package pravna.com.myapp.service.extended.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pravna.com.myapp.repository.ZakonRepository;
import pravna.com.myapp.repository.extended.ZakonExtendedRepository;
import pravna.com.myapp.service.extended.ZakonExtendedService;
import pravna.com.myapp.service.impl.ZakonServiceImpl;

@Service
@Primary
public class ZakonExtendedServiceImpl extends ZakonServiceImpl implements ZakonExtendedService {

    private final Logger log = LoggerFactory.getLogger(ZakonServiceImpl.class);

    private final ZakonExtendedRepository zakonRepository;

    public ZakonExtendedServiceImpl(ZakonExtendedRepository zakonRepository) {
        super(zakonRepository);
        this.zakonRepository = zakonRepository;
    }
}
