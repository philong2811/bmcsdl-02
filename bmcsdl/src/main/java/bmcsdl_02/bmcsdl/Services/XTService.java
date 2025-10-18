package bmcsdl_02.bmcsdl.Services;

import bmcsdl_02.bmcsdl.Entity.Passport;
import bmcsdl_02.bmcsdl.Entity.Resident;
import java.util.List;

public interface XTService {
  public List<Resident> getResident(String username, String password);
}
