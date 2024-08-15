package pravna.com.myapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pravna.com.myapp.domain.Sud;

/**
 * Spring Data MongoDB repository for the Sud entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SudRepository extends MongoRepository<Sud, String> {}
