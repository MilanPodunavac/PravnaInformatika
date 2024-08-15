package pravna.com.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pravna.com.myapp.web.rest.TestUtil;

class RadnjaPresudeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RadnjaPresude.class);
        RadnjaPresude radnjaPresude1 = new RadnjaPresude();
        radnjaPresude1.setId("id1");
        RadnjaPresude radnjaPresude2 = new RadnjaPresude();
        radnjaPresude2.setId(radnjaPresude1.getId());
        assertThat(radnjaPresude1).isEqualTo(radnjaPresude2);
        radnjaPresude2.setId("id2");
        assertThat(radnjaPresude1).isNotEqualTo(radnjaPresude2);
        radnjaPresude1.setId(null);
        assertThat(radnjaPresude1).isNotEqualTo(radnjaPresude2);
    }
}
