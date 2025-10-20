package bmcsdl_02.bmcsdl.Controler;

import bmcsdl_02.bmcsdl.Config.JwtServies;
import bmcsdl_02.bmcsdl.Config.UserContext;
import bmcsdl_02.bmcsdl.Entity.Renewal;
import bmcsdl_02.bmcsdl.Services.UserService;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin( origins = "*" , allowedHeaders = "*")
public class UserController {
  @Autowired
  UserService userService;

  @Autowired
  JwtServies jwtServies;

  @GetMapping({"/", "/home", "/renewal-submit"})
  public String Userhome(Model model){
    List<Renewal> rs = userService.getRenewal(UserContext.getCurrentUser().getUsername(), UserContext.getCurrentUser()
        .getPassword());

    model.addAttribute("renewals", rs);

    return "renewal-submit";
  }

  @GetMapping("/renewal-form")
  public String submitForm(){
    return "renewal-form";
  }

  @PostMapping("/renewal-form")
  public String submitForm(@RequestParam String cmnd, @RequestParam String district, @RequestParam String descriptions){
    long millis = System.currentTimeMillis();
    Date date = new Date(millis);

    var request = Renewal.builder()
        .district(district)
        .cmnd(cmnd)
        .descriptions(descriptions)
        .create_date(date)
        .status("Đang chờ...")
        .build();
    System.out.println(request);
    int check = 0;
    check = userService.CreateRenewal(UserContext.getCurrentUser().getUsername(), UserContext.getCurrentUser()
        .getPassword(), request);
    if(check > 0)
      return "redirect:renewal-submit";
    else
      System.out.println(check);
    return null;
  }
}
