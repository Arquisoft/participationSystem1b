package controllers;



import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import model.CitizenDB;
import model.Comment;
import model.Suggestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import services.CitizenDBService;
import services.CommentsService;
import services.SuggestionService;
import services.VoteCommentService;
import services.VoteSuggestionService;


@Scope("session")
@Controller
//@RequestMapping("*")
public class MainController {
	
	//Una vez que tengamos funcionando el login y los servicios de manera correcta
	//inicializaremos el usuario a null y lo cargaremos en el método de login
	//buscando al user en la BD
	//private CitizenDB ciudadabo = null
	
	private CitizenDB ciudadano =
			new CitizenDB("nombre2", "apellidos2", "nombre2@gmail.com", Calendar.getInstance().getTime(), "direccion", "nacionalidad", "71640217H", "PARTICIPANT");
	private CitizenDB administrador =
			new CitizenDB("admin", "apellidos2", "admin@gmail.com", Calendar.getInstance().getTime(), "direccion", "nacionalidad", "71640219", "ADMIN");
	
	
	//Descomentar cuando funciones service
//	@Autowired
//	private SuggestionService suggestionService;
//	@Autowired
//	private CitizenDBService citizenDBService;
//	@Autowired
//	private CommentsService commentsService;

	private CitizenDBService citizenService;
	private CommentsService commentService;
	private SuggestionService suggestionService;
	private VoteCommentService vCommentService;
	private VoteSuggestionService vSuggestionService;
	
	
	
	//aunque lo suyo sería buscar todas las sugerencias desde el servicio de momento
	//falla, con lo que voy a crear a pelo una lista de sugerencias e insertar en ellas para
	//ir probando
	//private List<Suggestion> sugerencias = //new SuggestionServiceImpl().findAll();
	
	
	private Set<Suggestion> sugerencias = new HashSet<Suggestion>();
	private Set<Comment> comments = new HashSet<Comment>();
	
//	@Autowired
//	public void setCommentService(CommentsService commentService) {
//		this.commentService = commentService;
//	}
//
//	@Autowired
//	public void setCitizenService(CitizenDBService citizenService) {
//		this.citizenService = citizenService;
//	}
//
//	@Autowired
//	public void setSuggestionService(SuggestionService suggestionService) {
//		this.suggestionService = suggestionService;
//	}
//
//	@Autowired
//	public void setVoteCommentService(VoteCommentService vCommentService) {
//		this.vCommentService = vCommentService;
//	}
//	
//	@Autowired
//	public void setSystemService(VoteSuggestionService vSuggestionService) {
//		this.vSuggestionService = vSuggestionService;
//	}
	
    @RequestMapping(value="/")
    public String landing(HttpSession session, Model model) {
    	//crearUsuario();  da un error (Oliver), hay q revisarlo
    	
    	ciudadano.setPassword("password");
    	administrador.setPassword("password");
    	
    	Suggestion suggestion = new Suggestion((long)1,"Sugerencia1",ciudadano);
    	sugerencias.add(suggestion);
    	suggestion = new Suggestion((long)2,"Sugerencia2",ciudadano);
    	sugerencias.add(suggestion);
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
    	session.setAttribute("sugerencias", sugerencias);
    	return "user/home";
    }
 
    if(user.getPassword().equals(administrador.getPassword()) && user.getMail().equals(administrador.getMail())){
    	session.setAttribute("administrador", this.administrador);
    	session.setAttribute("sugerencias", sugerencias);
    	return "admin/home";
    }
    return "error";
   
    }
   
    @RequestMapping(value="/user/suggestion")
    public String goMakeSuggestion(String id_sug,HttpSession session){
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
     	Set<Suggestion> aux = (Set<Suggestion>) session.getAttribute("sugerencias");
    	for(Suggestion sug : aux)
    		if(sug.getId() == Long.parseLong(id_sug)){
    			session.setAttribute("sugerencia", sug);
    			session.setAttribute("titulo", sug.getTitle());
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
       	
    
    	@RequestMapping(value="/admin/edit/editSuggestion")
    	public String editSuggestion(@RequestParam String titulo,
    			@RequestParam String contenido,HttpSession session){
    		//ESTO AHORA
    		Suggestion suggestion = (Suggestion) session.getAttribute("sugerencia");
    		Suggestion suggestion2 = suggestion;
    		
    		sugerencias.remove(suggestion);
    		
    		if(!titulo.equals(""))
    			suggestion2.setTitle(titulo);
    		if(!contenido.equals(""))
    		suggestion2.setContent(contenido);
    		
    		sugerencias.add(suggestion2);
    		
    		//Cuando tengamos Service
    		//Suggestion suggestion = (Suggestion) session.getAttribute("sugerencia");
    		//suggestion.setTitle(titulo);
    		//suggestion.setContent(contenido);
    		//suggestionService.update(suggestion);
    		//sugerencias = suggestionService.findAll();
    		
    		session.setAttribute("sugerencia", suggestion2);
    		session.setAttribute("sugerencias", sugerencias);
    		
    		
    		return "admin/home";
    	}
    	
    	

    	@RequestMapping(value="/cerrarSesion")
        public String logOut( HttpSession session){
         
    		session.setAttribute("usuario", null);
    		
    		
    		return "/index2";
    	}
}