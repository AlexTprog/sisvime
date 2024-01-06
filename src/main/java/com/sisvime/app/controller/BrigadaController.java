package com.sisvime.app.controller;

import com.sisvime.app.models.Service.IBrigadaService;
import com.sisvime.app.models.Service.IPacienteService;
import com.sisvime.app.models.Service.IVisitaService;
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

//VISITA MEDICA
@Controller
@RequestMapping("/views/brigada")
public class BrigadaController {

    @Autowired
    private IVisitaService visitaservice;

    @Autowired
    private IBrigadaService brigadaservice;

    @Autowired
    private IPacienteService pacienteservice;

    @GetMapping("/listbrigadamedica")
    public String listarbrigadas(Model model) {

        var listadobrigada = brigadaservice.listartodos();
        model.addAttribute("titulo", "Lista de Visitas Medicas");
        model.addAttribute("brigadas", listadobrigada);

        return "/views/brigada/brigmedlist";
    }

    @GetMapping(value = "/brigadamedicalista", produces = { "application/json" })
    public @ResponseBody List<Brigada> listarBrigadas() {
        return brigadaservice.listartodos();
    }

    @GetMapping(value = "/distritoparentesco/{month}/{year}", produces = { "application/json" })
    public @ResponseBody List<DistritoParentesco> distritoParentesco(@PathVariable String month,
            @PathVariable String year) {
        int number = 0;
        int numbery = 0;
        number = Integer.parseInt(month);
        numbery = Integer.parseInt(year);
        return brigadaservice.distritoParentesco(number, numbery);
    }

    @GetMapping(value = "/distritoparentescoactual", produces = { "application/json" })
    public @ResponseBody List<DistritoParentesco> distritoParentescoActual() {
        return brigadaservice.distritoParentescoActual();
    }

    @GetMapping(value = "/titularactual", produces = { "application/json" })
    public @ResponseBody List<TitularParentesco> titularParentescoActual() {
        return brigadaservice.titularParentescoActual();
    }

    @GetMapping(value = "/titularparentesco/{month}/{year}", produces = { "application/json" })
    public @ResponseBody List<TitularParentesco> titularParentesco(@PathVariable String month,
            @PathVariable String year) {
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
        model.addAttribute("titulo",
                "Detalle de la Visita Medica : " + ' ' + brigada.getTipobrigada() + ' ' + brigada.getZonabrigada());

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
    public String guardar(@ModelAttribute Brigada brigada,Model model ,RedirectAttributes attribute, BindingResult result) {

        // Obtener todas las visitas
        var allvisits = visitaservice.listartodos();

        // Realizar la validación directamente en el controlador
        boolean existeBrigada = false;

        for (var existingBrigada : brigadaservice.listartodos()) {
            if (existingBrigada.getIdpac().equals(brigada.getIdpac()) && existingBrigada.getFecha().equals(brigada.getFecha())) {
                existeBrigada = true;
                break;
            }
        }

        if (!existeBrigada) {

            // Guardar la nueva brigada
            brigadaservice.guardar(brigada);

            for (var visita : allvisits) {
                if (visita.getHora().equals(brigada.getHoraini()) &&
                        visita.getIsFree() &&
                        visita.getFecha().compareTo(brigada.getFecha()) == 0) {
                    visita.setIsFree(false);
                    visitaservice.guardar(visita);
                    break;
                }
            }

            return "redirect:/views/brigada/listbrigadamedica";
        } else {

            attribute.addFlashAttribute("mensaje", "El paciente ya tiene una visita para esa fecha");
            return "redirect:/views/brigada/createbrigada";
        }
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

    @DeleteMapping("/deletebrigada/{id}")
    public void delete(@PathVariable("id") int idbrigada){
        brigadaservice.eliminar(idbrigada);
    }

}
