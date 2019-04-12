package se.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;

@Controller
public class HomeController {
    @RequestMapping("/Home")
    public String Home(Model model){
        return "index";
    }

    @RequestMapping("/")
    public  String Root(Model model){
        model.addAttribute("Title","HomePage");
        return  "index";
    }

    @RequestMapping("/FormMain")
    public String FormMain(Model model){
        model.addAttribute("Title", "FormMain");
        return "index";
    }

    @RequestMapping("/Login")
    public String Login(Model model){
        model.addAttribute("Title", "Login Page");
        return "index";
    }
}
