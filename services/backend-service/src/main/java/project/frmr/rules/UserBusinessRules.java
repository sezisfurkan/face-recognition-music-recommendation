package project.frmr.rules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.frmr.exceptions.BusinessException;
import project.frmr.repository.UserRepository;

@Service
public class UserBusinessRules {

    private UserRepository userRepository;

    public UserBusinessRules(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void checkIfEmailExists(String email) {
        if(userRepository.existsByEmail(email)) {
            throw new BusinessException("Email already exists. Please try to sign in with different email address");
        }
    }

}
