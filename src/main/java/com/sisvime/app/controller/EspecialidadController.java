package com.sisvime.app.controller;


import com.sisvime.app.models.Service.IEspecialidadService;
import com.sisvime.app.models.entity.Especialidad;
import com.sisvime.app.share.pagination.PageRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class EspecialidadController {

    @Autowired
    private IEspecialidadService especialidadservice;

    /* LISTAR los datos del Formulario */

    @RequestMapping(value = "/listaresp", method = RequestMethod.GET)
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

        Pageable pagueRequest = PageRequest.of(page, 10);

        Page<Especialidad> especialidades = especialidadservice.findAll(pagueRequest);

        PageRender<Especialidad> pageRender = new PageRender<>("/listaresp", especialidades);

        model.addAttribute("titulo", "Lista de Especialidades");
        model.addAttribute("especialidades", especialidades);
        model.addAttribute("page", pageRender);
        return "listaresp";
    }

    @GetMapping(value = "/listar-especialidad", produces = {"application/json"})
    public @ResponseBody List<Especialidad> listarEspecialidad() {
        return especialidadservice.findAll();
    }

    /* Primera fase mostrar formulario al usuario */
    /* CREAR formulario al usuario */

    @RequestMapping(value = "/formesp")
    public String crear(Map<String, Object> model) {

        Especialidad especialidad = new Especialidad();

        model.put("especialidad", especialidad);
        model.put("titulo", "Formulario de Especialidades");

        return "formesp";


    }

    /* EDITAR formulario al usuario */
    /* --------EDITAR-------  */
    @RequestMapping(value = "/formesp/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
        Especialidad especialidad = null;
        if (id > 0) {
            especialidad = especialidadservice.findOne(id);

            if (especialidad == null) {
                flash.addFlashAttribute("error", "El ID de Especialidad no existe en la Base de Datos");
                return "redirect:/listaresp";
            }
        } else {
            flash.addFlashAttribute("Error", "El ID de Especialidad no puede ser cero");
            return "redirect:/listaresp";
        }
        model.put("especialidad", especialidad);
        model.put("titulo", "Editar Especialidad");
        return "formesp";


    }

    /* Segunda fase el usuario envia en el metodo guardar los datos del formulario */
    /* Guardar los datos del Formulario */

    @RequestMapping(value = "/formesp", method = RequestMethod.POST)
    public String guardar(@Valid Especialidad especialidad, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Especialidad");
            return "formesp";
        }
        String mensajeFlash = (especialidad.getId() != null) ? "Especialidad editado con Éxito" : "Especialidad creado con Éxito!";

        especialidadservice.save(especialidad);
        status.setComplete();
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:listaresp";
    }

    /* ELIMINAR los datos del Formulario */

    @RequestMapping(value = "/eliminaresp/{id}")
    public String eliminaresp(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            especialidadservice.delete(id);
            flash.addFlashAttribute("success", "Especialidad eliminado con Exito!");
        }
        return "redirect:/listaresp";
    }


}
