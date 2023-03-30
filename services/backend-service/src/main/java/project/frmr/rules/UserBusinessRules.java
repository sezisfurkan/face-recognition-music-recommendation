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

    public void checkIfUserExists(String email, String username) {
        if(userRepository.existsByEmail(email) && userRepository.existsByUsername(username)) {
            throw new BusinessException("A user with the same email and username already exists. Please try to sign up with different email address and username.");
        } else if (userRepository.existsByEmail(email)) {
            throw new BusinessException("A user with the same email already exists. Please try to sign up with different email address.");
        } else if (userRepository.existsByUsername(username)) {
            throw new BusinessException("A user with the same username already exists. Please try to sign up with different username.");
        }
    }

}
