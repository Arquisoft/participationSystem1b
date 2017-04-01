package controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("*")
public class MainController {

    @RequestMapping(value="*")
    public String landing(Model model) {
        return "login";
    }
    
    @RequestMapping(value="/")
    public String landing2(Model model) {
        return "login";
    }
}