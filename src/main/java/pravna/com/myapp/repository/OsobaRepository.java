package pravna.com.myapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pravna.com.myapp.domain.Osoba;

/**
 * Spring Data MongoDB repository for the Osoba entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OsobaRepository extends MongoRepository<Osoba, String> {}
