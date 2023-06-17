package com.sisvime.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/views/horatrabajo")
public class WorkHoursViewController {
//    @Autowired
//    private IHoratrabajoService horatrabajoservice;
//
//    @Autowired
//    private IHoraService horaservice;

    @GetMapping("/listwork")
    public String listarhoratrabajo(Model model) {
//        List<Horatrabajo> listadohoratrabajo = horatrabajoservice.listartodos();
//
//        model.addAttribute("titulo", "Horario de Atención");
//        model.addAttribute("horatrabajos", listadohoratrabajo);
        return "/views/horatrabajo/listawork";
    }

    @GetMapping("/createhoratrabajo")
    public String crear(Model model) {
//        Horatrabajo horatrabajo = new Horatrabajo();
//        List<Hora> listHora = horaservice.listaHora();
//
//        model.addAttribute("horatrabajo", horatrabajo);
//        model.addAttribute("horas", listHora);
        return "/views/horatrabajo/listaworkform";
    }

    @PostMapping("/savehoratrabajo")
    public String guardar(
            /*@ModelAttribute Horatrabajo horatrabajo,*/ RedirectAttributes attribute, BindingResult result) {
//        horatrabajoservice.guardar(horatrabajo);
//        attribute.addFlashAttribute("success", "Horario de Medico Guardado con Exito");
//        System.out.println("Horario de Medico Guardado con Exito");
        return "redirect:/views/horatrabajo/listwork";
    }

    @GetMapping("/deleteHorario/{id}")
    public String eliminar(@PathVariable("id") Integer idhorariotrabajo) {
//        horatrabajoservice.eliminar(idhorariotrabajo);
        return "redirect:/views/horatrabajo/listwork";
    }
}
