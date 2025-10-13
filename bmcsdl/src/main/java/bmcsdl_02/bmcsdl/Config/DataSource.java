package bmcsdl_02.bmcsdl.Config;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSource {
  public static DriverManagerDataSource createDataSource(String username, String password) {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
    dataSource.setUrl("jdbc:oracle:thin:@localhost:1521/orclpdb");
    dataSource.setUsername(username);
    dataSource.setPassword(password);
    return dataSource;
  }
}
