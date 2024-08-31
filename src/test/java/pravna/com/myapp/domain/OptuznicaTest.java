package pravna.com.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pravna.com.myapp.web.rest.TestUtil;

class OptuznicaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Optuznica.class);
        Optuznica optuznica1 = new Optuznica();
        optuznica1.setId("id1");
        Optuznica optuznica2 = new Optuznica();
        optuznica2.setId(optuznica1.getId());
        assertThat(optuznica1).isEqualTo(optuznica2);
        optuznica2.setId("id2");
        assertThat(optuznica1).isNotEqualTo(optuznica2);
        optuznica1.setId(null);
        assertThat(optuznica1).isNotEqualTo(optuznica2);
    }
}
