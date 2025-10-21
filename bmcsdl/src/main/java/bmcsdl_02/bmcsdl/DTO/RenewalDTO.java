package bmcsdl_02.bmcsdl.DTO;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RenewalDTO {
  private Long id;

  private Date create_date;
  private String status;
  private String district;
  private Long verified_by;
  private Long approved_by;
  private String descriptions;
  private String cmnd;
  private Long pass_id;
}
