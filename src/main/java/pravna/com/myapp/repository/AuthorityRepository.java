package pravna.com.myapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pravna.com.myapp.domain.Authority;

/**
 * Spring Data MongoDB repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends MongoRepository<Authority, String> {}
