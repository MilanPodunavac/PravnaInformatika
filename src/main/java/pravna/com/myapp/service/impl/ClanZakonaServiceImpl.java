package pravna.com.myapp.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pravna.com.myapp.domain.ClanZakona;
import pravna.com.myapp.repository.ClanZakonaRepository;
import pravna.com.myapp.service.ClanZakonaService;

/**
 * Service Implementation for managing {@link ClanZakona}.
 */
@Service
public class ClanZakonaServiceImpl implements ClanZakonaService {

    private final Logger log = LoggerFactory.getLogger(ClanZakonaServiceImpl.class);

    private final ClanZakonaRepository clanZakonaRepository;

    public ClanZakonaServiceImpl(ClanZakonaRepository clanZakonaRepository) {
        this.clanZakonaRepository = clanZakonaRepository;
    }

    @Override
    public ClanZakona save(ClanZakona clanZakona) {
        log.debug("Request to save ClanZakona : {}", clanZakona);
        return clanZakonaRepository.save(clanZakona);
    }

    @Override
    public ClanZakona update(ClanZakona clanZakona) {
        log.debug("Request to update ClanZakona : {}", clanZakona);
        return clanZakonaRepository.save(clanZakona);
    }

    @Override
    public Optional<ClanZakona> partialUpdate(ClanZakona clanZakona) {
        log.debug("Request to partially update ClanZakona : {}", clanZakona);

        return clanZakonaRepository
            .findById(clanZakona.getId())
            .map(existingClanZakona -> {
                if (clanZakona.getBroj() != null) {
                    existingClanZakona.setBroj(clanZakona.getBroj());
                }
                if (clanZakona.getGlava() != null) {
                    existingClanZakona.setGlava(clanZakona.getGlava());
                }
                if (clanZakona.getNaziv() != null) {
                    existingClanZakona.setNaziv(clanZakona.getNaziv());
                }
                if (clanZakona.getTekst() != null) {
                    existingClanZakona.setTekst(clanZakona.getTekst());
                }

                return existingClanZakona;
            })
            .map(clanZakonaRepository::save);
    }

    @Override
    public Page<ClanZakona> findAll(Pageable pageable) {
        log.debug("Request to get all ClanZakonas");
        return clanZakonaRepository.findAll(pageable);
    }

    @Override
    public Optional<ClanZakona> findOne(String id) {
        log.debug("Request to get ClanZakona : {}", id);
        return clanZakonaRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete ClanZakona : {}", id);
        clanZakonaRepository.deleteById(id);
    }
}
