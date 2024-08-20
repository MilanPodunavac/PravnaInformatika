package pravna.com.myapp.service.extended.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pravna.com.myapp.repository.extended.ZakonExtendedRepository;
import pravna.com.myapp.service.extended.ZakonExtendedService;
import pravna.com.myapp.service.impl.ZakonServiceImpl;
import pravna.com.myapp.service.mapper.ZakonMapper;

@Service
@Primary
public class ZakonExtendedServiceImpl extends ZakonServiceImpl implements ZakonExtendedService {

    private final Logger log = LoggerFactory.getLogger(ZakonServiceImpl.class);

    private final ZakonExtendedRepository zakonRepository;

    private final ZakonMapper zakonMapper;

    public ZakonExtendedServiceImpl(ZakonExtendedRepository zakonRepository, ZakonMapper zakonMapper) {
        super(zakonRepository, zakonMapper);
        this.zakonRepository = zakonRepository;
        this.zakonMapper = zakonMapper;
    }
}
