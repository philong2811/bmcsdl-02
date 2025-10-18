package bmcsdl_02.bmcsdl.Controler;

import bmcsdl_02.bmcsdl.Config.JwtServies;
import bmcsdl_02.bmcsdl.Config.UserContext;
import bmcsdl_02.bmcsdl.Entity.Resident;
import bmcsdl_02.bmcsdl.Entity.Users;
import bmcsdl_02.bmcsdl.Services.UserService;
import bmcsdl_02.bmcsdl.Services.XTService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
@RequestMapping("/xt")
@RequiredArgsConstructor
@CrossOrigin( origins = "*" , allowedHeaders = "*")
public class XTController {

  @Autowired
  XTService xtService;
  @Autowired
  JwtServies jwtServies;

  public String getToken(){
    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
        .getRequest()
        .getHeader("Authorization")
        .replace("Bearer ","");
  }

  @RequestMapping("/test")
  public ResponseEntity<List<Resident>> getResident(){
    return ResponseEntity.ok(xtService.getResident(UserContext.getCurrentUser().getUsername(), UserContext.getCurrentUser()
        .getPassword()));
  }

  @GetMapping("/hello")
  public String hello(){
    return "hello";
  }

  @GetMapping("/home")
  public String xtHome(){
    System.out.println("User is: " + SecurityContextHolder.getContext().getAuthentication().getName());
    System.out.println("Authorities: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
    return "xt";
  }
}
