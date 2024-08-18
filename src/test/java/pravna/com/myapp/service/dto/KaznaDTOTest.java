package pravna.com.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pravna.com.myapp.web.rest.TestUtil;

class KaznaDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(KaznaDTO.class);
        KaznaDTO kaznaDTO1 = new KaznaDTO();
        kaznaDTO1.setId("id1");
        KaznaDTO kaznaDTO2 = new KaznaDTO();
        assertThat(kaznaDTO1).isNotEqualTo(kaznaDTO2);
        kaznaDTO2.setId(kaznaDTO1.getId());
        assertThat(kaznaDTO1).isEqualTo(kaznaDTO2);
        kaznaDTO2.setId("id2");
        assertThat(kaznaDTO1).isNotEqualTo(kaznaDTO2);
        kaznaDTO1.setId(null);
        assertThat(kaznaDTO1).isNotEqualTo(kaznaDTO2);
    }
}
