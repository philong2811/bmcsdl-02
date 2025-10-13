package bmcsdl_02.bmcsdl.Services;

import bmcsdl_02.bmcsdl.Entity.Users;
import java.util.List;
import java.util.Optional;

public interface UserService {
  public List<Users> getAll();

  public Optional<Users> getUser(String username);

  public List<Users> getUserByRole(String role);

  public List<String> testConnect(String username, String password);

  public Optional<Users> getByUserName(String username);

}
