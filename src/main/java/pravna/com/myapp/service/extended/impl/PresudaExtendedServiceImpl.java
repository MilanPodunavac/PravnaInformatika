package pravna.com.myapp.service.extended.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pravna.com.myapp.repository.PresudaRepository;
import pravna.com.myapp.repository.extended.PresudaExtendedRepository;
import pravna.com.myapp.service.extended.PresudaExtendedService;
import pravna.com.myapp.service.impl.PresudaServiceImpl;

@Service
@Primary
public class PresudaExtendedServiceImpl extends PresudaServiceImpl implements PresudaExtendedService {

    private final Logger log = LoggerFactory.getLogger(PresudaServiceImpl.class);

    private final PresudaExtendedRepository presudaRepository;

    public PresudaExtendedServiceImpl(PresudaExtendedRepository presudaRepository) {
        super(presudaRepository);
        this.presudaRepository = presudaRepository;
    }
}
