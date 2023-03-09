package project.frmr.controllers;


import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.frmr.entities.User;

import java.util.logging.Logger;

@RestController
public class UserController {


    @PostMapping("/users")
    public void createUser(@RequestBody User user){

    }
}
