package pravna.com.myapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pravna.com.myapp.domain.Presuda;

/**
 * Spring Data MongoDB repository for the Presuda entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PresudaRepository extends MongoRepository<Presuda, String> {}
