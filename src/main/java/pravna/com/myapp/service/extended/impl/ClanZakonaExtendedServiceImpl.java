package pravna.com.myapp.service.extended.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pravna.com.myapp.repository.extended.ClanZakonaExtendedRepository;
import pravna.com.myapp.service.extended.ClanZakonaExtendedService;
import pravna.com.myapp.service.impl.ClanZakonaServiceImpl;
import pravna.com.myapp.service.mapper.ClanZakonaMapper;

@Service
@Primary
public class ClanZakonaExtendedServiceImpl extends ClanZakonaServiceImpl implements ClanZakonaExtendedService {

    private final Logger log = LoggerFactory.getLogger(ClanZakonaServiceImpl.class);

    private final ClanZakonaExtendedRepository clanZakonaRepository;

    private final ClanZakonaMapper clanZakonaMapper;

    public ClanZakonaExtendedServiceImpl(ClanZakonaExtendedRepository clanZakonaRepository, ClanZakonaMapper clanZakonaMapper) {
        super(clanZakonaRepository, clanZakonaMapper);
        this.clanZakonaRepository = clanZakonaRepository;
        this.clanZakonaMapper = clanZakonaMapper;
    }
}
