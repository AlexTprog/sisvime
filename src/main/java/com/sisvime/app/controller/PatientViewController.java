package com.sisvime.app.controller;

import com.sisvime.app.entity.Patient;
import com.sisvime.app.service.PatientService;
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
public class PatientViewController {
    @Autowired
    private PatientService patientService;

    @GetMapping(value = "/verpa/{id}")
    public String ver(@PathVariable(value = "id") Long id, Model model) {

        var dbPatient = patientService.getById(id);

        model.addAttribute("paciente", dbPatient);
        model.addAttribute("titulo", "Detalle del Paciente : "
                + ' ' + dbPatient.getName() + ' ' + dbPatient.getName() + ' '
                + dbPatient.getName());

        return "/views/paciente/verpac";
    }

    @GetMapping("/listarpac")
    public String listarPaciente(Model model) {

        List<Patient> listadopaciente = patientService.getAll();

        model.addAttribute("titulo", "Listado del Paciente");
        model.addAttribute("pacientes", listadopaciente);
        return "/views/paciente/listarpac";
    }

    @GetMapping(value = "/ver-pacientes", produces = {"application/json"})
    public @ResponseBody List<Patient> verPacientes() {
        return patientService.getAll();
    }

    /* Primera fase mostrar formulario al usuario */
    /* CREAR--CREAR los datos del Formulario */

    @GetMapping("/formpac")
    public String crear(Model model) {

        var paciente = new Patient();

        model.addAttribute("titulo", "Formulario: Nuevo Personal Medico");
        model.addAttribute("paciente", paciente);

        return "/views/paciente/formpac";

    }

    /*EDITAR---EDITAR los datos del Formulario */

    @GetMapping(value = "/editpac/{id}")
    public String editar(@PathVariable(value = "id") Long idpaciente, Model model) {

        var paciente = patientService.getById(idpaciente);


        model.addAttribute("titulo", "Formulario: Editar Personal Medico");
        model.addAttribute("paciente", paciente);

        return "/views/paciente/formpac";

    }

    /*GUARDAR---GUARDAR--los datos del Formulario */
    @PostMapping("/savepac")
    public String guardar(@Valid @ModelAttribute Patient paciente, BindingResult result, Model model,
                          @RequestParam("file") MultipartFile foto, RedirectAttributes attribute) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario: Nuevo Personal Medico");
            model.addAttribute("paciente", paciente);

            return "/views/paciente/formpac";
        }


        //TODO Revisar rutas
        if (!foto.isEmpty()) {
            String rutaAbosluta = "D://Users//RICHARD//Documents//workspace-spring-tool-suite-4-4.8.0.RELEASE//spring-boot-data-jpa//imagen//paciente";

            try {
                byte[] bytesImg = foto.getBytes();
                Path rutacompleta = Paths.get(rutaAbosluta + "//" + foto.getOriginalFilename());
                Files.write(rutacompleta, bytesImg);

                paciente.setPhoto(foto.getOriginalFilename());

            } catch (IOException e) {

                e.printStackTrace();
            }

        }
        patientService.create(paciente);
        attribute.addFlashAttribute("success", "Paciente guardado con exito!");
        return "redirect:/views/paciente/listarpac";
    }

    /* ELIMINAR los datos del Formulario */

    @GetMapping("/deletepac/{id}")
    public String eliminar(@PathVariable("id") Long idpaciente) {

        patientService.delete(idpaciente);

        return "redirect:/views/paciente/listarpac";

    }

    @GetMapping(value = "/cargar-paciente/{term}", produces = {"application/json"})
    public @ResponseBody List<Patient> cargarPaciente(@PathVariable String name) {
        return patientService.searchByName(name);
    }
}
