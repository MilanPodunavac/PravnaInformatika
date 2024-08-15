package pravna.com.myapp.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pravna.com.myapp.domain.Presuda;
import pravna.com.myapp.repository.PresudaRepository;
import pravna.com.myapp.service.PresudaService;

/**
 * Service Implementation for managing {@link Presuda}.
 */
@Service
public class PresudaServiceImpl implements PresudaService {

    private final Logger log = LoggerFactory.getLogger(PresudaServiceImpl.class);

    private final PresudaRepository presudaRepository;

    public PresudaServiceImpl(PresudaRepository presudaRepository) {
        this.presudaRepository = presudaRepository;
    }

    @Override
    public Presuda save(Presuda presuda) {
        log.debug("Request to save Presuda : {}", presuda);
        return presudaRepository.save(presuda);
    }

    @Override
    public Presuda update(Presuda presuda) {
        log.debug("Request to update Presuda : {}", presuda);
        return presudaRepository.save(presuda);
    }

    @Override
    public Optional<Presuda> partialUpdate(Presuda presuda) {
        log.debug("Request to partially update Presuda : {}", presuda);

        return presudaRepository
            .findById(presuda.getId())
            .map(existingPresuda -> {
                if (presuda.getTekst() != null) {
                    existingPresuda.setTekst(presuda.getTekst());
                }
                if (presuda.getDatum() != null) {
                    existingPresuda.setDatum(presuda.getDatum());
                }
                if (presuda.getDatumObjave() != null) {
                    existingPresuda.setDatumObjave(presuda.getDatumObjave());
                }
                if (presuda.getDatumPritvora() != null) {
                    existingPresuda.setDatumPritvora(presuda.getDatumPritvora());
                }
                if (presuda.getTip() != null) {
                    existingPresuda.setTip(presuda.getTip());
                }
                if (presuda.getBroj() != null) {
                    existingPresuda.setBroj(presuda.getBroj());
                }
                if (presuda.getGodina() != null) {
                    existingPresuda.setGodina(presuda.getGodina());
                }
                if (presuda.getOptuznica() != null) {
                    existingPresuda.setOptuznica(presuda.getOptuznica());
                }
                if (presuda.getDatumOptuznice() != null) {
                    existingPresuda.setDatumOptuznice(presuda.getDatumOptuznice());
                }
                if (presuda.getPokusaj() != null) {
                    existingPresuda.setPokusaj(presuda.getPokusaj());
                }
                if (presuda.getKrivica() != null) {
                    existingPresuda.setKrivica(presuda.getKrivica());
                }
                if (presuda.getNacin() != null) {
                    existingPresuda.setNacin(presuda.getNacin());
                }

                return existingPresuda;
            })
            .map(presudaRepository::save);
    }

    @Override
    public Page<Presuda> findAll(Pageable pageable) {
        log.debug("Request to get all Presudas");
        return presudaRepository.findAll(pageable);
    }

    @Override
    public Optional<Presuda> findOne(String id) {
        log.debug("Request to get Presuda : {}", id);
        return presudaRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Presuda : {}", id);
        presudaRepository.deleteById(id);
    }
}
