package bmcsdl_02.bmcsdl.Services;

import bmcsdl_02.bmcsdl.Entity.RenewRole;
import bmcsdl_02.bmcsdl.Entity.Renewal;
import java.util.List;

public interface XDService {
  List<RenewRole> getRenewRole(String username, String password);
  List<Renewal> getRenewal(String username, String password);

  int verify (String username, String password, String cmnd);

}
