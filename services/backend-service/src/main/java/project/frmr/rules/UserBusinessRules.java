package project.frmr.rules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.frmr.exceptions.BusinessException;
import project.frmr.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserBusinessRules {

    private UserRepository userRepository;

    public void checkIfEmailExists(String email) {
        if(userRepository.existsByEmail(email)) {
            throw new BusinessException("Email already exists. Please try to sign in with different email address");
        }
    }

}
