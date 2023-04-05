package project.frmr.controller.impl;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.frmr.controller.UserController;
import project.frmr.dto.UserDTO;
import project.frmr.entity.User;
import project.frmr.mapper.UserMapper;
import project.frmr.service.UserService;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/api/v1")
@CrossOrigin
public class UserControllerImpl implements UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Override
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO save(@Valid @RequestBody UserDTO userDTO) {

        User user = userMapper.asEntity(userDTO);
        return userMapper.asDTO(userService.save(user));
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> login(HttpSession session) {
        return new ResponseEntity<String>(session.getId(), null, HttpStatus.OK);
    }

    @Override
    @GetMapping("/user/{id}")
    public UserDTO findById(@PathVariable("id") String id) {

        User user = userService.findById(id).orElse(null);
        return userMapper.asDTO(user);
    }

    @Override
    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable("id") String id) {
        userService.deleteById(id);
    }

    @Override
    @GetMapping("/user")
    public List<UserDTO> list() {
        return userMapper.asDTOList(userService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<UserDTO> pageQuery(Pageable pageable) {
        Page<User> userPage = userService.findAll(pageable);
        List<UserDTO> dtoList = userPage
                .stream()
                .map(userMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, userPage.getTotalElements());
    }

    @Override
    @PutMapping("/user/{id}")
    public UserDTO update(@RequestBody UserDTO userDTO, @PathVariable("id") String id) {
        User user = userMapper.asEntity(userDTO);
        return userMapper.asDTO(userService.update(user, id));
    }
}
