package com.sisvime.app.controller.rest;

import com.sisvime.app.entity.users.User;
import com.sisvime.app.entity.users.dto.UserCreateDto;
import com.sisvime.app.service.UserService;
import com.sisvime.app.share.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ArrayList<User> GetAll() {
        return this.userService.getAll();
    }

    @PostMapping
    public User Create(@RequestBody UserCreateDto user) {
        var dto = modelMapper.map(user, User.class);
        return userService.create(dto);
    }

    @GetMapping(value = "/{id}")
    public Optional<User> Get(@PathVariable Long id) {
        return userService.getById(id);
    }
}
