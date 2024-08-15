package pravna.com.myapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pravna.com.myapp.domain.Povreda;

/**
 * Spring Data MongoDB repository for the Povreda entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PovredaRepository extends MongoRepository<Povreda, String> {}
