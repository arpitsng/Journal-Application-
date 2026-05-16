package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    public UserService userService;


    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @PostMapping
    public boolean createUser(@RequestBody User user){
        userService.saveEntry(user);
        return true;
    }

    @PutMapping("/update/{UserName}")
    public ResponseEntity<?> updateUser(@RequestBody User user,
                                        @PathVariable String UserName){

        User userIndb = userService.findByUserName(UserName);

        if(userIndb != null){
            userIndb.setUserName(user.getUserName());
            userIndb.setPassword(user.getPassword());
            userService.saveEntry(userIndb);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}