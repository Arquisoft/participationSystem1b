package controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import model.CitizenDB;

@Controller
@RequestMapping("*")
public class MainController {

    @RequestMapping(value="/")
    public String landing(Model model) {
        return "login";
    }
}