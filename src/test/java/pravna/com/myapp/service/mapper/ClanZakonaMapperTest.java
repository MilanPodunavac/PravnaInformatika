package pravna.com.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClanZakonaMapperTest {

    private ClanZakonaMapper clanZakonaMapper;

    @BeforeEach
    public void setUp() {
        clanZakonaMapper = new ClanZakonaMapperImpl();
    }
}
