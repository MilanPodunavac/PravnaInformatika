package pravna.com.myapp.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pravna.com.myapp.domain.Kazna;
import pravna.com.myapp.repository.KaznaRepository;
import pravna.com.myapp.service.dto.KaznaDTO;
import pravna.com.myapp.service.mapper.KaznaMapper;

/**
 * Service Implementation for managing {@link Kazna}.
 */
@Service
public class KaznaService {

    private final Logger log = LoggerFactory.getLogger(KaznaService.class);

    private final KaznaRepository kaznaRepository;

    private final KaznaMapper kaznaMapper;

    public KaznaService(KaznaRepository kaznaRepository, KaznaMapper kaznaMapper) {
        this.kaznaRepository = kaznaRepository;
        this.kaznaMapper = kaznaMapper;
    }

    /**
     * Save a kazna.
     *
     * @param kaznaDTO the entity to save.
     * @return the persisted entity.
     */
    public KaznaDTO save(KaznaDTO kaznaDTO) {
        log.debug("Request to save Kazna : {}", kaznaDTO);
        Kazna kazna = kaznaMapper.toEntity(kaznaDTO);
        kazna = kaznaRepository.save(kazna);
        return kaznaMapper.toDto(kazna);
    }

    /**
     * Update a kazna.
     *
     * @param kaznaDTO the entity to save.
     * @return the persisted entity.
     */
    public KaznaDTO update(KaznaDTO kaznaDTO) {
        log.debug("Request to update Kazna : {}", kaznaDTO);
        Kazna kazna = kaznaMapper.toEntity(kaznaDTO);
        kazna = kaznaRepository.save(kazna);
        return kaznaMapper.toDto(kazna);
    }

    /**
     * Partially update a kazna.
     *
     * @param kaznaDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<KaznaDTO> partialUpdate(KaznaDTO kaznaDTO) {
        log.debug("Request to partially update Kazna : {}", kaznaDTO);

        return kaznaRepository
            .findById(kaznaDTO.getId())
            .map(existingKazna -> {
                kaznaMapper.partialUpdate(existingKazna, kaznaDTO);

                return existingKazna;
            })
            .map(kaznaRepository::save)
            .map(kaznaMapper::toDto);
    }

    /**
     * Get all the kaznas.
     *
     * @return the list of entities.
     */
    public List<KaznaDTO> findAll() {
        log.debug("Request to get all Kaznas");
        return kaznaRepository.findAll().stream().map(kaznaMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one kazna by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<KaznaDTO> findOne(String id) {
        log.debug("Request to get Kazna : {}", id);
        return kaznaRepository.findById(id).map(kaznaMapper::toDto);
    }

    /**
     * Delete the kazna by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Kazna : {}", id);
        kaznaRepository.deleteById(id);
    }
}
