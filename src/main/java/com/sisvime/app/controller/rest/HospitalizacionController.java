package com.sisvime.app.controller.rest;

import com.sisvime.app.models.Service.Imp.CamaService;
import com.sisvime.app.models.Service.Imp.HospitalizacionService;
import com.sisvime.app.models.entity.Cama;
import com.sisvime.app.models.entity.Hospitalizacion;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/hospitalizacion")
public class HospitalizacionController {
    @Autowired
    private HospitalizacionService hospitalizacionService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<Hospitalizacion> GetAll(){
        return hospitalizacionService.getAll();
    }



}
