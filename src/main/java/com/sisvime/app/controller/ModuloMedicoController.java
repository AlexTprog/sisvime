package com.sisvime.app.controller;

import com.sisvime.app.models.Service.IPersonaService;
import com.sisvime.app.models.Service.Imp.HospitalizacionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/views/ModuloMedico")
public class ModuloMedicoController {
    @Autowired
    protected ModelMapper modelMapper;
    @Autowired
    private HospitalizacionService hospitalizacionService;

    @GetMapping(value = "/modulo/{id}")
    public String OrdenesMed(@PathVariable(value = "id") int idPaciente, Model model) {

        var hospt = hospitalizacionService.getById(idPaciente);
        var edadPac = hospt.Paciente.calculaEdad();
        model.addAttribute("hospitalizacion",hospt);
        model.addAttribute("PacEdad",edadPac);
        return "/views/hospitalizacion/ModuloMedico";
    }
}
