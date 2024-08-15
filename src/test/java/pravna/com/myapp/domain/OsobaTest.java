package pravna.com.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pravna.com.myapp.web.rest.TestUtil;

class OsobaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Osoba.class);
        Osoba osoba1 = new Osoba();
        osoba1.setId("id1");
        Osoba osoba2 = new Osoba();
        osoba2.setId(osoba1.getId());
        assertThat(osoba1).isEqualTo(osoba2);
        osoba2.setId("id2");
        assertThat(osoba1).isNotEqualTo(osoba2);
        osoba1.setId(null);
        assertThat(osoba1).isNotEqualTo(osoba2);
    }
}
