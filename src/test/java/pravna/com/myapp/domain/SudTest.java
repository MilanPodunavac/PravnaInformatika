package pravna.com.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pravna.com.myapp.web.rest.TestUtil;

class SudTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Sud.class);
        Sud sud1 = new Sud();
        sud1.setId("id1");
        Sud sud2 = new Sud();
        sud2.setId(sud1.getId());
        assertThat(sud1).isEqualTo(sud2);
        sud2.setId("id2");
        assertThat(sud1).isNotEqualTo(sud2);
        sud1.setId(null);
        assertThat(sud1).isNotEqualTo(sud2);
    }
}
