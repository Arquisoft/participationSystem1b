package hello.controllers;


import hello.Message;
import hello.producers.KafkaProducer;
import model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Scope("session")
public class UserController {
	
    @Autowired
    private KafkaProducer kafkaProducer;
    Participant user;

    //@RequestMapping("/")
    //public String landing(Model model) {
    //    model.addAttribute("message", new Message());
    //    return "index";
    //}
    
    //@RequestMapping("/send")
    //public String send(Model model, @ModelAttribute Message message) {
    //    kafkaProducer.send("exampleTopic", message.getMessage());
    //    return "redirect:/";
    //}

    public String getLogin(@RequestParam String login, @RequestParam String password, Model mod){

        //Participant user = participantService.getParticipant(login);

        if(user != null){
            if(DigestUtils.sha512Hex(password).equals(user.getPassword()))
                return "user/index";
        }
        return "login";

    }
}