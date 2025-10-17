package bmcsdl_02.bmcsdl.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Date create_date;
  private String status;
  private String district;
  private Long verified_by;
  private Long approved_by;
  private String descriptions;
  private Long user_id;
  private Long pass_id;
//  @ManyToOne
//  @JoinColumn(name = "user_id")
//  private Users user;
//
//  @OneToOne
//  @JoinColumn(name = "pass_id")
//  private Passport passport;

}
