package bmcsdl_02.bmcsdl.Auth;

import bmcsdl_02.bmcsdl.Common.RoleEnum;
import bmcsdl_02.bmcsdl.Config.DataSource;
import bmcsdl_02.bmcsdl.Config.JwtServies;
import bmcsdl_02.bmcsdl.Config.UserContext;
import bmcsdl_02.bmcsdl.DTO.UserRegister;
import bmcsdl_02.bmcsdl.Entity.Users;
import bmcsdl_02.bmcsdl.Repository.UserRepository;
import bmcsdl_02.bmcsdl.Services.UserService;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@CrossOrigin( origins = "*" , allowedHeaders = "*")
public class AuthController {
//  public String getToken(){
//    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
//        .getRequest()
//        .getHeader("Authorization")
//        .replace("Bearer ","");
//  }

  @Autowired
  UserService userService;

  @Autowired
  JwtServies jwtServies;
  private final AuthService authService;

  @GetMapping({ "/", "/login"})
  public String loginForm() {
    return "login";
  }

  @PostMapping("/login")
  public String login(@RequestParam String username, @RequestParam String password,
      Model model){
    var request = AuthReq.builder()
        .username(username)
        .password(password)
        .build();
    var token = authService.authenticate(request);
    if(token == null || token.toString().isEmpty()){
      return "login";
    }

    Optional<Users> userDetail = userService.getUser(jwtServies.extractUserName(token.getToken()));
    if (userDetail.isEmpty()) {
      model.addAttribute("error", "Không tìm thấy thông tin người dùng.");
      return "login";
    }
    var role = userDetail.get().getRole();
    model.addAttribute("token", token.getToken());
    model.addAttribute("role", role.name());

    UserContext user = new UserContext();
    user.setCurrentUser(request.getUsername(), request.getPassword());

    return "login-success-handler";
  }

//  @PostMapping("/login-API")
//  public ResponseEntity<AuthRes> authenticate(@RequestParam String username, @RequestParam String password){
//    AuthReq request = AuthReq.builder()
//        .username(username)
//        .password(password)
//        .build();
//    var check = authService.authenticate(request);
//    if(check == null){
//      return ResponseEntity.status(403).body(check);
//    }
//    UserContext user = new UserContext();
//    user.setCurrentUser(request.getUsername(), request.getPassword());
//    return ResponseEntity.ok(authService.authenticate(request));
//  }
//
//  @PostMapping("/register")
//  public ResponseEntity<AuthRes> register(@RequestBody UserRegister request){
//    try {
//      if(userService.getUser(request.getUsername()).isEmpty()) {
//        request.setUsername(request.getUsername());
//        UserContext.setCurrentUser(request.getUsername(), request.getPassword());
//        return ResponseEntity.ok(authService.userRegister(request));
//      }
//    } catch (Exception e){
//      return ResponseEntity.status(400).body(AuthRes.builder().token(e.getMessage()).build());
//    }
//    return null;
//  }
//  @GetMapping("/user-info")
//  public ResponseEntity<Optional<Users>> getUserInfo(){
//    return ResponseEntity.ok(userService.getUser(jwtServies.extractUserName(getToken())));
//  }
//
//  @PostMapping("/check")
//  public ResponseEntity<Boolean> check(@RequestBody AuthReq request){
//    return ResponseEntity.status(200).body(authService.check(request));
//  }

}