package pravna.com.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SudMapperTest {

    private SudMapper sudMapper;

    @BeforeEach
    public void setUp() {
        sudMapper = new SudMapperImpl();
    }
}
