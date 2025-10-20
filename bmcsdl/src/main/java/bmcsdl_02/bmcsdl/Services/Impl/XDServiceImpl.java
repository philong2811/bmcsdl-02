package bmcsdl_02.bmcsdl.Services.Impl;

import bmcsdl_02.bmcsdl.Config.DataSource;
import bmcsdl_02.bmcsdl.Entity.RenewRole;
import bmcsdl_02.bmcsdl.Entity.Renewal;
import bmcsdl_02.bmcsdl.Services.XDService;
import bmcsdl_02.bmcsdl.Services.XTService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class XDServiceImpl implements XDService {

  @Override
  public List<RenewRole> getRenewRole(String username, String password) {
    DataSource Datasource = new DataSource();
    JdbcTemplate jdbcTemplate = new JdbcTemplate(Datasource.createDataSource(username, password));

    String sql = "SELECT * FROM ADMIN_TEST.RENEW_ROLE";
    List<RenewRole> rs = jdbcTemplate.query(sql, new RowMapper<RenewRole>() {
      @Override
      public RenewRole mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new RenewRole(
            rs.getLong("id"),
            rs.getString("rule")
        );
      }
    });
    return rs;
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
  public int verify(String username, String password, String cmnd) {
    DataSource Datasource = null;
    JdbcTemplate jdbcTemplate = new JdbcTemplate(Datasource.createDataSource(username, password));

    String sql = "UPDATE ADMIN_TEST.RENEWAL SET STATUS = 'Đã xét duyệt', DESCRIPTIONS = 'Chờ lưu trữ hồ sơ' WHERE CMND = ?";

    int count = jdbcTemplate.update(sql, cmnd);
    return count;
  }
}
