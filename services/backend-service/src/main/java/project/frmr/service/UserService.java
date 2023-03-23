package project.frmr.service;

import lombok.AllArgsConstructor;
import project.frmr.entity.User;



public interface UserService extends GenericService<User, String> {
    User findByUsername(String username);

}
