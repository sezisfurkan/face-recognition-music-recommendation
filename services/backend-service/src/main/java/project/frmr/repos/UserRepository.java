package project.frmr.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import project.frmr.entities.User;

public interface UserRepository extends JpaRepository<User,Long>{


}
