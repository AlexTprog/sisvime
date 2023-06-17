package com.sisvime.app.controller;


import com.sisvime.app.models.Service.IPacienteService;
import com.sisvime.app.models.entity.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/views/paciente")
public class PacienteController {

    @Autowired
    private IPacienteService pacienteservice;

    @GetMapping(value = "/verpa/{id}")
    public String ver(@PathVariable(value = "id") Long idver, Model model) {

        var paciente = pacienteservice.buscarporId(idver);

        model.addAttribute("paciente", paciente);
        model.addAttribute("titulo", "Detalle del Paciente : " + ' ' + paciente.getNombre() + ' ' + paciente.getApellidoma() + ' ' + paciente.getApellido_pa());

        return "/views/paciente/verpac";
    }

    /*------------------------------------------- */
    /* LISTAR los datos del Formulario */

    @GetMapping("/listarpac")
    public String listarPaciente(Model model) {

        List<Paciente> listadopaciente = pacienteservice.listartodos();

        model.addAttribute("titulo", "Listado del Paciente");
        model.addAttribute("pacientes", listadopaciente);
        return "/views/paciente/listarpac";
    }

    @GetMapping(value = "/ver-pacientes", produces = {"application/json"})
    public @ResponseBody List<Paciente> verPacientes() {
        return pacienteservice.listartodos();
    }

    /* Primera fase mostrar formulario al usuario */
    /* CREAR--CREAR los datos del Formulario */

    @GetMapping("/formpac")
    public String crear(Model model) {

        Paciente paciente = new Paciente();

        model.addAttribute("titulo", "Formulario: Nuevo Personal Medico");
        model.addAttribute("paciente", paciente);

        return "/views/paciente/formpac";

    }

    /*EDITAR---EDITAR los datos del Formulario */

    @GetMapping(value = "/editpac/{id}")
    public String editar(@PathVariable(value = "id") Long idpaciente, Model model) {

        Paciente paciente = pacienteservice.buscarporId(idpaciente);


        model.addAttribute("titulo", "Formulario: Editar Personal Medico");
        model.addAttribute("paciente", paciente);

        return "/views/paciente/formpac";

    }

    /*GUARDAR---GUARDAR--los datos del Formulario */
    @PostMapping("/savepac")
    public String guardar(@Valid @ModelAttribute Paciente paciente, BindingResult result, Model model, @RequestParam("file") MultipartFile foto, RedirectAttributes attribute) {

        if (result.hasErrors()) {

            model.addAttribute("titulo", "Formulario: Nuevo Personal Medico");
            model.addAttribute("paciente", paciente);

            return "/views/paciente/formpac";
        }


        if (!foto.isEmpty()) {
            String rutaAbosluta = "D://Users//RICHARD//Documents//workspace-spring-tool-suite-4-4.8.0.RELEASE//spring-boot-data-jpa//imagen//paciente";

            try {
                byte[] bytesImg = foto.getBytes();
                Path rutacompleta = Paths.get(rutaAbosluta + "//" + foto.getOriginalFilename());
                Files.write(rutacompleta, bytesImg);

                paciente.setFoto(foto.getOriginalFilename());

            } catch (IOException e) {

                e.printStackTrace();
            }

        }
        pacienteservice.guardar(paciente);
        attribute.addFlashAttribute("success", "Paciente guardado con exito!");
        return "redirect:/views/paciente/listarpac";
    }

    /* ELIMINAR los datos del Formulario */

    @GetMapping("/deletepac/{id}")
    public String eliminar(@PathVariable("id") Long idpaciente) {

        pacienteservice.eliminar(idpaciente);

        return "redirect:/views/paciente/listarpac";

    }

    @GetMapping(value = "/cargar-paciente/{term}", produces = {"application/json"})
    public @ResponseBody List<Paciente> cargarPaciente(@PathVariable String term) {
        return pacienteservice.findByNombre(term);
    }

}