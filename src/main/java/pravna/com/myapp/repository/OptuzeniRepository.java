package pravna.com.myapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pravna.com.myapp.domain.Optuzeni;

/**
 * Spring Data MongoDB repository for the Optuzeni entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OptuzeniRepository extends MongoRepository<Optuzeni, String> {}
