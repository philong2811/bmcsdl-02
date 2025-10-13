package bmcsdl_02.bmcsdl.Controler;

import bmcsdl_02.bmcsdl.Config.JwtServies;
import bmcsdl_02.bmcsdl.DTO.ResDTO;
import bmcsdl_02.bmcsdl.Entity.Users;
import bmcsdl_02.bmcsdl.Services.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
@RequestMapping("/user")
@CrossOrigin( origins = "*" , allowedHeaders = "*")
public class UserController {
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
}
