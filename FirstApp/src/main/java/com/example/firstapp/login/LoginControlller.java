package com.example.firstapp.login;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("username1")
public class LoginControlller {

    // constructor injection.
    // authenticationService sınıfında herhangi bir attribute kullanmayacagım için
    // constructor injection.
    // Eğer authenticationService.name olsaydı autowired.
    public LoginControlller(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /*
     * 
     * 
     * private Logger logger = LoggerFactory.getLogger(getClass());
     * 
     * @RequestMapping("login")
     * // modal: used for moving from JSP to controller
     * // name1: in the url , name2: to use in page. used in login.jsp
     * public String goToLoginPage(@RequestParam String name1, ModelMap model) {
     * model.put("name2", name1);
     * 
     * // logger.debug("request param is {}", name1);
     * return "login";
     * 
     * }
     */
    private AuthenticationService authenticationService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String goToLoginPage() {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    // parametredekiler login.jsp ile aynı olması lazım.
    public String goToWelcomePage(@RequestParam String username, @RequestParam String password, ModelMap model) {

        if (authenticationService.isValid(username, password)) {

            model.put("username1", username);
            model.put("password1", password);
            return "welcome";
        } else {

            model.put("errorMessage", "Invalid inputs! Please try again");

            return "login";

        }

    }

}
