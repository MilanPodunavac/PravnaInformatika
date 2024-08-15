package pravna.com.myapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pravna.com.myapp.domain.Zakon;

/**
 * Spring Data MongoDB repository for the Zakon entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ZakonRepository extends MongoRepository<Zakon, String> {}
