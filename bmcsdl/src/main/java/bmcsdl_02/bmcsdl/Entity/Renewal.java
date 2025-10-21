package bmcsdl_02.bmcsdl.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
  private String cmnd;
  private Long pass_id;

   @Transient
  private String passport_id; // derived from passport by cmnd for UI
  
  // Manual getter for pass_id to ensure compatibility
  public Long getPass_id() {
    return pass_id;
  }
  
//  @ManyToOne
//  @JoinColumn(name = "user_id")
//  private Users user;
//
//  @OneToOne
//  @JoinColumn(name = "pass_id")
//  private Passport passport;
}
