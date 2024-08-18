package pravna.com.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pravna.com.myapp.web.rest.TestUtil;

class ZakonDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ZakonDTO.class);
        ZakonDTO zakonDTO1 = new ZakonDTO();
        zakonDTO1.setId("id1");
        ZakonDTO zakonDTO2 = new ZakonDTO();
        assertThat(zakonDTO1).isNotEqualTo(zakonDTO2);
        zakonDTO2.setId(zakonDTO1.getId());
        assertThat(zakonDTO1).isEqualTo(zakonDTO2);
        zakonDTO2.setId("id2");
        assertThat(zakonDTO1).isNotEqualTo(zakonDTO2);
        zakonDTO1.setId(null);
        assertThat(zakonDTO1).isNotEqualTo(zakonDTO2);
    }
}
