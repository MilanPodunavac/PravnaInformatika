package pravna.com.myapp.repository.extended;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pravna.com.myapp.repository.PovredaRepository;

@Repository
@Primary
public interface PovredaExtendedRepository extends PovredaRepository {}
