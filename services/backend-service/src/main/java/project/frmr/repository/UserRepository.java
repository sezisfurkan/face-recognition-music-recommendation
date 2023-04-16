package project.frmr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.frmr.entity.User;

public interface UserRepository extends JpaRepository<User,String> {
    public User findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    public User findByUsernameAndPassword(String username, String password);
}
