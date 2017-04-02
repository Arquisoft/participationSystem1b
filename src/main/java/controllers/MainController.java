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
import model.Censura;
import model.CitizenDB;
import model.Suggestion;

import org.springframework.web.bind.annotation.RequestParam;
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
	
	//aunque lo suyo sería buscar todas las sugerencias desde el servicio de momento
	//falla, con lo que voy a crear a pelo una lista de sugerencias e insertar en ellas para
	//ir probando
	//private List<Suggestion> sugerencias = //new SuggestionServiceImpl().findAll();
	
	
	private List<Suggestion> sugerencias = new ArrayList<Suggestion>();
	
    @RequestMapping(value="/")
    public String landing(String userName,String password,HttpSession session) {
    	//crearUsuario();  da un error (Oliver), hay q revisarlo
    	
    	ciudadano.setPassword("password");
    	
    	sugerencias.add(new Suggestion(1,"Sugerencia1",
    			new CitizenDB("nombre", "apellidos", "nombre@gmail.com", Calendar.getInstance().getTime(), "direccion", "nacionalidad", "12345678F", "PARTICIPANT")));
    	
    	sugerencias.add(new Suggestion(2,"Sugerencia2",
    			new CitizenDB("nombre2", "apellidos", "nombre@gmail.com", Calendar.getInstance().getTime(), "direccion", "nacionalidad", "12345622", "PARTICIPANT")));
    	
    	session.setAttribute("sugerencias", this.sugerencias);
    	
        return "index";
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
    public String getLogin(@RequestParam String userName, @RequestParam String password, HttpSession session){
    //CitizenDB user =  new CitizenDBServiceImpl().getByLogin(userName);

    if(this.ciudadano != null){
         if(ciudadano.getPassword().compareTo(password)== 0){
        	 
        	 //si string es admin, sino cambiarlo 
        	 if(ciudadano.getType().compareTo("admin") == 0)
               return "admin/home";
        	 
        	 //habría que revisar los tipos
         	 if(ciudadano.getType().compareTo("PARTICIPANT") == 0)
         		 return "user/home";
         }
       }
        return "login";
    }
    
   
    @RequestMapping(value="/user/suggestion")
    public String makeSuggestion(String id_sug,HttpSession session){
    	//de nuevo en este método
    	//sería lógico buscar la sugerencia
    	//por id a través de un servicio
    	//no obstante a falta de funcionamiento de los mismos iré
    	//buscando las sugerencias en la lista creada 
    	//en la misma session del usuario
    	
    	
    	
    	return "user/suggestion";
    }
    
    
    @RequestMapping(value="/admin/home")
    public String adminHome(Model model){
    	return "admin/home";
    }
    
    
    @RequestMapping(value="/admin/edit")
    public String adminEdit(String id_sug,HttpSession session){
    	if(id_sug != null){
    		List<Suggestion> aux = (List<Suggestion>)session.getAttribute("sugerencias");
        	for(Suggestion sug : aux)
        		if(sug.getId() == Long.parseLong(id_sug))
        			Censura.censurar(sug);
        	session.setAttribute("sugerencias", sugerencias);
    	}
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
    			if(sug.getNum_votes() > 0)  //sino nos quedaríamos en negativo en los votos
    				sug.setNum_votes(sug.getNum_votes()-1);
    	
    	session.setAttribute("sugerencias", sugerencias);
    	
    	return "user/home";
    }
    
    @RequestMapping(value="/borrar")
    public String borrar(String id_sug,HttpSession session){
    	List<Suggestion> aux = (List<Suggestion>)session.getAttribute("sugerencias");
    	for(Suggestion sug : aux)
    		if(sug.getId() == Long.parseLong(id_sug))
    			this.sugerencias.remove(sug);
    	
    	session.setAttribute("sugerencias", sugerencias);
    	return "admin/home";
    }
    
    
    @RequestMapping(value="user/comment")
    public String comment(String id_sug,String comment,HttpSession session){
    	List<Suggestion> aux = (List<Suggestion>)session.getAttribute("sugerencias");
    	for(int i=0;i < aux.size();i++)
    		if(aux.get(i).getId() == Long.parseLong(id_sug));
    			//aux.get(i).addComment(comment);  // igual hay q hacer un método addComment o similar
    			//en las sugerencias
    	
    			
    			
    	//una vez que acabamos guardamos en session los cambios en las sugerencias...		
    	session.setAttribute("sugerencias", sugerencias);
    	return "user/comment";
    }
}