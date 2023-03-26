package project.frmr.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.frmr.entity.User;
import project.frmr.mapper.UserMapper;
import project.frmr.repository.UserRepository;
import project.frmr.rules.UserBusinessRules;
import project.frmr.service.EmailSenderService;
import project.frmr.service.UserService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
/*@AllArgsConstructor*/
public class UserServiceImpl implements UserService {
    PasswordEncoder passwordEncoder;
    private UserMapper userMapper;
    private UserBusinessRules userBusinessRules;

    private EmailSenderService emailSenderService;


    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }



    @Autowired
    private UserRepository userRepository;
    // şuraya private BCryptPasswordEncoder encoder gibi bi şey eklenebilir mi
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User entity) {
        userBusinessRules.checkIfEmailExists(entity.getEmail());
        /*BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = entity.getPassword();
        entity.setPassword(encoder.encode(password));*/
        passwordEncoder= new BCryptPasswordEncoder();
        String encryptedPassword = this.passwordEncoder.encode(entity.getPassword());
        entity.setPassword(encryptedPassword);

        sendEmail(entity);

        return userRepository.save(entity);
    }

    public void sendEmail(User entity) {
        emailSenderService.sendEmail(entity.getEmail(),
                                     entity.getUsername() + " sign up successfully to FRMR system.",
                                     "Confirmation Mail");
    }

    @Override
    public List<User> save(List<User> entities) {
        return (List<User>) userRepository.saveAll(entities);
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        Page<User> entityPage = userRepository.findAll(pageable);
        List<User> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public User update(User entity, String id) {
        Optional<User> optional = findById(id);
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }
}
