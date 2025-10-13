package bmcsdl_02.bmcsdl.Config;

public class UserContext {
  public static final UserCredentials currentUser = new UserCredentials();

  public UserContext() {

  }

  public static void setCurrentUser(String username, String password) {
    currentUser.setUsername(username);
    currentUser.setPassword(password);
  }

  public static UserCredentials getCurrentUser() {
    return currentUser;
  }

  // Xóa thông tin người dùng
  public static void clear() {
    currentUser.clear();
  }
}
