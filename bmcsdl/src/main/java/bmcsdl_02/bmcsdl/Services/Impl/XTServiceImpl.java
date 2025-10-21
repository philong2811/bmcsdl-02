package bmcsdl_02.bmcsdl.Services.Impl;

import bmcsdl_02.bmcsdl.Config.DataSource;
import bmcsdl_02.bmcsdl.DTO.RenewalDTO;
import bmcsdl_02.bmcsdl.Entity.Renewal;
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
    public List<RenewalDTO> getRenewal(String username, String password) {
        DataSource Datasource = null;
        JdbcTemplate jdbcTemplate = new JdbcTemplate(Datasource.createDataSource(username, password));

        String sql = "SELECT * FROM ADMIN_TEST.RENEWAL";
        List<RenewalDTO> rs = jdbcTemplate.query(sql, new RowMapper<RenewalDTO>() {
            public RenewalDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new RenewalDTO(
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

    @Override
    public List<Resident> getResident(String username, String password) {
        DataSource Datasource = null;
        JdbcTemplate jdbcTemplate = new JdbcTemplate(Datasource.createDataSource(username, password));

        String sql = "SELECT * FROM ADMIN_TEST.RESIDENT";
        List<Resident> rs = jdbcTemplate.query(sql, new RowMapper<Resident>() {
            @Override
            public Resident mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Resident(
                        rs.getLong("id"),
                        rs.getString("cmnd"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone_number"),
                        rs.getString("district"),
                        rs.getString("pass_id")
                );
            }
        });
        return rs;
    }

    @Override
    public int verify(String username, String password, String cmnd) {
        DataSource Datasource = null;
        JdbcTemplate jdbcTemplate = new JdbcTemplate(Datasource.createDataSource(username, password));

        String sql = "UPDATE ADMIN_TEST.RENEWAL SET STATUS = 'Đã xác thực' WHERE CMND = ?";

        int count = jdbcTemplate.update(sql, cmnd);
        return count;
    }
}
