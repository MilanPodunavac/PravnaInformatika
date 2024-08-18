package pravna.com.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RadnjaPresudeMapperTest {

    private RadnjaPresudeMapper radnjaPresudeMapper;

    @BeforeEach
    public void setUp() {
        radnjaPresudeMapper = new RadnjaPresudeMapperImpl();
    }
}
