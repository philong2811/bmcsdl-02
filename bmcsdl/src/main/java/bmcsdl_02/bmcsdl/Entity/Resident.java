package bmcsdl_02.bmcsdl.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resident {
  @Id
  private Long id;

  private String name;
  private String address;
  private String phone_number;
  private String district;
  private Long pass_id;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private Users user;

}
