package pravna.com.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KaznaMapperTest {

    private KaznaMapper kaznaMapper;

    @BeforeEach
    public void setUp() {
        kaznaMapper = new KaznaMapperImpl();
    }
}
