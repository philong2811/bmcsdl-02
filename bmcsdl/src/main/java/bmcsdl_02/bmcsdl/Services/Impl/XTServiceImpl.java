package bmcsdl_02.bmcsdl.Services.Impl;

import bmcsdl_02.bmcsdl.Config.DataSource;
import bmcsdl_02.bmcsdl.Entity.Passport;
import bmcsdl_02.bmcsdl.Entity.Resident;
import bmcsdl_02.bmcsdl.Services.XTService;
import jakarta.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class XTServiceImpl implements XTService {
  @Override
  public List<Resident> getResident(String username, String password) {
    DataSource Datasource = null;
    JdbcTemplate jdbcTemplate = new JdbcTemplate(Datasource.createDataSource(username, password));

    String sql = "SELECT * FROM ADMIN_TEST.resident";
    List<Resident> rs = jdbcTemplate.query(sql, new RowMapper<Resident>() {
      @Override
      public Resident mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Resident(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getString("cmnd"),
            rs.getString("address"),
            rs.getString("phone_number"),
            rs.getString("district"),
            rs.getString("pass_id")
        );
      }
    });
    return rs;
  }
}
