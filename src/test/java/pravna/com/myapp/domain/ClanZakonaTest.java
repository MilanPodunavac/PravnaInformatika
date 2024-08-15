package pravna.com.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pravna.com.myapp.web.rest.TestUtil;

class ClanZakonaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClanZakona.class);
        ClanZakona clanZakona1 = new ClanZakona();
        clanZakona1.setId("id1");
        ClanZakona clanZakona2 = new ClanZakona();
        clanZakona2.setId(clanZakona1.getId());
        assertThat(clanZakona1).isEqualTo(clanZakona2);
        clanZakona2.setId("id2");
        assertThat(clanZakona1).isNotEqualTo(clanZakona2);
        clanZakona1.setId(null);
        assertThat(clanZakona1).isNotEqualTo(clanZakona2);
    }
}
