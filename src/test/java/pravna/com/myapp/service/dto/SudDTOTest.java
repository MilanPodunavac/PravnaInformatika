package pravna.com.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pravna.com.myapp.web.rest.TestUtil;

class SudDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SudDTO.class);
        SudDTO sudDTO1 = new SudDTO();
        sudDTO1.setId("id1");
        SudDTO sudDTO2 = new SudDTO();
        assertThat(sudDTO1).isNotEqualTo(sudDTO2);
        sudDTO2.setId(sudDTO1.getId());
        assertThat(sudDTO1).isEqualTo(sudDTO2);
        sudDTO2.setId("id2");
        assertThat(sudDTO1).isNotEqualTo(sudDTO2);
        sudDTO1.setId(null);
        assertThat(sudDTO1).isNotEqualTo(sudDTO2);
    }
}
