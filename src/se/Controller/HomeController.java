/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/12
 */

package se.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    /**
     * root path
     *
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String Root(Model model) {
        model.addAttribute("Title", "HomePage");
        return "index";
    }

    /**
     * Main Form
     *
     * @param model
     * @return
     */
    @RequestMapping("/FormMain")
    public String FormMain(Model model) {
        model.addAttribute("Title", "FormMain");
        return "index";
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
}
