package bmcsdl_02.bmcsdl.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin( origins = "*" , allowedHeaders = "*")
public class AuthController {

}
