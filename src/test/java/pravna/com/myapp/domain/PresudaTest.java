package pravna.com.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pravna.com.myapp.web.rest.TestUtil;

class PresudaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Presuda.class);
        Presuda presuda1 = new Presuda();
        presuda1.setId("id1");
        Presuda presuda2 = new Presuda();
        presuda2.setId(presuda1.getId());
        assertThat(presuda1).isEqualTo(presuda2);
        presuda2.setId("id2");
        assertThat(presuda1).isNotEqualTo(presuda2);
        presuda1.setId(null);
        assertThat(presuda1).isNotEqualTo(presuda2);
    }
}
