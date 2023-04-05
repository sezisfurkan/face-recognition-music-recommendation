package project.frmr.auth;


import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import project.frmr.entity.User;
import project.frmr.exceptions.ApiError;
import project.frmr.repository.UserRepository;
import project.frmr.responses.Views;

import java.util.Base64;

@RestController
public class AuthController {
    @Autowired
    UserRepository userRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/auth")
    @JsonView(Views.Base.class)
    ResponseEntity<?> handleAuthentication(@RequestHeader(name="Authorization", required = false) String authorization){
        if(authorization==null){
            ApiError error = new ApiError(401,"Unauthorized request","/auth");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);

        }
        String base64encoded = authorization.split("Basic ")[1]; //dXNlcjI6cGFzc3dvcmQ=
        String decoded = new String(Base64.getDecoder().decode(base64encoded)); // user2:password
        String[] parts = decoded.split(":"); // [user2:password]
        String username= parts[0]; // user2
        String password= parts[1]; // password
        User inDB = userRepository.findByUsername(username);

        if(username==null || password==null){
            ApiError error = new ApiError(401,"Unauthorized request","/auth");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);}


        if(inDB==null){
            ApiError error = new ApiError(401,"Unauthorized request","/auth");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
        String hashedPassword = inDB.getPassword();
        if(!passwordEncoder.matches(password,hashedPassword)){
            ApiError error = new ApiError(401,"Unauthorized request","/auth");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
  /*      // username, displayName , image
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("username",inDB.getUserName());
        responseBody.put("displayName",inDB.getDisplayName());
        responseBody.put("image",inDB.getImage());
*/
        return ResponseEntity.ok(inDB);
    }

/*    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ApiError handleBadCredentialsException{

    }*/
}




