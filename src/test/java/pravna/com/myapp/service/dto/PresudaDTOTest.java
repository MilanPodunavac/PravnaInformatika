package pravna.com.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pravna.com.myapp.web.rest.TestUtil;

class PresudaDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PresudaDTO.class);
        PresudaDTO presudaDTO1 = new PresudaDTO();
        presudaDTO1.setId("id1");
        PresudaDTO presudaDTO2 = new PresudaDTO();
        assertThat(presudaDTO1).isNotEqualTo(presudaDTO2);
        presudaDTO2.setId(presudaDTO1.getId());
        assertThat(presudaDTO1).isEqualTo(presudaDTO2);
        presudaDTO2.setId("id2");
        assertThat(presudaDTO1).isNotEqualTo(presudaDTO2);
        presudaDTO1.setId(null);
        assertThat(presudaDTO1).isNotEqualTo(presudaDTO2);
    }
}
