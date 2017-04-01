package controllers;



import java.util.ArrayList;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.apache.commons.codec.digest.DigestUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import factory.Services;
import model.CitizenDB;
import model.Suggestion;

import org.springframework.web.bind.annotation.RequestParam;
import services.impl.CitizenDBServiceImpl;
import services.impl.SuggestionServiceImpl;


@Scope("session")
@Controller
@RequestMapping("*")
public class MainController {
	

	
	private CitizenDB ciudadano =
			new CitizenDB("nombre2", "apellidos2", "nombre2@gmail.com", Calendar.getInstance().getTime(), "direccion", "nacionalidad", "71640211H", "PARTICIPANT");
	
	//aunque lo suyo sería buscar todas las sugerencias desde el servicio de momento
	//falla, con lo que voy a crear a pelo una lista de sugerencias e insertar en ellas para
	//ir probando
	//private List<Suggestion> sugerencias = //new SuggestionServiceImpl().findAll();
	
	
	private List<Suggestion> sugerencias = new ArrayList<Suggestion>();
	
    @RequestMapping(value="/")
    public String landing(Model model,HttpSession session) {
    	//crearUsuario();  da un error (Oliver), hay q revisarlo
    	
    	sugerencias.add(new Suggestion(1,"Sugerencia1",
    			new CitizenDB("nombre", "apellidos", "nombre@gmail.com", Calendar.getInstance().getTime(), "direccion", "nacionalidad", "12345678F", "PARTICIPANT")));
    	
    	sugerencias.add(new Suggestion(2,"Sugerencia2",
    			new CitizenDB("nombre2", "apellidos", "nombre@gmail.com", Calendar.getInstance().getTime(), "direccion", "nacionalidad", "12345622", "PARTICIPANT")));
    	
    	session.setAttribute("sugerencias", this.sugerencias);
    	
        return "login";
    }
    
    @RequestMapping(value="/login")
    public String log(Model model){
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
    	
    	return "user/suggestion";
    }
    
    
    @RequestMapping(value="/admin/home")
    public String adminHome(Model model){
    	return "admin/home";
    }
    
    
    @RequestMapping(value="/admin/edit")
    public String adminEdit(Model model){
    	return "admin/edit";
    }
    
    @RequestMapping(value="/votaPos")
    public String votePos(String id_sug,HttpSession session){
    	//hasta que no funcionen los servicios lo buscaré a pelo en la lista
    	//de sugerencias que tenemos creada
    	//Suggestion sug = new SuggestionServiceImpl().findById(Long.parseLong(id_sug));
    	List<Suggestion> aux = (List<Suggestion>)session.getAttribute("sugerencias");
    	for(Suggestion sug : aux)
    		if(sug.getId() == Long.parseLong(id_sug))
    			sug.setNum_votes(sug.getNum_votes()+1);
    	
    	session.setAttribute("sugerencias", sugerencias);
    	
    	return "user/home";
    }
    
    @RequestMapping(value="/votaNeg")
    public String voteNeg(String id_sug,HttpSession session){
    	//hasta que no funcionen los servicios lo buscaré a pelo en la lista
    	//de sugerencias que tenemos creada
    	//Suggestion sug = new SuggestionServiceImpl().findById(Long.parseLong(id_sug));
    	List<Suggestion> aux = (List<Suggestion>)session.getAttribute("sugerencias");
    	for(Suggestion sug : aux)
    		if(sug.getId() == Long.parseLong(id_sug))
    			sug.setNum_votes(sug.getNum_votes()-1);
    	
    	session.setAttribute("sugerencias", sugerencias);
    	
    	return "user/home";
    }
}