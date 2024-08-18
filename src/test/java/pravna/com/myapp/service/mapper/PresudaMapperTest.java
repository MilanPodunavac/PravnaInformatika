package pravna.com.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PresudaMapperTest {

    private PresudaMapper presudaMapper;

    @BeforeEach
    public void setUp() {
        presudaMapper = new PresudaMapperImpl();
    }
}
