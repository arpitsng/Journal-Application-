package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    public UserService userService;

    @GetMapping("/health-check")
    public String healthcheck(){
        return "ok";
    }


    @PostMapping("/create")
    public boolean createUser(@RequestBody User user){
        userService.saveEntry(user);
        return true;
    }
}
