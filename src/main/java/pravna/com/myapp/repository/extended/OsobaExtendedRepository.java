package pravna.com.myapp.repository.extended;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pravna.com.myapp.repository.OsobaRepository;

@Repository
@Primary
public interface OsobaExtendedRepository extends OsobaRepository {}
