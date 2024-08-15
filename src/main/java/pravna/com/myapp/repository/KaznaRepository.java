package pravna.com.myapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pravna.com.myapp.domain.Kazna;

/**
 * Spring Data MongoDB repository for the Kazna entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KaznaRepository extends MongoRepository<Kazna, String> {}
