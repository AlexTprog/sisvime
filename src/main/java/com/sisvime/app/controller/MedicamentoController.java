package com.sisvime.app.controller;


import com.sisvime.app.models.Service.IMedicamentoService;
import com.sisvime.app.models.entity.Medicamento;
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
public class MedicamentoController {

    @Autowired
    private IMedicamentoService medicservice;

    /* LISTAR los datos del Formulario */

    @RequestMapping(value = "/listarmed", method = RequestMethod.GET)
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageRequest = PageRequest.of(page, 4);

        var medicamentos = medicservice.findAllPage(pageRequest);

        var pageRender = new PageRender<>("/listarmed", medicamentos);

        model.addAttribute("titulo", "Listado de Medicamento");
        model.addAttribute("medicamentos", medicamentos);
        model.addAttribute("page", pageRender);

        return "listarmed";
    }

    /* Primera CREAR ---fase mostrar formulario al usuario */
    @RequestMapping(value = "/formmed")
    public String crear(Map<String, Object> model) {
        Medicamento medicamento = new Medicamento();

        model.put("medicamento", medicamento);
        model.put("titulo", "Formulario de Medicamento");

        return "formmed";
    }

    /* Primera EDITAR-- ---fase mostrar formulario al usuario */
    @RequestMapping(value = "/formmed/{id}")
    public String editar(@PathVariable(value = "id") int id, Map<String, Object> model, RedirectAttributes flash) {
        Medicamento medicamento = null;

        if (id > 0) {
            medicamento = medicservice.findOne(id);

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
    public String guardar(@Valid Medicamento medicamento, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario del Vehiculo");
            return "formmed";
        }
        /*String mensajeFlash =(medicamento.getId() !=null)? "Medicamento editado con Exito!" : "Medicamento creado con Exito";*/

        medicservice.save(medicamento);
        status.setComplete();
        /*flash.addFlashAttribute("success",mensajeFlash);*/
        return "redirect:listarmed";
    }

    @RequestMapping(value = "/eliminarmed/{id}")
    public String eliminarmed(@PathVariable(value = "id") int id, RedirectAttributes flash) {

        if (id > 0) {
            medicservice.delete(id);
            flash.addFlashAttribute("success", "Medicamento eliminado con Exito!");
        }

        return "redirect:/listarmed";
    }

}