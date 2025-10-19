package bmcsdl_02.bmcsdl.Controler;

import bmcsdl_02.bmcsdl.Config.JwtServies;
import bmcsdl_02.bmcsdl.Config.UserContext;
import bmcsdl_02.bmcsdl.Entity.Renewal;
import bmcsdl_02.bmcsdl.Entity.Resident;
import bmcsdl_02.bmcsdl.Services.XTService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
=======
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
>>>>>>> dcbb09afc8152b0040ed818043b67b4c6a60270f

@Controller
@RequestMapping("/xt")
@RequiredArgsConstructor
@CrossOrigin( origins = "*" , allowedHeaders = "*")
public class XTController {

    @Autowired
    XTService xtService;
    @Autowired
    JwtServies jwtServies;

<<<<<<< HEAD
  @GetMapping("/hello")
  public String hello(){
    return "hello";
  }

  @RequestMapping({"/home", "/renewal"})
  public String xtRenewal(Model model){
    List<Renewal> rs = xtService.getRenewal(UserContext.getCurrentUser().getUsername(), UserContext.getCurrentUser()
        .getPassword());

    model.addAttribute("renewals", rs);
    return "xt-renewal";
  }

  @RequestMapping("/resident")
  public String xtResident(Model model){
    List<Resident> rs = xtService.getResident(UserContext.getCurrentUser().getUsername(), UserContext.getCurrentUser()
        .getPassword());

    model.addAttribute("listResident", rs);
    return "xt-resident";
  }

  @RequestMapping("/verify")
  public String xtVerify(@RequestParam("cmnd") String cmnd){
    int check = 0;
    check = xtService.verify(UserContext.getCurrentUser().getUsername(), UserContext.getCurrentUser()
        .getPassword(), cmnd);
    if(check > 0)
      return "redirect:renewal";
    else
      System.out.println(check);
    return null;
  }
=======
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping({"/home", "/renewal"})
    public String xtRenewal(Model model){
        List<Renewal> rs = xtService.getRenewal(UserContext.getCurrentUser().getUsername(), UserContext.getCurrentUser()
                .getPassword());

        model.addAttribute("renewals", rs);
        return "xt-renewal";
    }

    @RequestMapping("/resident")
    public String xtResident(Model model){
        List<Resident> rs = xtService.getResident(UserContext.getCurrentUser().getUsername(), UserContext.getCurrentUser()
                .getPassword());

        model.addAttribute("listResident", rs);
        return "xt-resident";
    }

    @RequestMapping("/verify")
    public String xtVerify(@RequestParam("cmnd") String cmnd){
        int check = 0;
        check = xtService.verify(UserContext.getCurrentUser().getUsername(), UserContext.getCurrentUser()
                .getPassword(), cmnd);
        if(check > 0)
            return "redirect:renewal";
        else
            System.out.println(check);
        return null;
    }
>>>>>>> dcbb09afc8152b0040ed818043b67b4c6a60270f
}
