package controllers;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import services.SuggestionService;
import model.CitizenDB;
import model.Comment;
import model.Suggestion;

@Scope("session")
@Controller
public class SuggestionController {
	
	//Descomentar cuando funciones service
	@Autowired
	private SuggestionService suggestionService;
//	@Autowired
//	private CitizenDBService citizenDBService;
//	@Autowired
//	private CommentsService commentsService;
	
	public void setSuggestionService(SuggestionService suggestionService) {
		this.suggestionService = suggestionService;
	}


	private Set<Suggestion> sugerencias = new HashSet<Suggestion>();
	private Set<Comment> comments = new HashSet<Comment>();
	
	
  	@RequestMapping(value="/user/suggestion/makeSuggestion")
    public String makeSuggestion(@RequestParam String titulo, @RequestParam String contenido, HttpSession session){
     
		CitizenDB user = (CitizenDB) session.getAttribute("usuario");
		Suggestion suggestion = new Suggestion((long)user.getSugerencias().size()+1,titulo, user);
		sugerencias.add(suggestion);
		//Esto cuando funcione el service
		//suggestionService.createSuggestion(suggestion);
		//sugerencias = suggestionService.findAll();
		//session.setAttribute("sugerencias", sugerencias);
		
		// AHORA 
		sugerencias = user.getSugerencias();
		session.setAttribute("sugerencias", sugerencias);
		
		return "user/home";
		
		
}
  	
    @RequestMapping(value="/votaPosSuggestion")
    public String votePosSuggestion(@RequestParam String id_sug,HttpSession session){
    	//hasta que no funcionen los servicios lo buscaré a pelo en la lista
    	//de sugerencias que tenemos creada
    	//Suggestion sug = new SuggestionServiceImpl().findById(Long.parseLong(id_sug));
    	Set<Suggestion> aux = (Set<Suggestion>) session.getAttribute("sugerencias");
    	for(Suggestion sug : aux)
    		if(sug.getId() == Long.parseLong(id_sug))
    			sug.setNum_votes(sug.getNum_votes()+1);
    	
    	session.setAttribute("sugerencias", sugerencias);
    	
    	return "user/home";
    }
    
    @RequestMapping(value="/votaNegSuggestion")
    public String voteNegSuggestion(@RequestParam String id_sug,HttpSession session){
    	//hasta que no funcionen los servicios lo buscaré a pelo en la lista
    	//de sugerencias que tenemos creada
    	//Suggestion sug = new SuggestionServiceImpl().findById(Long.parseLong(id_sug));
    	Set<Suggestion> aux = (Set<Suggestion>) session.getAttribute("sugerencias");
    	for(Suggestion sug : aux)
    		if(sug.getId() == Long.parseLong(id_sug))
    			if(sug.getNum_votes() > 0)  //sino nos quedaríamos en negativo en los votos
    				sug.setNum_votes(sug.getNum_votes()-1);
    	
    	session.setAttribute("sugerencias", sugerencias);
    	
    	return "user/home";
    }
    
    //De momento no funciona correctamente
//    @RequestMapping(value="/user/suggestion")
//    public String goMakeSuggestion(@RequestParam String id_sug,HttpSession session){
//    	//de nuevo en este método
//    	//sería lógico buscar la sugerencia
//    	//por id a través de un servicio
//    	//no obstante a falta de funcionamiento de los mismos iré
//    	//buscando las sugerencias en la lista creada 
//    	//en la misma session del usuario
//    	Long id = Long.parseLong(id_sug);
//		//Descomentar cuando solucionemos el error
////    	Suggestion suggestion = new Suggestion();
////    	suggestion = SuggestionService.findById(id);
//		
////    	session.setAttribute("sugerencia", suggestion);
//    	
//    	return "user/suggestion";
//    }

}
