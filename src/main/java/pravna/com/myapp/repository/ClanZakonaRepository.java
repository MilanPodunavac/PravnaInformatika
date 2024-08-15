package pravna.com.myapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pravna.com.myapp.domain.ClanZakona;

/**
 * Spring Data MongoDB repository for the ClanZakona entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClanZakonaRepository extends MongoRepository<ClanZakona, String> {}
