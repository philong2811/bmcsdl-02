package bmcsdl_02.bmcsdl.Entity;

import jakarta.persistence.Entity;
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
public class Passport {
  @Id
  private String id;

  private Date end_date;
  private Date create_date;
  private String cmnd;

//  @ManyToOne
//  @JoinColumn(name = "user_id")
//  private Users user;
//
//  @OneToOne(mappedBy = "passport", cascade = CascadeType.ALL)
//  private Renewal renewal;

}