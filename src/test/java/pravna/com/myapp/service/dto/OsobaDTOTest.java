package pravna.com.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pravna.com.myapp.web.rest.TestUtil;

class OsobaDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OsobaDTO.class);
        OsobaDTO osobaDTO1 = new OsobaDTO();
        osobaDTO1.setId("id1");
        OsobaDTO osobaDTO2 = new OsobaDTO();
        assertThat(osobaDTO1).isNotEqualTo(osobaDTO2);
        osobaDTO2.setId(osobaDTO1.getId());
        assertThat(osobaDTO1).isEqualTo(osobaDTO2);
        osobaDTO2.setId("id2");
        assertThat(osobaDTO1).isNotEqualTo(osobaDTO2);
        osobaDTO1.setId(null);
        assertThat(osobaDTO1).isNotEqualTo(osobaDTO2);
    }
}
