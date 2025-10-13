package bmcsdl_02.bmcsdl.Config;

public class UserCredentials {
  private String username;
  private String password;

  public UserCredentials(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public UserCredentials() {

  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void clear() {
    this.username = null;
    this.password = null;
  }
}
