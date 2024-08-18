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
import pravna.com.myapp.service.dto.ClanZakonaDTO;
import pravna.com.myapp.service.mapper.ClanZakonaMapper;

/**
 * Service Implementation for managing {@link ClanZakona}.
 */
@Service
public class ClanZakonaServiceImpl implements ClanZakonaService {

    private final Logger log = LoggerFactory.getLogger(ClanZakonaServiceImpl.class);

    private final ClanZakonaRepository clanZakonaRepository;

    private final ClanZakonaMapper clanZakonaMapper;

    public ClanZakonaServiceImpl(ClanZakonaRepository clanZakonaRepository, ClanZakonaMapper clanZakonaMapper) {
        this.clanZakonaRepository = clanZakonaRepository;
        this.clanZakonaMapper = clanZakonaMapper;
    }

    @Override
    public ClanZakonaDTO save(ClanZakonaDTO clanZakonaDTO) {
        log.debug("Request to save ClanZakona : {}", clanZakonaDTO);
        ClanZakona clanZakona = clanZakonaMapper.toEntity(clanZakonaDTO);
        clanZakona = clanZakonaRepository.save(clanZakona);
        return clanZakonaMapper.toDto(clanZakona);
    }

    @Override
    public ClanZakonaDTO update(ClanZakonaDTO clanZakonaDTO) {
        log.debug("Request to update ClanZakona : {}", clanZakonaDTO);
        ClanZakona clanZakona = clanZakonaMapper.toEntity(clanZakonaDTO);
        clanZakona = clanZakonaRepository.save(clanZakona);
        return clanZakonaMapper.toDto(clanZakona);
    }

    @Override
    public Optional<ClanZakonaDTO> partialUpdate(ClanZakonaDTO clanZakonaDTO) {
        log.debug("Request to partially update ClanZakona : {}", clanZakonaDTO);

        return clanZakonaRepository
            .findById(clanZakonaDTO.getId())
            .map(existingClanZakona -> {
                clanZakonaMapper.partialUpdate(existingClanZakona, clanZakonaDTO);

                return existingClanZakona;
            })
            .map(clanZakonaRepository::save)
            .map(clanZakonaMapper::toDto);
    }

    @Override
    public Page<ClanZakonaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ClanZakonas");
        return clanZakonaRepository.findAll(pageable).map(clanZakonaMapper::toDto);
    }

    @Override
    public Optional<ClanZakonaDTO> findOne(String id) {
        log.debug("Request to get ClanZakona : {}", id);
        return clanZakonaRepository.findById(id).map(clanZakonaMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete ClanZakona : {}", id);
        clanZakonaRepository.deleteById(id);
    }
}
