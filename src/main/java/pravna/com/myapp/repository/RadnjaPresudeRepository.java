package pravna.com.myapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pravna.com.myapp.domain.RadnjaPresude;

/**
 * Spring Data MongoDB repository for the RadnjaPresude entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RadnjaPresudeRepository extends MongoRepository<RadnjaPresude, String> {}
