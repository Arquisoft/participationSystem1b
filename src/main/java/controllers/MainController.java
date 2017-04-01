package controllers;



import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.codec.digest.DigestUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import factory.Services;
import model.CitizenDB;
import org.springframework.web.bind.annotation.RequestParam;
import services.impl.CitizenDBServiceImpl;
@Controller
@RequestMapping("*")
public class MainController {
	

	private CitizenDB citizen;
	
	
    @RequestMapping(value="/")
    public String landing(Model model) {
    	//crearUsuario();
        return "login";
    }
    
    private void crearUsuario() {
    	CitizenDB citizenDB = new CitizenDB("nombre", "apellidos", "nombre@gmail.com", Calendar.getInstance().getTime(), "direccion", "nacionalidad", "12345678F", "PARTICIPANT");
    	Services.getCitizenDBService().createCitizenDB(citizenDB);
    	CitizenDB citizenDB2 = null;
    	citizenDB2 = Services.getCitizenDBService().getCitizenDB( "nombre@gmail.com");
		
	}
    
    
    @RequestMapping(value="/user/home")
    public String logHtml(Model model){
    	return "user/home";
    }
    
    
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String getLogin(@RequestParam String login, @RequestParam String password, Model mod){
    CitizenDB user =  new CitizenDBServiceImpl().getByLogin(login);

    if(user != null){
         if(DigestUtils.sha512Hex(password).equals(user.getPassword()))
        	 if(user.getType().compareTo("admin") == 0)
               return "admin/home";
         	 if(user.getType().compareTo("user") == 0)
         		 return "user/home";
       }
        return "login";
    }
    
    
    @RequestMapping(value="/user/suggestion")
    public String makeSuggestion(Model model){
    	//cuando tengamos log en BD sería interesante comprobar si el usuario (citizen != null)
    	//lo que implicaría que hemos pasado por la página de login
    	//sino cualquiera llegaría a este punto escribiendo la ruta en el navegador
    	
    	
    	return "user/home";
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