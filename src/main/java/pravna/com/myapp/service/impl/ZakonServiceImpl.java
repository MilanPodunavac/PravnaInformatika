package pravna.com.myapp.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pravna.com.myapp.domain.Zakon;
import pravna.com.myapp.repository.ZakonRepository;
import pravna.com.myapp.service.ZakonService;

/**
 * Service Implementation for managing {@link Zakon}.
 */
@Service
public class ZakonServiceImpl implements ZakonService {

    private final Logger log = LoggerFactory.getLogger(ZakonServiceImpl.class);

    private final ZakonRepository zakonRepository;

    public ZakonServiceImpl(ZakonRepository zakonRepository) {
        this.zakonRepository = zakonRepository;
    }

    @Override
    public Zakon save(Zakon zakon) {
        log.debug("Request to save Zakon : {}", zakon);
        return zakonRepository.save(zakon);
    }

    @Override
    public Zakon update(Zakon zakon) {
        log.debug("Request to update Zakon : {}", zakon);
        return zakonRepository.save(zakon);
    }

    @Override
    public Optional<Zakon> partialUpdate(Zakon zakon) {
        log.debug("Request to partially update Zakon : {}", zakon);

        return zakonRepository
            .findById(zakon.getId())
            .map(existingZakon -> {
                if (zakon.getNaziv() != null) {
                    existingZakon.setNaziv(zakon.getNaziv());
                }

                return existingZakon;
            })
            .map(zakonRepository::save);
    }

    @Override
    public Page<Zakon> findAll(Pageable pageable) {
        log.debug("Request to get all Zakons");
        return zakonRepository.findAll(pageable);
    }

    @Override
    public Optional<Zakon> findOne(String id) {
        log.debug("Request to get Zakon : {}", id);
        return zakonRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Zakon : {}", id);
        zakonRepository.deleteById(id);
    }
}
