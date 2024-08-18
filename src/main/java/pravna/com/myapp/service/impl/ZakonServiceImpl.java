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
import pravna.com.myapp.service.dto.ZakonDTO;
import pravna.com.myapp.service.mapper.ZakonMapper;

/**
 * Service Implementation for managing {@link Zakon}.
 */
@Service
public class ZakonServiceImpl implements ZakonService {

    private final Logger log = LoggerFactory.getLogger(ZakonServiceImpl.class);

    private final ZakonRepository zakonRepository;

    private final ZakonMapper zakonMapper;

    public ZakonServiceImpl(ZakonRepository zakonRepository, ZakonMapper zakonMapper) {
        this.zakonRepository = zakonRepository;
        this.zakonMapper = zakonMapper;
    }

    @Override
    public ZakonDTO save(ZakonDTO zakonDTO) {
        log.debug("Request to save Zakon : {}", zakonDTO);
        Zakon zakon = zakonMapper.toEntity(zakonDTO);
        zakon = zakonRepository.save(zakon);
        return zakonMapper.toDto(zakon);
    }

    @Override
    public ZakonDTO update(ZakonDTO zakonDTO) {
        log.debug("Request to update Zakon : {}", zakonDTO);
        Zakon zakon = zakonMapper.toEntity(zakonDTO);
        zakon = zakonRepository.save(zakon);
        return zakonMapper.toDto(zakon);
    }

    @Override
    public Optional<ZakonDTO> partialUpdate(ZakonDTO zakonDTO) {
        log.debug("Request to partially update Zakon : {}", zakonDTO);

        return zakonRepository
            .findById(zakonDTO.getId())
            .map(existingZakon -> {
                zakonMapper.partialUpdate(existingZakon, zakonDTO);

                return existingZakon;
            })
            .map(zakonRepository::save)
            .map(zakonMapper::toDto);
    }

    @Override
    public Page<ZakonDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Zakons");
        return zakonRepository.findAll(pageable).map(zakonMapper::toDto);
    }

    @Override
    public Optional<ZakonDTO> findOne(String id) {
        log.debug("Request to get Zakon : {}", id);
        return zakonRepository.findById(id).map(zakonMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Zakon : {}", id);
        zakonRepository.deleteById(id);
    }
}
