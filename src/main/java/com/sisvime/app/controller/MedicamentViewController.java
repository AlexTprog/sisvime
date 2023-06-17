package com.sisvime.app.controller;

import com.sisvime.app.entity.Medicament;
import com.sisvime.app.service.MedicamentService;
import com.sisvime.app.share.pagination.PageRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class MedicamentViewController {

    @Autowired
    private MedicamentService medicservice;

    /* LISTAR los datos del Formulario */

    @RequestMapping(value = "/listarmed", method = RequestMethod.GET)
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageRequest = PageRequest.of(page, 4);
        var medicamentos = medicservice.getAll(pageRequest);
        var pageRender = new PageRender<>("/listarmed", medicamentos);
        model.addAttribute("titulo", "Listado de Medicamento");
        model.addAttribute("medicamentos", medicamentos);
        model.addAttribute("page", pageRender);
        return "listarmed";
    }

    /* Primera CREAR ---fase mostrar formulario al usuario */
    @RequestMapping(value = "/formmed")
    public String crear(Map<String, Object> model) {
        var medicamento = new Medicament();
        model.put("medicamento", medicamento);
        model.put("titulo", "Formulario de Medicamento");
        return "formmed";
    }

    /* Primera EDITAR-- ---fase mostrar formulario al usuario */
    @RequestMapping(value = "/formmed/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
        Medicament medicamento = null;
        if (id > 0) {
            medicamento = medicservice.getById(id);

            if (medicamento == null) {
                flash.addFlashAttribute("error", "El ID del Medicamento no existe en la Base de Datos");
                return "redirect:/listarmed";
            }
        } else {
            flash.addFlashAttribute("error", "El ID del Medicamento no puede ser cero");
            return "redirect:/listarmed";
        }
        model.put("medicamento", medicamento);
        model.put("titulo", "Editar Medicamento");
        return "/formmed";
    }

    /* Segunda fase el usuario envia en el metodo guardar los datos del formulario */
    /* Guardar */

    @RequestMapping(value = "/formmed", method = RequestMethod.POST)
    public String guardar(@Valid Medicament medicamento, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario del Vehiculo");
            return "formmed";
        }
        medicservice.create(medicamento);
        status.setComplete();
        return "redirect:listarmed";
    }

    @RequestMapping(value = "/eliminarmed/{id}")
    public String eliminarmed(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            medicservice.delete(id);
            flash.addFlashAttribute("success", "Medicamento eliminado con Exito!");
        }
        return "redirect:/listarmed";
    }


}
