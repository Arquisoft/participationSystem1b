package hello.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import model.Suggestion;

@Controller
public class SuggestionController {
	
	@GetMapping("/suggestion")
	public String SuggestionForm(Model model){
		model.addAttribute("suggestion",new Suggestion());
		return "suggestion";
	}
	
	@PostMapping("/suggestion")
	public String SuggestionSubmit(@ModelAttribute Suggestion suggestion){
		return "result";
	}
}
