package pravna.com.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pravna.com.myapp.web.rest.TestUtil;

class OptuznicaDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OptuznicaDTO.class);
        OptuznicaDTO optuznicaDTO1 = new OptuznicaDTO();
        optuznicaDTO1.setId("id1");
        OptuznicaDTO optuznicaDTO2 = new OptuznicaDTO();
        assertThat(optuznicaDTO1).isNotEqualTo(optuznicaDTO2);
        optuznicaDTO2.setId(optuznicaDTO1.getId());
        assertThat(optuznicaDTO1).isEqualTo(optuznicaDTO2);
        optuznicaDTO2.setId("id2");
        assertThat(optuznicaDTO1).isNotEqualTo(optuznicaDTO2);
        optuznicaDTO1.setId(null);
        assertThat(optuznicaDTO1).isNotEqualTo(optuznicaDTO2);
    }
}
