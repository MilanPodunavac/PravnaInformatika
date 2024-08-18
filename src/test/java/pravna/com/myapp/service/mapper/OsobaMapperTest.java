package pravna.com.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OsobaMapperTest {

    private OsobaMapper osobaMapper;

    @BeforeEach
    public void setUp() {
        osobaMapper = new OsobaMapperImpl();
    }
}
