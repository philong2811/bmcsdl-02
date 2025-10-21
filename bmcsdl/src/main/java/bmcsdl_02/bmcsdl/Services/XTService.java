package bmcsdl_02.bmcsdl.Services;

import bmcsdl_02.bmcsdl.DTO.RenewalDTO;
import bmcsdl_02.bmcsdl.Entity.Passport;
import bmcsdl_02.bmcsdl.Entity.Renewal;
import bmcsdl_02.bmcsdl.Entity.Resident;
import java.util.List;
import org.hibernate.Internal;

public interface XTService {
    List<RenewalDTO> getRenewal(String username, String password);

    List<Resident> getResident(String username, String password);

    int verify (String username, String password, String cmnd);
}
