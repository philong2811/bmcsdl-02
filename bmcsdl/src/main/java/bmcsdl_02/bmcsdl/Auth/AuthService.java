package bmcsdl_02.bmcsdl.Auth;

import bmcsdl_02.bmcsdl.Common.RoleEnum;
import bmcsdl_02.bmcsdl.Config.JwtServies;
import bmcsdl_02.bmcsdl.DTO.UserRegister;
import bmcsdl_02.bmcsdl.Entity.Users;
import bmcsdl_02.bmcsdl.Repository.UserRepository;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final JwtServies jwtServies;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  public AuthRes userRegister(UserRegister request){
    var setRole = RoleEnum.USER;
    if(request.getRole() != null || request.getRole().trim().isEmpty())
      setRole = RoleEnum.valueOf(request.getRole().toUpperCase());
    var user = Users.builder()
        .username(request.getUsername())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(setRole)
        .build();
    userRepository.save(user);
    userRepository.createUserSystem(request.getUsername(), request.getPassword());
    var jwtToken = jwtServies.generateToken(user);
    return AuthRes.builder().token(jwtToken).build();
  }

  public AuthRes authenticate(AuthReq request){
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()
        )
    );
    var user = userRepository.findByUsername(request.getUsername()).orElseThrow();
    var jwtToken = jwtServies.generateToken(user);
    return AuthRes.builder().token(jwtToken).build();
  }

  public Boolean check(AuthReq request){
    try{
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              request.getUsername(),
              request.getPassword()
          )
      );
      return true;
    }catch(Exception e){
      return false;
    }
  }
}
