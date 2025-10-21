package bmcsdl_02.bmcsdl.Controler;

import bmcsdl_02.bmcsdl.Config.JwtServies;
import bmcsdl_02.bmcsdl.Config.UserContext;
import bmcsdl_02.bmcsdl.DTO.RenewalDTO;
import bmcsdl_02.bmcsdl.Entity.Passport;
import bmcsdl_02.bmcsdl.Entity.RenewRole;
import bmcsdl_02.bmcsdl.Entity.Renewal;
import bmcsdl_02.bmcsdl.Services.XDService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/xd")
@RequiredArgsConstructor
@CrossOrigin( origins = "*" , allowedHeaders = "*")
public class XDController {

  @Autowired
  XDService xdService;

  @Autowired
  JwtServies jwtServies;

  @RequestMapping("/rules")
  public String XDRules(Model model){
    List<RenewRole> rules = xdService.getRenewRole(UserContext.getCurrentUser().getUsername(), UserContext.getCurrentUser()
        .getPassword());
    model.addAttribute("rules", rules);
    return "xd-rules";
  }

  @RequestMapping({"/renewal", "/home"})
  public String XDRenewal(Model model){
    List<RenewalDTO> listRenewal = xdService.getRenewal(UserContext.getCurrentUser().getUsername(), UserContext.getCurrentUser()
        .getPassword());
    model.addAttribute("listRenewal", listRenewal);
    return "xd-renewal";
  }

  @RequestMapping("/verify")
  public String xtVerify(@RequestParam("cmnd") String cmnd){
    int check = 0;
    check = xdService.verify(UserContext.getCurrentUser().getUsername(), UserContext.getCurrentUser()
        .getPassword(), cmnd);
    if(check > 0)
      return "redirect:renewal";
    else
      System.out.println(check);
    return null;
  }

  @RequestMapping("/cancel")
  public String xtCancelRenew(@RequestParam("cmnd") String cmnd, @RequestParam("descriptions") String descriptions){
    int check = 0;
    check = xdService.cancel(UserContext.getCurrentUser().getUsername(), UserContext.getCurrentUser()
        .getPassword(), cmnd, descriptions);
    if(check > 0)
      return "redirect:renewal";
    else
      System.out.println(check);
    return null;
  }

  @RequestMapping("/passport")
  public String passportPage(Model model){
    List<Passport> listPassport = xdService.getPassport(UserContext.getCurrentUser().getUsername(), UserContext.getCurrentUser()
        .getPassword());
    model.addAttribute("listPassport", listPassport);
    return "xd-passport";
  }
}
