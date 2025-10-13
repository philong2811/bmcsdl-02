package bmcsdl_02.bmcsdl.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Renewal {
  @Id
  private Long id;

  private Date submit_date;
  private String status;
  private String district;
  private String verified_by;
  private String approved_by;
  private String descriptions;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private Users user;

  @OneToOne
  @JoinColumn(name = "pass_id")
  private Passport passport;

}
