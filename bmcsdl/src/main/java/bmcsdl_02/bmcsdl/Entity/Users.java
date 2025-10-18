package bmcsdl_02.bmcsdl.Entity;

import bmcsdl_02.bmcsdl.Common.RoleEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Collection;
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
  private String cmnd;
  private String district;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Enumerated(EnumType.STRING)
  private RoleEnum role;

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
  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }
//  @JsonBackReference
//  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//  private List<Passport> passports;
//
//  @JsonBackReference
//  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//  private Resident resident;
//
//  @JsonBackReference
//  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//  private List<Renewal> renewals;
}