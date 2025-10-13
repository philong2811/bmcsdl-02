package bmcsdl_02.bmcsdl.Entity;

import bmcsdl_02.bmcsdl.Common.RoleEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users implements UserDetails {
  private String username;
  private String password;

  @Id
  private Long id;
  @Enumerated(EnumType.STRING)
  private RoleEnum role;

  private Date create_date;
  private Date update_date;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }
  @Override
  public boolean isEnabled() {
    return true;
  }

  @JsonBackReference
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Passport> passports;

  @JsonBackReference
  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  private Resident resident;

  @JsonBackReference
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Renewal> renewals;
}