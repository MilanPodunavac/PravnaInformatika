package pravna.com.myapp.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CbrDTO implements Serializable {

    private Set<PresudaDTO> presude = new HashSet<>();

    private Set<KaznaDTO> kazne = new HashSet<>();

    public Set<PresudaDTO> getPresude() {
        return presude;
    }

    public void setPresude(Set<PresudaDTO> presude) {
        this.presude = presude;
    }

    public Set<KaznaDTO> getKazne() {
        return kazne;
    }

    public void setKazne(Set<KaznaDTO> kazne) {
        this.kazne = kazne;
    }
}
