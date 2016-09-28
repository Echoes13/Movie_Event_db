package wad.controller;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import wad.domain.GroupAccount;
import wad.repository.GroupRepository;

//Kontrolleri joka vie käyttäjän aloitussivulle

@Controller
public class DefaultController {
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private GroupRepository groupRepository;
    
    @RequestMapping("*")
    public String handleDefault() {
        return "redirect:/events";
    }
    
//    Sovelluksen alustamiseen käytetty metodi
//    
//    @PostConstruct
//    public void init() {
//        groupRepository.deleteAll();
//        
//        GroupAccount admin = new GroupAccount();
//        admin.setName("Admin");
//        admin.setUsername("Admin");
//        admin.setPassword(passwordEncoder.encode("sevento22"));
//
//        groupRepository.save(admin);
//        
//        GroupAccount firman = new GroupAccount();
//        firman.setName("FirmÅn");
//        firman.setUsername("tommywiseau22");
//        firman.setPassword(passwordEncoder.encode("doublekimble"));
//
//        groupRepository.save(firman);
//    }
}

