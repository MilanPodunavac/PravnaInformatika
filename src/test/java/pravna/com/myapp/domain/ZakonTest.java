package pravna.com.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pravna.com.myapp.web.rest.TestUtil;

class ZakonTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Zakon.class);
        Zakon zakon1 = new Zakon();
        zakon1.setId("id1");
        Zakon zakon2 = new Zakon();
        zakon2.setId(zakon1.getId());
        assertThat(zakon1).isEqualTo(zakon2);
        zakon2.setId("id2");
        assertThat(zakon1).isNotEqualTo(zakon2);
        zakon1.setId(null);
        assertThat(zakon1).isNotEqualTo(zakon2);
    }
}
