package com.sisvime.app.controller;

import com.sisvime.app.entity.Brigade;
import com.sisvime.app.entity.Patient;
import com.sisvime.app.service.BridageService;
import com.sisvime.app.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/views/brigada")
public class BrigadeViewController {

    @Autowired
    private BridageService bridageService;

    @Autowired
    private PatientService patientService;

    @GetMapping("/listbrigadamedica")
    public String listarbrigadas(Model model) {

        List<Brigade> listadobrigada = bridageService.getAll();
        model.addAttribute("titulo", "Lista de Brigada");
        model.addAttribute("brigadas", listadobrigada);

        return "/views/brigada/brigmedlist";
    }

    @GetMapping(value = "/brigadamedicalista", produces = {"application/json"})
    public @ResponseBody List<Brigade> listarBrigadas() {
        return bridageService.getAll();
    }

//    @GetMapping(value = "/distritoparentesco/{month}/{year}", produces = {"application/json"})
//    public @ResponseBody List<DistritoParentesco> distritoParentesco(@PathVariable String month, @PathVariable String year) {
//        int number = 0;
//        int numbery = 0;
//        number = Integer.parseInt(month);
//        numbery = Integer.parseInt(year);
//        return bridageService.distritoParentesco(number, numbery);
//    }

//    @GetMapping(value = "/distritoparentescoactual", produces = {"application/json"})
//    public @ResponseBody List<DistritoParentesco> distritoParentescoActual() {
//        return bridageService.distritoParentescoActual();
//    }

//    @GetMapping(value = "/titularactual", produces = {"application/json"})
//    public @ResponseBody List<TitularParentesco> titularParentescoActual() {
//        return bridageService.titularParentescoActual();
//    }

//    @GetMapping(value = "/titularparentesco/{month}/{year}", produces = {"application/json"})
//    public @ResponseBody List<TitularParentesco> titularParentesco(@PathVariable String month, @PathVariable String year) {
//        int number = 0;
//        int numbery = 0;
//        number = Integer.parseInt(month);
//        numbery = Integer.parseInt(year);
//        return bridageService.titularParentesco(number, numbery);
//    }

    @GetMapping(value = "/verbri/{id}")
    public String ver(@PathVariable(value = "id") Long id, Model model) {

        var brigada = bridageService.getById(id);

        model.addAttribute("brigada", brigada);
//        model.addAttribute("titulo", "Detalle de la Visita Medica : "
//                + ' ' + brigada.getTipobrigada() + ' ' + brigada.getZonabrigada());

        return "/views/brigada/verbrig";
    }

    @GetMapping("/createbrigada")
    public String crear(Model model) {

        var brigada = new Brigade();
        var listpaciente = patientService.getAll();

        model.addAttribute("titulo", "Formulario: Nueva Brigada");
        model.addAttribute("brigada", brigada);
        model.addAttribute("paciente", listpaciente);

        return "/views/brigada/brigadamedicaform";
    }


    @PostMapping("/savebrigada")
    public String guardar(@ModelAttribute Brigade brigada, RedirectAttributes attribute, BindingResult result) {

        bridageService.create(brigada);
        attribute.addFlashAttribute("success", "Brigada Guardado con exito");
        System.out.println("Guardado con Exito la Brigada");
        return "redirect:/views/brigada/listbrigadamedica";
    }

    @GetMapping(value = "/editbrigada/{id}")
    public String editar(@PathVariable(value = "id") Long id, Model model) {
        var brigada = bridageService.getById(id);

        model.addAttribute("titulo", "Formulario:Editar Brigada Medica");
        model.addAttribute("brigada", brigada);
        return "/views/brigada/brigadamedicaform";
    }


    @GetMapping("/deletebrigada/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        bridageService.delete(id);
        return "redirect:/views/brigada/brigmedlist";
    }

}
