package pravna.com.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pravna.com.myapp.web.rest.TestUtil;

class OptuzeniDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OptuzeniDTO.class);
        OptuzeniDTO optuzeniDTO1 = new OptuzeniDTO();
        optuzeniDTO1.setId("id1");
        OptuzeniDTO optuzeniDTO2 = new OptuzeniDTO();
        assertThat(optuzeniDTO1).isNotEqualTo(optuzeniDTO2);
        optuzeniDTO2.setId(optuzeniDTO1.getId());
        assertThat(optuzeniDTO1).isEqualTo(optuzeniDTO2);
        optuzeniDTO2.setId("id2");
        assertThat(optuzeniDTO1).isNotEqualTo(optuzeniDTO2);
        optuzeniDTO1.setId(null);
        assertThat(optuzeniDTO1).isNotEqualTo(optuzeniDTO2);
    }
}
