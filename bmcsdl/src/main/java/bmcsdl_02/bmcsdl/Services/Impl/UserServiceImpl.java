package bmcsdl_02.bmcsdl.Services.Impl;

import bmcsdl_02.bmcsdl.Config.DataSource;
import bmcsdl_02.bmcsdl.Entity.Users;
import bmcsdl_02.bmcsdl.Repository.UserRepository;
import bmcsdl_02.bmcsdl.Services.UserService;
import jakarta.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService{
  @Autowired
  UserRepository userRepository;
  @Autowired
  JdbcTemplate jdbcTemplate;

  @Override
  public List<Users> getAll() {
    return userRepository.findAll();
  }

  @Override
  public Optional<Users> getUser(String username) {
    return userRepository.findByUsername(username);
  }

  @Override
  public List<Users> getUserByRole(String role) {
    return userRepository.findByRole(role);
  }

  @Override
  public Optional<Users> getByUserName(String username) {
    return userRepository.findByUsername(username);
  }
  
  public List<String> testConnect(String username, String password) {
    DataSource Datasource = null;
    JdbcTemplate jdbcTemplate = new JdbcTemplate(Datasource.createDataSource(username, password));

    if (jdbcTemplate == null) {
      System.out.println("Repository thread: " + Thread.currentThread().getName());
      throw new IllegalStateException("JdbcTemplate is not initialized!");
    } else {
      String sql = "SELECT SYS_CONTEXT('USERENV', 'SESSION_USER') AS session_user FROM dual"; // Thay đổi theo nhu cầu
      List<String> results = jdbcTemplate.query(sql, new RowMapper<String>() {
        @Override
        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
          return rs.getString("session_user");
        }
      });
      return results;
    }
  }
}
