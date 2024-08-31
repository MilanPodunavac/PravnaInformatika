package pravna.com.myapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pravna.com.myapp.domain.Optuznica;

/**
 * Spring Data MongoDB repository for the Optuznica entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OptuznicaRepository extends MongoRepository<Optuznica, String> {}
