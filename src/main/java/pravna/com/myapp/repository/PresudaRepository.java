package pravna.com.myapp.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pravna.com.myapp.domain.Presuda;

/**
 * Spring Data MongoDB repository for the Presuda entity.
 */
@Repository
public interface PresudaRepository extends MongoRepository<Presuda, String> {
    @Query("{}")
    Page<Presuda> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    List<Presuda> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Optional<Presuda> findOneWithEagerRelationships(String id);
}
