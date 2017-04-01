package controllers;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import model.CitizenDB;
import services.impl.CitizenDBServiceImpl;



@Controller
@RequestMapping("*")
public class MainController {

	
    @RequestMapping(value="/")
    public String landing(Model model) {
        return "login";
    }
    
    
    
    @RequestMapping(value="/user/home")
    public String logHtml(Model model){
    	return "user/home";
    }
    
    
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String getLogin(@RequestParam String login, @RequestParam String password, Model mod){
    CitizenDB user =  new CitizenDBServiceImpl().getCitizenDB(login);

    if(user != null){
         if(DigestUtils.sha512Hex(password).equals(user.getPassword()))
               return "user/home";
       }
        return "login";
    }
    
    
    @RequestMapping(value="/user/suggestion")
    public String makeSuggestion(Model model){
    	return "user/suggestion";
    }
    
    
    @RequestMapping(value="/Admin/home")
    public String adminHome(Model model){
    	return "Admin/home";
    }
    
    
    @RequestMapping(value="/Admin/edit")
    public String adminEdit(Model model){
    	return "Admin/edit";
    }
}