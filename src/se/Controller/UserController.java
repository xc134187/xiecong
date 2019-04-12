package se.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UserController {

    /**
     * postback page for login form
     * @param username  username
     * @param pwd       password
     * @return FormMain if success, Login if false
     */
    @RequestMapping(value = "/User/Login", method = RequestMethod.POST)
    public String Login(@RequestParam String username, @RequestParam String pwd){
        //Todo connect to database
        boolean success = false;

        if(success){
            return "redirect:/FormMain";
        }else {
            return "redirect:/Login";
        }
    }

    @RequestMapping(value = "/User/MailCheck", method = RequestMethod.GET)
    public String MailCheck(@RequestParam String userId, @RequestParam String mailAddr){
        //todo check userId is match mailAddr
        return null;
    }

    @RequestMapping(value = "/User/ResetPassword", method = RequestMethod.GET)
    public String ResetPassword(@RequestParam String UserId, HttpServletResponse response){
        try {
            response.getWriter().write("<script>alert('"+UserId+"')</script>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }
}
