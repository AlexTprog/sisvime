package com.sisvime.app.controller.rest;

import com.sisvime.app.entity.users.User;
import com.sisvime.app.entity.users.dto.UserCreateDto;
import com.sisvime.app.entity.users.dto.UserUpdateDto;
import com.sisvime.app.service.UserService;
import com.sisvime.app.share.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/User")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @GetMapping("/GetAll")
    public ArrayList<User> GetAll() {
        return this.userService.getAll();
    }

    @PostMapping("/Create")
    public User Create(@RequestBody UserCreateDto user) {
        var dto = modelMapper.map(user, User.class);
        return userService.create(dto);
    }

    @GetMapping("/Get/{id}")
    public Optional<User> Get(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PutMapping("/Update")
    public User Update(@RequestBody UserUpdateDto user) {
        var dto = modelMapper.map(user, User.class);
        return userService.update(dto);
    }

    @DeleteMapping("/Delete")
    public void Delete(Long id) {
        userService.deleteById(id);
    }
}
