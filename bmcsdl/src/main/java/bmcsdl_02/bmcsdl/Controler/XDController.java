package bmcsdl_02.bmcsdl.Controler;

import bmcsdl_02.bmcsdl.Config.JwtServies;
import bmcsdl_02.bmcsdl.Config.UserContext;
import bmcsdl_02.bmcsdl.Entity.RenewRole;
import bmcsdl_02.bmcsdl.Entity.Renewal;
import bmcsdl_02.bmcsdl.Services.XDService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

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
    List<Renewal> listRenewal = xdService.getRenewal(UserContext.getCurrentUser().getUsername(), UserContext.getCurrentUser()
        .getPassword());
    model.addAttribute("listRenewal", listRenewal);
    return "xd-renewal";
  }
}
