package pravna.com.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OptuzeniMapperTest {

    private OptuzeniMapper optuzeniMapper;

    @BeforeEach
    public void setUp() {
        optuzeniMapper = new OptuzeniMapperImpl();
    }
}
