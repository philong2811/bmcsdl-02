package bmcsdl_02.bmcsdl.Controler;

import bmcsdl_02.bmcsdl.Config.JwtServies;
import bmcsdl_02.bmcsdl.Config.UserContext;
import bmcsdl_02.bmcsdl.DTO.ResDTO;
import bmcsdl_02.bmcsdl.Entity.Users;
import bmcsdl_02.bmcsdl.Services.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
@RequestMapping("/user")
@CrossOrigin( origins = "*" , allowedHeaders = "*")
public class APIUserController {
  @Autowired
  UserService userService;

  @Autowired
  JwtServies jwtServies;


  public String getToken(){
    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
        .getRequest()
        .getHeader("Authorization")
        .replace("Bearer ","");
  }

  @RequestMapping("/user-detail")
  public ResDTO<Users> getUserDetail(){
    Optional<Users> userDetail = userService.getUser(jwtServies.extractUserName(getToken()));
    return new ResDTO<>(userDetail.get(), "Success", "ok");
  }
  @RequestMapping("/test-connect")
  public List<String> testConnect(){
    return userService.testConnect(
        UserContext.getCurrentUser().getUsername(), UserContext.getCurrentUser().getPassword());
  }

}
