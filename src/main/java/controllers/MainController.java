package controllers;



import java.util.ArrayList;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.apache.commons.codec.digest.DigestUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import factory.Services;
import model.Censura;
import model.CitizenDB;
import model.Comment;
import model.Suggestion;

import org.springframework.web.bind.annotation.RequestParam;

import services.CitizenDBService;
import services.CommentsService;
import services.SuggestionService;
import services.impl.CitizenDBServiceImpl;
import services.impl.SuggestionServiceImpl;


@Scope("session")
@Controller
//@RequestMapping("*")
public class MainController {
	
	//Una vez que tengamos funcionando el login y los servicios de manera correcta
	//inicializaremos el usuario a null y lo cargaremos en el método de login
	//buscando al user en la BD
	//private CitizenDB ciudadabo = null
	
	private CitizenDB ciudadano =
			new CitizenDB("nombre2", "apellidos2", "nombre2@gmail.com", Calendar.getInstance().getTime(), "direccion", "nacionalidad", "71640211H", "PARTICIPANT");
	private CitizenDB administrador =
			new CitizenDB("admin", "apellidos2", "admin@gmail.com", Calendar.getInstance().getTime(), "direccion", "nacionalidad", "71640211H", "ADMIN");
	
	
	//Descomentar cuando funciones service
//	@Autowired
//	private SuggestionService suggestionService;
//	@Autowired
//	private CitizenDBService citizenDBService;
//	@Autowired
//	private CommentsService commentsService;
//	
	
	
	//aunque lo suyo sería buscar todas las sugerencias desde el servicio de momento
	//falla, con lo que voy a crear a pelo una lista de sugerencias e insertar en ellas para
	//ir probando
	//private List<Suggestion> sugerencias = //new SuggestionServiceImpl().findAll();
	
	
	private Set<Suggestion> sugerencias = new HashSet<Suggestion>();
	private Set<Comment> comments = new HashSet<Comment>();
	
    @RequestMapping(value="/")
    public String landing(HttpSession session, Model model) {
    	//crearUsuario();  da un error (Oliver), hay q revisarlo
    	
    	ciudadano.setPassword("password");
    	administrador.setPassword("password");
    	
    	Suggestion suggestion = new Suggestion((long)1,"Sugerencia1",ciudadano);
    	
    	suggestion = new Suggestion((long)2,"Sugerencia2",ciudadano);
    	sugerencias = ciudadano.getSugerencias();
    	session.setAttribute("sugerencias", this.sugerencias);
    	
        return "index2";
       }
   
   /* 
    @RequestMapping(value="/login")
    public String log(Model model){
    	return "login";
    }
    */
    
    
    /**
     * 
     * Hay que revisar este método. No crea el usuario, da un error
     * de NullPointerException a la hora de invocar el servicio,
     * de hecho habría que revisar las implementaciones de los servicios
     * ya que las que he probado fallan a la hora de la invocación con
     * fallos de NullPointerException.
     */
    /*
    private void crearUsuario() {
    	CitizenDB citizenDB = new CitizenDB("nombre", "apellidos", "nombre@gmail.com", Calendar.getInstance().getTime(), "direccion", "nacionalidad", "12345678F", "PARTICIPANT");
    	Services.getCitizenDBService().createCitizenDB(citizenDB);
    	CitizenDB citizenDB2 = null;
    	citizenDB2 = Services.getCitizenDBService().getCitizenDB( "nombre@gmail.com");	
	}
    */
    
    @RequestMapping(value="/user/home")
    public String logHtml(Model model){
    	return "user/home";
    }
    
    
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String getLogin(@RequestParam String email, @RequestParam String password, HttpSession session, Model model){
    //CitizenDB user = services.getByLogin(email);
   
    	//Esto se deberia de quitar cuando funcione el services y se cambiaria por algo parecido a lo de arriba
    CitizenDB user = new CitizenDB("nombre", "apellidos", email, Calendar.getInstance().getTime(), "direccion", "naciolidad", "12345678D", "PARTICIPANT");
    user.setPassword(password);
    
    // Esto ahora para hacer preubas

    if(user.getPassword().equals(ciudadano.getPassword()) && user.getMail().equals(ciudadano.getMail())){
    	session.setAttribute("usuario", ciudadano);
    	return "user/home";
    }
    if(user.getPassword().equals(administrador.getPassword()) && user.getMail().equals(administrador.getMail())){
    	session.setAttribute("administrador", this.administrador);
    	return "admin/home";
    }
    return "error";
    
    /* Esto cuando este bien lo de servicio  
    if(user == null)
    	return "error"; //porque quiere decir que no existe este usuario
    session.setAttribute("usuario", user);
    List<Suggestion> sugerencias = suggestionService.findAll();
	model.addAttribute("sugerencias", sugerencias);
    if(user.getType().equals("PARTICIPANT")){
    	return "user/home";
    }
    else if(user.getType().equals("ADMIN")){
    	return "Admin/home";
    }
    */
    }
    
 
    
    
    @RequestMapping(value="/admin/home")
    public String adminHome(Model model){
    	return "admin/home";
    }
    
    
    @RequestMapping(value="/admin/edit")
    public String adminEdit(String id_sug,HttpSession session){
    	if(id_sug != null){
    		Set<Suggestion> aux = (Set<Suggestion>) session.getAttribute("sugerencias");
        	for(Suggestion sug : aux)
        		if(sug.getId() == Long.parseLong(id_sug))
        			Censura.censurar(sug);
        	session.setAttribute("sugerencias", sugerencias);
    	}
    	return "admin/edit";
    }
    

    
   
    
    @RequestMapping(value="/borrar")
    public String borrar(String id_sug,HttpSession session){
    	Set<Suggestion> aux = (Set<Suggestion>) session.getAttribute("sugerencias");
    	for(Suggestion sug : aux)
    		if(sug.getId() == Long.parseLong(id_sug))
    			this.sugerencias.remove(sug);
    	
    	session.setAttribute("sugerencias", sugerencias);
    	return "admin/home";
    }
    
    
    
  
    	
    	
    	@RequestMapping(value="/cerrarSesion")
        public String logOut( HttpSession session){
         
    		session.setAttribute("usuario", null);
    		
    		return "/index2";
    	}
}