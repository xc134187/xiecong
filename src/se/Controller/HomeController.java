package se.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
