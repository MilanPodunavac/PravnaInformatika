package pravna.com.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pravna.com.myapp.web.rest.TestUtil;

class OptuzeniTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Optuzeni.class);
        Optuzeni optuzeni1 = new Optuzeni();
        optuzeni1.setId("id1");
        Optuzeni optuzeni2 = new Optuzeni();
        optuzeni2.setId(optuzeni1.getId());
        assertThat(optuzeni1).isEqualTo(optuzeni2);
        optuzeni2.setId("id2");
        assertThat(optuzeni1).isNotEqualTo(optuzeni2);
        optuzeni1.setId(null);
        assertThat(optuzeni1).isNotEqualTo(optuzeni2);
    }
}
