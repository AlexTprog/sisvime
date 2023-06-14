package com.sisvime.app.controller;

import com.sisvime.app.entity.Vehicle;
import com.sisvime.app.service.VehicleService;
import com.sisvime.app.share.VehicleStatus;
import org.springframework.beans.factory.annotation.Autowired;
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
public class VehicleController {
    @Autowired
    private VehicleService vehiculoservice;


    /* LISTAR los datos del Formulario */

    @RequestMapping(value = "/listarveh", method = RequestMethod.GET)
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageRequest = PageRequest.of(page, 4);

        //TODO Resolver
//        Page<Vehicle> vehiculos = vehiculoservice.findAll(pageRequest);

//        PageRender<Vehicle> pageRender = new PageRender<>("/listarveh", vehiculos);

        model.addAttribute("titulo", "Listado de Vehiculo");
//        model.addAttribute("vehiculos", vehiculos);
//        model.addAttribute("page", pageRender);

        return "/views/vehiculo/listarveh";
    }

    @GetMapping(value = "/listarvehiculos", produces = {"application/json"})
    public @ResponseBody List<Vehicle> listarVehiculo() {
        return vehiculoservice.getAll();
    }


    /* Primera CREAR ---fase mostrar formulario al usuario */
    @RequestMapping(value = "/formveh")
    public String crear(Map<String, Object> model) {
        var vehiculo = new Vehicle();

        model.put("vehiculo", vehiculo);
        model.put("titulo", "Formulario de Vehiculo");

        return "/views/vehiculo/formveh";
    }

    /* Primera EDITAR-- ---fase mostrar formulario al usuario */
    @RequestMapping(value = "/formveh/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
        Vehicle vehiculo = null;

        if (id > 0) {
            vehiculo = vehiculoservice.getById(id).orElse(null);

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
    public String guardar(@Valid Vehicle vehiculo, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status, @RequestParam("file") MultipartFile foto) {

        vehiculo.setStatus(VehicleStatus.Enable);// Activado por defecto

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario del Vehiculo");
            System.out.println("Existieron errores en el formulario");
            return "views/vehiculo/formveh";
        }
        // TODO Revisar
        if (!foto.isEmpty()) {
            //Path directorioImagenes = Paths.get("src//main//resources//static/img");
            String rutaAbosluta = "D://Users//RICHARD//Documents//workspace-spring-tool-suite-4-4.8.0.RELEASE//spring-boot-data-jpa//imagen//vehiculo";


            try {
                byte[] bytesImg = foto.getBytes();
                Path rutacompleta = Paths.get(rutaAbosluta + "//" + foto.getOriginalFilename());
                Files.write(rutacompleta, bytesImg);

                vehiculo.setPhoto(foto.getOriginalFilename());

            } catch (IOException e) {

                e.printStackTrace();
            }

        }

        vehiculoservice.create(vehiculo);
        status.setComplete();
        System.out.println("El vehiculo se ha guardado satisfactoriamente");
        return "redirect:/views/vehiculo/listarveh";
    }

    @RequestMapping(value = "/eliminarveh/{id}")
    public String eliminarveh(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

        if (id > 0) {
            vehiculoservice.delete(id);
            flash.addFlashAttribute("success", "Vehiculo eliminado con Exito!");
        }

        return "redirect:/views/vehiculo/listarveh";
    }

    @GetMapping("/unlock/{id}")
    public String activar(@PathVariable("id") Long idVehiculo, RedirectAttributes attributes) {
        vehiculoservice.active(idVehiculo);
        attributes.addFlashAttribute("msg", "El usuario fue activado y ahora tiene acceso al sistema.");
        return "redirect:/views/vehiculo/listarveh";
    }

    @GetMapping("/lock/{id}")
    public String bloquear(@PathVariable("id") Long idVehiculo, RedirectAttributes attributes) {
        vehiculoservice.block(idVehiculo);
        vehiculoservice.block(idVehiculo);
        attributes.addFlashAttribute("msg", "El usuario fue bloqueado y no tendra acceso al sistema.");
        return "redirect:/views/vehiculo/listarveh";
    }
}
