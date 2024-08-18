package pravna.com.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pravna.com.myapp.web.rest.TestUtil;

class ClanZakonaDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClanZakonaDTO.class);
        ClanZakonaDTO clanZakonaDTO1 = new ClanZakonaDTO();
        clanZakonaDTO1.setId("id1");
        ClanZakonaDTO clanZakonaDTO2 = new ClanZakonaDTO();
        assertThat(clanZakonaDTO1).isNotEqualTo(clanZakonaDTO2);
        clanZakonaDTO2.setId(clanZakonaDTO1.getId());
        assertThat(clanZakonaDTO1).isEqualTo(clanZakonaDTO2);
        clanZakonaDTO2.setId("id2");
        assertThat(clanZakonaDTO1).isNotEqualTo(clanZakonaDTO2);
        clanZakonaDTO1.setId(null);
        assertThat(clanZakonaDTO1).isNotEqualTo(clanZakonaDTO2);
    }
}
