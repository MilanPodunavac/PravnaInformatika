package pravna.com.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ZakonMapperTest {

    private ZakonMapper zakonMapper;

    @BeforeEach
    public void setUp() {
        zakonMapper = new ZakonMapperImpl();
    }
}
