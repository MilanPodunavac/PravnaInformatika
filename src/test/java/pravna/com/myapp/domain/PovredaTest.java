package pravna.com.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pravna.com.myapp.web.rest.TestUtil;

class PovredaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Povreda.class);
        Povreda povreda1 = new Povreda();
        povreda1.setId("id1");
        Povreda povreda2 = new Povreda();
        povreda2.setId(povreda1.getId());
        assertThat(povreda1).isEqualTo(povreda2);
        povreda2.setId("id2");
        assertThat(povreda1).isNotEqualTo(povreda2);
        povreda1.setId(null);
        assertThat(povreda1).isNotEqualTo(povreda2);
    }
}
