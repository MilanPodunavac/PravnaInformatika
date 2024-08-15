package pravna.com.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pravna.com.myapp.web.rest.TestUtil;

class KaznaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Kazna.class);
        Kazna kazna1 = new Kazna();
        kazna1.setId("id1");
        Kazna kazna2 = new Kazna();
        kazna2.setId(kazna1.getId());
        assertThat(kazna1).isEqualTo(kazna2);
        kazna2.setId("id2");
        assertThat(kazna1).isNotEqualTo(kazna2);
        kazna1.setId(null);
        assertThat(kazna1).isNotEqualTo(kazna2);
    }
}
