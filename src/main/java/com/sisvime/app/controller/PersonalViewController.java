package com.sisvime.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/views/personal")
public class PersonalViewController {
//    @Autowired
//    private IPersonaService personaservice;
//
//    @Autowired
//    private IEspecialidadService especialidadservice;
//
//    @Autowired
//    private ICitaService citaService;
//
//    @Autowired
//    private IHoraService horaService;

    @GetMapping(value = "/verp/{id}")
    public String ver(@PathVariable(value = "id") Long idver, Model model) {
//        Personal personal = personaservice.buscarporId(idver);
//        model.addAttribute("personal", personal);
//        model.addAttribute("titulo", "Detalle del Personal Medico: " + ' ' + personal.getNombre() + ' ' + personal.getApellidopat());
        return "/views/personal/ver";
    }

    @GetMapping("/listarper")
    public String listarPersonal(Model model) {
//        List<Personal> listadopersonal = personaservice.listartodos();
//        model.addAttribute("titulo", "Listado del Personal");
//        model.addAttribute("personales", listadopersonal);
        return "/views/personal/listarper";
    }

    /**
     * LISTA JSON DE PERSONAL
     */
//    @GetMapping(value = "/listarpersonal", produces = {"application/json"})
//    public @ResponseBody List<Personal> cargarPersonal() {
//        return personaservice.listartodos();
//    }
    @GetMapping("/createper")
    public String crear(Model model) {

//        Personal personal = new Personal();
//
//        List<Especialidad> listespecialidades = especialidadservice.findAll();
//
//        //List<Genero> listgeneros=generoservice.findAll();
//        //List<Civil> listciviles=civilservice.findAll();
//
//        model.addAttribute("titulo", "Formulario: Nuevo Personal Medico");
//        model.addAttribute("personal", personal);
//        model.addAttribute("especialidades", listespecialidades);
//        //model.addAttribute("generos", listgeneros);
//        //model.addAttribute("civiles", listciviles);


        return "/views/personal/createper";

    }

    @PostMapping("/saveper")
    public String guardar(
//            @Valid @ModelAttribute Personal personal,
            BindingResult result, Model model, @RequestParam("file") MultipartFile foto,
            RedirectAttributes attribute) {

//        List<Especialidad> listespecialidades = especialidadservice.findAll();
//
//        if (result.hasErrors()) {
//            model.addAttribute("titulo", "Formulario: Nuevo Personal Medico");
//            model.addAttribute("personal", personal);
//            model.addAttribute("especialidades", listespecialidades);
//            return "views/personal/createper";
//        }
//
//
//        if (!foto.isEmpty()) {
//            //Path directorioImagenes = Paths.get("src//main//resources//static/img");
//            String rutaAbosluta = "D://Users//RICHARD//Documents//workspace-spring-tool-suite-4-4.8.0.RELEASE//spring-boot-data-jpa//imagen//personal";
//
//
//            try {
//                byte[] bytesImg = foto.getBytes();
//                Path rutacompleta = Paths.get(rutaAbosluta + "//" + foto.getOriginalFilename());
//                Files.write(rutacompleta, bytesImg);
//
//                personal.setFoto(foto.getOriginalFilename());
//
//            } catch (IOException e) {
//
//                e.printStackTrace();
//            }
//
//        }
//        personaservice.guardar(personal);
//        attribute.addFlashAttribute("success", "Personal guardado con exito!");
//        System.out.println(" Guardado con exito!!");
        return "redirect:/views/personal/listarper";
    }


    @GetMapping(value = "/editper/{id}")
    public String editar(@PathVariable(value = "id") Long idpersonal, Model model) {
//        Personal personal = personaservice.buscarporId(idpersonal);
//        List<Especialidad> listespecialidades = especialidadservice.findAll();
//        //List<Genero> listgeneros=generoservice.findAll();
//        //List<Civil> listciviles=civilservice.findAll();
//        model.addAttribute("titulo", "Formulario: Editar Personal Medico");
//        model.addAttribute("personal", personal);
//        model.addAttribute("especialidades", listespecialidades);
//        //model.addAttribute("generos", listgeneros);
//        //model.addAttribute("civiles", listciviles);
        return "/views/personal/createper";
    }

    @GetMapping("/deleteper/{id}")
    public String eliminar(@PathVariable("id") Long idpersonal) {
//        personaservice.eliminar(idpersonal);
        return "redirect:/views/personal/listarper";
    }

//    //    List horarios ocupados personal
//
//    @GetMapping(value = "/AvalibleTimes/{name}/{date}", produces = {"application/json"})
//    public @ResponseBody List<Hora> AvalibleTimes(
//            @PathVariable(value = "name") String name,
//            @PathVariable(value = "date") String date
//    ) throws ParseException {
//        var formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
//        var fechaAtencion = new Date();
//
//        fechaAtencion = formatoFecha.parse(date);
//
//        List<Hora> horas = horaService.listaHora();
//
//        var medicos = citaService.listartodos();
//        for (var personal : medicos) {
//            if (personal.getMed().equals(name) &&
//                    formatoFecha.format(fechaAtencion).equals(formatoFecha.format((personal.getFecha())))) {
//                var hour = horaService.Get(personal.getIdhora().getId());
//                horas.remove(hour);
//            }
//        }
//        return horas;
//    }
}
