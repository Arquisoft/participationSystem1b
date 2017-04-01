package controllers;


import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import factory.Services;
import model.CitizenDB;
import repository.CitizenDBRepository;

@Controller
@RequestMapping("*")
public class MainController {
	

    @RequestMapping(value="/")
    public String landing(Model model) {
    	crearUsuario();
        return "login";
    }
    
    private void crearUsuario() {
//    	CitizenDB citizenDB = new CitizenDB("nombre", "apellidos", "nombre@gmail.com", Calendar.getInstance().getTime(), "direccion", "nacionalidad", "12345678F", "PARTICIPANT");
//    	Services.getCitizenDBService().createCitizenDB(citizenDB);
//    	CitizenDB citizenDB2 = null;
//    	citizenDB2 = Services.getCitizenDBService().getCitizenDB( "nombre@gmail.com");
//		
	}

	@RequestMapping(value="/login", method = RequestMethod.POST)
    public String land(@RequestBody String parametros, Model model) {
        return "login";
    }
}