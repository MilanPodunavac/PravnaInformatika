package pravna.com.myapp.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pravna.com.myapp.domain.Osoba;

/**
 * Spring Data MongoDB repository for the Osoba entity.
 */
@Repository
public interface OsobaRepository extends MongoRepository<Osoba, String> {
    @Query("{}")
    Page<Osoba> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    List<Osoba> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Optional<Osoba> findOneWithEagerRelationships(String id);
}
