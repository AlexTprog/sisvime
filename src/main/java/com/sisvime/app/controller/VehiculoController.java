package com.sisvime.app.controller;

import com.sisvime.app.models.Service.IVehiculoService;
import com.sisvime.app.models.entity.Vehiculo;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/views/vehiculo")
public class VehiculoController {

    @Autowired
    private IVehiculoService vehiculoservice;


    /* LISTAR los datos del Formulario */

    @RequestMapping(value = "/listarveh", method = RequestMethod.GET)
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageRequest = PageRequest.of(page, 4);

        Page<Vehiculo> vehiculos = vehiculoservice.findAll(pageRequest);

        PageRender<Vehiculo> pageRender = new PageRender<>("/listarveh", vehiculos);

        model.addAttribute("titulo", "Listado de Vehiculo");
        model.addAttribute("vehiculos", vehiculos);
        model.addAttribute("page", pageRender);

        return "/views/vehiculo/listarveh";
    }

    @GetMapping(value = "/listarvehiculos", produces = {"application/json"})
    public @ResponseBody List<Vehiculo> listarVehiculo() {
        return vehiculoservice.findAll();
    }


    /* Primera CREAR ---fase mostrar formulario al usuario */
    @RequestMapping(value = "/formveh")
    public String crear(Map<String, Object> model) {
        Vehiculo vehiculo = new Vehiculo();

        model.put("vehiculo", vehiculo);
        model.put("titulo", "Formulario de Vehiculo");

        return "/views/vehiculo/formveh";
    }

    /* Primera EDITAR-- ---fase mostrar formulario al usuario */
    @RequestMapping(value = "/formveh/{id}")
    public String editar(@PathVariable(value = "id") int id, Map<String, Object> model, RedirectAttributes flash) {
        Vehiculo vehiculo = null;

        if (id > 0) {
            vehiculo = vehiculoservice.findOne(id);

            if (vehiculo == null) {
                flash.addFlashAttribute("error", "El ID del Vehiculo no existe en la Base de Datos");
                return "redirect:/listarveh";
            }
        } else {
            flash.addFlashAttribute("error", "El ID del Vehiculo no puede ser cero");
            return "redirect:/listarveh";
        }


        model.put("vehiculo", vehiculo);
        model.put("titulo", "Editar Vehiculo");

        return "/views/vehiculo/formveh";
    }

    /* Segunda fase el usuario envia en el metodo guardar los datos del formulario */
    /* Guardar */

    @RequestMapping(value = "/formveh", method = RequestMethod.POST)
    public String guardar(@Valid Vehiculo vehiculo, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status, @RequestParam("file") MultipartFile foto) {

        vehiculo.setEstatus(1);// Activado por defecto

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario del Vehiculo");
            System.out.println("Existieron errores en el formulario");
            return "views/vehiculo/formveh";
        }

        if (!foto.isEmpty()) {
            //Path directorioImagenes = Paths.get("src//main//resources//static/img");
            String rutaAbosluta = "D://Users//RICHARD//Documents//workspace-spring-tool-suite-4-4.8.0.RELEASE//spring-boot-data-jpa//imagen//vehiculo";


            try {
                byte[] bytesImg = foto.getBytes();
                Path rutacompleta = Paths.get(rutaAbosluta + "//" + foto.getOriginalFilename());
                Files.write(rutacompleta, bytesImg);

                vehiculo.setFoto(foto.getOriginalFilename());

            } catch (IOException e) {

                e.printStackTrace();
            }

        }

        vehiculoservice.save(vehiculo);
        status.setComplete();
        System.out.println("El vehiculo se ha guardado satisfactoriamente");
        return "redirect:/views/vehiculo/listarveh";
    }

    @RequestMapping(value = "/eliminarveh/{id}")
    public String eliminarveh(@PathVariable(value = "id") int id, RedirectAttributes flash) {

        if (id > 0) {
            vehiculoservice.delete(id);
            flash.addFlashAttribute("success", "Vehiculo eliminado con Exito!");
        }

        return "redirect:/views/vehiculo/listarveh";
    }

    @GetMapping("/unlock/{id}")
    public String activar(@PathVariable("id") int idVehiculo, RedirectAttributes attributes) {
        vehiculoservice.activar(idVehiculo);
        attributes.addFlashAttribute("msg", "El usuario fue activado y ahora tiene acceso al sistema.");
        return "redirect:/views/vehiculo/listarveh";
    }

    @GetMapping("/lock/{id}")
    public String bloquear(@PathVariable("id") int idVehiculo, RedirectAttributes attributes) {
        vehiculoservice.bloquear(idVehiculo);
        attributes.addFlashAttribute("msg", "El usuario fue bloqueado y no tendra acceso al sistema.");
        return "redirect:/views/vehiculo/listarveh";
    }

}
