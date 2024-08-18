package pravna.com.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pravna.com.myapp.web.rest.TestUtil;

class PovredaDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PovredaDTO.class);
        PovredaDTO povredaDTO1 = new PovredaDTO();
        povredaDTO1.setId("id1");
        PovredaDTO povredaDTO2 = new PovredaDTO();
        assertThat(povredaDTO1).isNotEqualTo(povredaDTO2);
        povredaDTO2.setId(povredaDTO1.getId());
        assertThat(povredaDTO1).isEqualTo(povredaDTO2);
        povredaDTO2.setId("id2");
        assertThat(povredaDTO1).isNotEqualTo(povredaDTO2);
        povredaDTO1.setId(null);
        assertThat(povredaDTO1).isNotEqualTo(povredaDTO2);
    }
}
