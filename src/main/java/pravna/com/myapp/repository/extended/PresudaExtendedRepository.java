package pravna.com.myapp.repository.extended;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pravna.com.myapp.repository.PresudaRepository;

@Repository
@Primary
public interface PresudaExtendedRepository extends PresudaRepository {}
