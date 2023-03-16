package project.frmr.controllers;


import org.springframework.web.bind.annotation.*;
import project.frmr.entities.User;
import project.frmr.repos.UserRepository;
import project.frmr.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    UserRepository userRepository;
    UserService userService;

    public UserController(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }
}
/*
    @GetMapping("/{username}")
    public User getOneUser(@PathVariable String username){
        return userRepository.save(username);

    }
    @PutMapping("/{userId}")
    public User updateOne(@PathVariable Long userId, @RequestBody User newUser){
        return userRepository.save(newUser);
    }

    @DeleteMapping( "/{userId}")
    public void deleteOneUser(@PathVariable Long userId){
        userRepository.deleteById(userId);
    }
*/
