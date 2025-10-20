package bmcsdl_02.bmcsdl.Services.Impl;

import bmcsdl_02.bmcsdl.Config.DataSource;
import bmcsdl_02.bmcsdl.Entity.RenewRole;
import bmcsdl_02.bmcsdl.Entity.Renewal;
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
  public Optional<Users> getUser(String username) {
    return userRepository.findByUsername(username);
  }

  @Override
  public List<Renewal> getRenewal(String username, String password) {
    DataSource Datasource = new DataSource();
    JdbcTemplate jdbcTemplate = new JdbcTemplate(Datasource.createDataSource(username, password));

    String sql = "SELECT * FROM ADMIN_TEST.RENEWAL";
    List<Renewal> rs = jdbcTemplate.query(sql, new RowMapper<Renewal>() {
      @Override
      public Renewal mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Renewal(
            rs.getLong("id"),
            rs.getDate("create_date"),
            rs.getString("status"),
            rs.getString("district"),
            rs.getLong("verified_by"),
            rs.getLong("approved_by"),
            rs.getString("descriptions"),
            rs.getString("cmnd"),
            rs.getLong("pass_id")
        );
      }
    });
    return rs;
  }

  public int CreateRenewal(String username, String password, Renewal renewal) {
    DataSource Datasource = null;
    JdbcTemplate jdbcTemplate = new JdbcTemplate(Datasource.createDataSource(username, password));

    String sql = "INSERT INTO ADMIN_TEST.RENEWAL(cmnd, district, descriptions, create_date, status) values (?,?,?,?,?)";

    int count = jdbcTemplate.update(sql, renewal.getCmnd(), renewal.getDistrict(), renewal.getDescriptions(), renewal.getCreate_date(), renewal.getStatus());
    return count;
  }
}