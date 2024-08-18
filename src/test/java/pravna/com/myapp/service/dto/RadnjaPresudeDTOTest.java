package pravna.com.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pravna.com.myapp.web.rest.TestUtil;

class RadnjaPresudeDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RadnjaPresudeDTO.class);
        RadnjaPresudeDTO radnjaPresudeDTO1 = new RadnjaPresudeDTO();
        radnjaPresudeDTO1.setId("id1");
        RadnjaPresudeDTO radnjaPresudeDTO2 = new RadnjaPresudeDTO();
        assertThat(radnjaPresudeDTO1).isNotEqualTo(radnjaPresudeDTO2);
        radnjaPresudeDTO2.setId(radnjaPresudeDTO1.getId());
        assertThat(radnjaPresudeDTO1).isEqualTo(radnjaPresudeDTO2);
        radnjaPresudeDTO2.setId("id2");
        assertThat(radnjaPresudeDTO1).isNotEqualTo(radnjaPresudeDTO2);
        radnjaPresudeDTO1.setId(null);
        assertThat(radnjaPresudeDTO1).isNotEqualTo(radnjaPresudeDTO2);
    }
}
