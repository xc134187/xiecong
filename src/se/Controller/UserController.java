/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/12
 */
package se.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import se.Model.User;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class UserController {

    /**
     * post back page for login form
     *
     * @param username username
     * @param pwd      password
     * @return FormMain if success, Login if false
     */
    @RequestMapping(value = "/User/Login", method = RequestMethod.POST)
    public String Login(@RequestParam String username, @RequestParam String pwd, HttpSession session) {
        User u = new User();
        boolean success = u.Login();

        if (success) {
            session.setAttribute("user", u);
            return "redirect:/FormMain";
        } else {
            return "redirect:/Login";
        }
    }

    /**
     * check if userid and email addr matched
     *
     * @param userId   userid
     * @param mailAddr email address
     * @return JSON string
     */
    @RequestMapping(value = "/User/MailCheck", method = RequestMethod.GET)
    public String MailCheck(@RequestParam String userId, @RequestParam String mailAddr) {
        //todo check userId is match mailAddr
        return null;
    }

    /**
     * a page that allow user to reset pwd
     *
     * @param UserId   userid
     * @param response reponse obj
     * @return page"resetPassword"
     */
    @RequestMapping(value = "/User/ResetPassword", method = RequestMethod.GET)
    public String ResetPassword(@RequestParam String UserId, HttpServletResponse response) {
        try {
            response.getWriter().write("<script>alert('" + UserId + "')</script>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }
}
