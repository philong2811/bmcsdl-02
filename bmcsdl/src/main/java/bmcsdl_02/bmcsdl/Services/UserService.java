package bmcsdl_02.bmcsdl.Services;

import bmcsdl_02.bmcsdl.Config.DataSource;
import bmcsdl_02.bmcsdl.Entity.Renewal;
import bmcsdl_02.bmcsdl.Entity.Users;
import java.util.List;
import java.util.Optional;

public interface UserService {
  Optional<Users> getUser(String username);

  List<Renewal> getRenewal(String username, String password);

  int CreateRenewal(String username, String password, Renewal renewal);
}
