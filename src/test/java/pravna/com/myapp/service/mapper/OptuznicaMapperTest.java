package pravna.com.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OptuznicaMapperTest {

    private OptuznicaMapper optuznicaMapper;

    @BeforeEach
    public void setUp() {
        optuznicaMapper = new OptuznicaMapperImpl();
    }
}
