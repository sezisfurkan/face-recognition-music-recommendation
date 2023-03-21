package project.frmr.controller.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.frmr.controller.UserController;
import project.frmr.dto.UserDTO;

import java.util.List;

public class UserControllerImpl implements UserController {
    @Override
    public UserDTO save(UserDTO user) {
        return null;
    }

    @Override
    public UserDTO findById(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<UserDTO> list() {
        return null;
    }

    @Override
    public Page<UserDTO> pageQuery(Pageable pageable) {
        return null;
    }

    @Override
    public UserDTO update(UserDTO dto, String id) {
        return null;
    }
}
