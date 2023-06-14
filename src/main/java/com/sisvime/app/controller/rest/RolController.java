package com.sisvime.app.controller.rest;

import com.sisvime.app.entity.roles.Rol;
import com.sisvime.app.entity.roles.dto.RoleCreateDto;
import com.sisvime.app.service.RolService;
import com.sisvime.app.share.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/rol")
public class RolController extends BaseController {
    @Autowired
    private RolService rolService;

    @GetMapping
    public ArrayList<Rol> GetAll() {
        return rolService.getAll();
    }

    @PostMapping
    public Rol Create(RoleCreateDto rol) {
        var dto = modelMapper.map(rol, Rol.class);
        return rolService.create(dto);
    }

    @GetMapping(value = "/{id}")
    public Optional<Rol> Get(@PathVariable Long id) {
        return rolService.getById(id);
    }
}
