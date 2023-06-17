package com.sisvime.app.controller;

import com.sisvime.app.models.Service.IBrigadaService;
import com.sisvime.app.models.Service.IPacienteService;
import com.sisvime.app.models.entity.Brigada;
import com.sisvime.app.models.entity.DistritoParentesco;
import com.sisvime.app.models.entity.Paciente;
import com.sisvime.app.models.entity.TitularParentesco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/views/brigada")
public class BrigadaController {

    @Autowired
    private IBrigadaService brigadaservice;

    @Autowired
    private IPacienteService pacienteservice;

    @GetMapping("/listbrigadamedica")
    public String listarbrigadas(Model model) {

        var listadobrigada = brigadaservice.listartodos();
        model.addAttribute("titulo", "Lista de Brigada");
        model.addAttribute("brigadas", listadobrigada);

        return "/views/brigada/brigmedlist";
    }

    @GetMapping(value = "/brigadamedicalista", produces = {"application/json"})
    public @ResponseBody List<Brigada> listarBrigadas() {
        return brigadaservice.listartodos();
    }

    @GetMapping(value = "/distritoparentesco/{month}/{year}", produces = {"application/json"})
    public @ResponseBody List<DistritoParentesco> distritoParentesco(@PathVariable String month, @PathVariable String year) {
        int number = 0;
        int numbery = 0;
        number = Integer.parseInt(month);
        numbery = Integer.parseInt(year);
        return brigadaservice.distritoParentesco(number, numbery);
    }

    @GetMapping(value = "/distritoparentescoactual", produces = {"application/json"})
    public @ResponseBody List<DistritoParentesco> distritoParentescoActual() {
        return brigadaservice.distritoParentescoActual();
    }

    @GetMapping(value = "/titularactual", produces = {"application/json"})
    public @ResponseBody List<TitularParentesco> titularParentescoActual() {
        return brigadaservice.titularParentescoActual();
    }

    @GetMapping(value = "/titularparentesco/{month}/{year}", produces = {"application/json"})
    public @ResponseBody List<TitularParentesco> titularParentesco(@PathVariable String month, @PathVariable String year) {
        int number = 0;
        int numbery = 0;
        number = Integer.parseInt(month);
        numbery = Integer.parseInt(year);
        return brigadaservice.titularParentesco(number, numbery);
    }

    @GetMapping(value = "/verbri/{id}")
    public String ver(@PathVariable(value = "id") int idver, Model model) {

        Brigada brigada = brigadaservice.buscarporId(idver);

        model.addAttribute("brigada", brigada);
        model.addAttribute("titulo", "Detalle de la Visita Medica : " + ' ' + brigada.getTipobrigada() + ' ' + brigada.getZonabrigada());

        return "/views/brigada/verbrig";
    }

    @GetMapping("/createbrigada")
    public String crear(Model model) {

        Brigada brigada = new Brigada();
        List<Paciente> listpaciente = pacienteservice.listartodos();

        model.addAttribute("titulo", "Formulario: Nueva Brigada");
        model.addAttribute("brigada", brigada);
        model.addAttribute("paciente", listpaciente);

        return "/views/brigada/brigadamedicaform";
    }


    @PostMapping("/savebrigada")
    public String guardar(@ModelAttribute Brigada brigada, RedirectAttributes attribute, BindingResult result) {

        brigadaservice.guardar(brigada);
        attribute.addFlashAttribute("success", "Brigada Guardado con exito");
        System.out.println("Guardado con Exito la Brigada");
        return "redirect:/views/brigada/listbrigadamedica";
    }

    @GetMapping(value = "/editbrigada/{id}")
    public String editar(@PathVariable(value = "id") int idbrigada, Model model) {
        Brigada brigada = brigadaservice.buscarporId(idbrigada);

        model.addAttribute("titulo", "Formulario:Editar Brigada Medica");
        model.addAttribute("brigada", brigada);
        return "/views/brigada/brigadamedicaform";
    }


    @GetMapping("/deletebrigada/{id}")
    public String eliminar(@PathVariable("id") int idbrigada) {

        brigadaservice.eliminar(idbrigada);

        return "redirect:/views/brigada/brigmedlist";
    }


}
