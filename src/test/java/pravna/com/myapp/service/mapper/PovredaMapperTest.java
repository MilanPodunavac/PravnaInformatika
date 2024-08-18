package pravna.com.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PovredaMapperTest {

    private PovredaMapper povredaMapper;

    @BeforeEach
    public void setUp() {
        povredaMapper = new PovredaMapperImpl();
    }
}
