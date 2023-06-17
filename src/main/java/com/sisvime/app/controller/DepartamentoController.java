package com.sisvime.app.controller;


import com.sisvime.app.models.Service.IDepartamentoService;
import com.sisvime.app.models.entity.Departamento;
import com.sisvime.app.share.pagination.PageRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
public class DepartamentoController {

    @Autowired
    private IDepartamentoService depaservice;

    /* LISTAR los datos del Formulario */

    @RequestMapping(value = "/listardep", method = RequestMethod.GET)
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

        Pageable pagueRequest = PageRequest.of(page, 4);

        var departamentos = depaservice.findAll(pagueRequest);

        PageRender<Departamento> pageRender = new PageRender<>("/listardep", departamentos);

        model.addAttribute("titulo", "Lista de Especialidades");
        model.addAttribute("departamentos", departamentos);
        model.addAttribute("page", pageRender);
        return "listardep";
    }

    /* Primera fase mostrar formulario al usuario */
    /* CREAR formulario al usuario */

    @RequestMapping(value = "/formdep")
    public String crear(Map<String, Object> model) {

        Departamento departamento = new Departamento();

        model.put("departamento", departamento);
        model.put("titulo", "Formulario de Departamentos del Peru");

        return "formdep";


    }

    /* EDITAR formulario al usuario */
    /* --------EDITAR-------  */
    @RequestMapping(value = "/formdep/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
        Departamento departamento = null;
        if (id > 0) {
            departamento = depaservice.findOne(id);

            if (departamento == null) {
                flash.addFlashAttribute("error", "El ID de Especialidad no existe en la Base de Datos");
                return "redirect:/listardep";
            }
        } else {
            flash.addFlashAttribute("Error", "El ID de Especialidad no puede ser cero");
            return "redirect:/listardep";
        }
        model.put("departamento", departamento);
        model.put("titulo", "Editar Departamento");
        return "formdep";


    }

    /* Segunda fase el usuario envia en el metodo guardar los datos del formulario */
    /* Guardar los datos del Formulario */
	
	/*@RequestMapping(value = "/formdep",method = RequestMethod.POST)
	public String guardar(@Valid Departamento departamento,BindingResult result,Model model,RedirectAttributes flash,SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo","Formulario de Departamento");
			return "formdep";
		}
		String mensajeFlash = (departamento.getId() !=null)? "Departamento editado con Éxito" : "Departamento creado con Éxito!";
		
		depaservice.save(departamento);
		status.setComplete();
		flash.addFlashAttribute("success",mensajeFlash);
		return "redirect:listardep";
	}
	*/

    /* ELIMINAR los datos del Formulario */

    @RequestMapping(value = "/eliminardep/{id}")
    public String eliminardep(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            depaservice.delete(id);
            flash.addFlashAttribute("success", "Departamento eliminado con Exito!");
        }
        return "redirect:/listardep";
    }


}
