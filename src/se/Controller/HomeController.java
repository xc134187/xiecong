/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/12
 */

package se.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import se.Model.User;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String Root() {
        return "redirect:/Login";
    }

    /**
     * Main Form
     *
     * @param model
     * @return
     */
    @RequestMapping("/FormMain")
    public String FormMain(Model model, HttpSession session) {
        User user = (User)session.getAttribute("user");
        if (user.getRole() == 1){
            model.addAttribute("Title","教师管理页面");
            return "teacher";
        }else if (user.getRole() == 2){
            model.addAttribute("Title", "学生管理页面");
            return "student";
        }
        return null;
    }

    /**
     * Login path
     *
     * @param model
     * @return
     */
    @RequestMapping("/Login")
    public String Login(Model model) {
        model.addAttribute("Title", "Login Page");
        return "login";
    }

    @RequestMapping("/Logout")
    public String Logout(HttpSession session){
        session.invalidate();
        return "redirect:Login";
    }
}
