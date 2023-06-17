package com.sisvime.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

// TODO Verificar si sirve/funciona
@Controller
public class SicknessViewController {
//    @Autowired
//    private IEnfermedadService enferservice;

    /* LISTAR los datos del Formulario */

    @RequestMapping(value = "/listarenf", method = RequestMethod.GET)
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
//        Pageable pageRequest = PageRequest.of(page, 4);
//        Page<Enfermedad> enfermedades = enferservice.findAllPage(pageRequest);
//
//        PageRender<Enfermedad> pageRender = new PageRender<>("/listarenf", enfermedades);
//
//        model.addAttribute("titulo", "Listado de Medicamento");
//        model.addAttribute("enfermedades", enfermedades);
//        model.addAttribute("page", pageRender);
        return "listarenf";
    }

    /* Primera CREAR ---fase mostrar formulario al usuario */
    @RequestMapping(value = "/formenf")
    public String crear(Map<String, Object> model) {
//        Enfermedad enfermedad = new Enfermedad();
//
//        model.put("enfermedad", enfermedad);
//        model.put("titulo", "Formulario de Enfermedad");

        return "formenf";
    }

    /* Primera EDITAR-- ---fase mostrar formulario al usuario */
    @RequestMapping(value = "/formenf/{id}")
    public String editar(@PathVariable(value = "id") int id, Map<String, Object> model, RedirectAttributes flash) {
//        Enfermedad enfermedad = null;
//
//        if (id > 0) {
//            enfermedad = enferservice.findOne(id);
//
//            if (enfermedad == null) {
//                flash.addFlashAttribute("error", "El ID de Enfermedad no existe en la Base de Datos");
//                return "redirect:/listarenf";
//            }
//        } else {
//            flash.addFlashAttribute("error", "El ID de Enfermedad no puede ser cero");
//            return "redirect:/listarenf";
//        }
//        model.put("enfermedad", enfermedad);
        model.put("titulo", "Editar Enfermedad");

        return "/formenf";
    }

    /* Segunda fase el usuario envia en el metodo guardar los datos del formulario */
    /* Guardar */

    @RequestMapping(value = "/formenf", method = RequestMethod.POST)
    public String guardar(
            /*@Valid Enfermedad enfermedad,*/ BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {

//        if (result.hasErrors()) {
//            model.addAttribute("titulo", "Formulario de Enfermedad");
//            return "formenf";
//        }
//        /*String mensajeFlash =(enfermedad.getId() !=null)? "Enfermedad editado con Exito!" : "Enfermedad creado con Exito";*/
//
//        enferservice.save(enfermedad);
//        status.setComplete();
//        /*flash.addFlashAttribute("success",mensajeFlash);*/
        return "redirect:listarenf";
    }

    @RequestMapping(value = "/eliminarenf/{id}")
    public String eliminarmed(@PathVariable(value = "id") int id, RedirectAttributes flash) {

//        if (id > 0) {
//            enferservice.delete(id);
//            flash.addFlashAttribute("success", "Enfermedad eliminado con Exito!");
//        }
        return "redirect:/listarenf";
    }
}
