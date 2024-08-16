package pravna.com.myapp.repository.extended;

import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pravna.com.myapp.domain.ClanZakona;
import pravna.com.myapp.repository.ClanZakonaRepository;

/**
 * Spring Data MongoDB repository for the ClanZakona entity.
 */
@SuppressWarnings("unused")
@Repository
@Primary
public interface ClanZakonaExtendedRepository extends ClanZakonaRepository {}
