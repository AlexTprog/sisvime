package com.sisvime.app.controller;

import com.sisvime.app.models.Dto.MessageDto;
import com.sisvime.app.models.Dto.VisitaDto;
import com.sisvime.app.models.Service.IPacienteService;
import com.sisvime.app.models.Service.IPersonaService;
import com.sisvime.app.models.Service.IVehiculoService;
import com.sisvime.app.models.Service.IVisitaService;
import com.sisvime.app.models.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

//BRIGADA
@Controller
@RequestMapping("/views/visitamed")
public class VisitaController {

    @Autowired
    private IVisitaService visitaservice;

    @Autowired
    private IPersonaService personaservice;

    @Autowired
    private IPacienteService pacienteservice;

    @Autowired
    private IVehiculoService vehiculoservice;

    @GetMapping("/listvisitamedica")
    public String listarvisita(Model model) {
        List<Visita> listavisita = visitaservice.listartodos();
        model.addAttribute("titulos", "Lista de Visita Medica");
        model.addAttribute("visitas", listavisita);
        return "/views/visitamed/visitamedicalist";
    }

    @GetMapping(value = "/visitalista", produces = {"application/json"})
    public @ResponseBody List<Visita> listarVisitas() {
        return visitaservice.listartodos();
    }

    @GetMapping(value = "/numerovisita", produces = {"application/json"})
    public @ResponseBody List<NumberVisitas> visitaMedica() {
        return visitaservice.visitaMedica();
    }

    @GetMapping(value = "/getallvisitas", produces = {"application/json"})
    public @ResponseBody List<Visita> getAllVisit() {
        return visitaservice.listartodos();
    }

    @GetMapping(value = "/numerovisitames/{term}/{year}", produces = {"application/json"})
    public @ResponseBody List<NumberVisitas> visitaMedicaMes(@PathVariable String term, @PathVariable String year) {
        int number = 0;
        int numbery = 0;
        number = Integer.parseInt(term);
        numbery = Integer.parseInt(year);
        return visitaservice.visitaMedicaMes(number, numbery);
    }

    @GetMapping(value = "/gruponumerovisita", produces = {"application/json"})
    public @ResponseBody List<GroupNumberVisitas> grupoVisitaMedica() {
        return visitaservice.grupoVisitaMedica();
    }

    @GetMapping(value = "/gruponumerovisitames/{term}/{year}", produces = {"application/json"})
    public @ResponseBody List<GroupNumberVisitas> grupoVisitaMedicaMes(@PathVariable String term,
                                                                       @PathVariable String year) {
        int number = 0;
        int numbery = 0;
        number = Integer.parseInt(term);
        numbery = Integer.parseInt(year);
        return visitaservice.grupoVisitaMedicaMes(number, numbery);
    }

    @GetMapping(value = "/visitachofer", produces = {"application/json"})
    public @ResponseBody List<VisitasChofer> visitasChofer() {
        return visitaservice.visitaChofer();
    }

    @GetMapping(value = "/visitachofermes/{term}/{year}", produces = {"application/json"})
    public @ResponseBody List<VisitasChofer> visitasChoferMes(@PathVariable String term, @PathVariable String year) {
        int number = 0;
        int numbery = 0;
        number = Integer.parseInt(term);
        numbery = Integer.parseInt(year);
        return visitaservice.visitaChoferMes(number, numbery);
    }

    @GetMapping(value = "/vervist/{id}")
    public String ver(@PathVariable(value = "id") int idver, Model model) {

        Visita visita = visitaservice.buscarporId(idver);

        model.addAttribute("visita", visita);
        model.addAttribute("titulo", "Detalle de la Brigada : " + ' ' + visita.getFecha() + ' ' + visita.getZona());
        return "/views/visitamed/vervisita";
    }

    @GetMapping("/createvisita")
    public String crear(Model model) {
        Visita visita = new Visita();

        List<Personal> listvisita = personaservice.listartodos();
        List<Paciente> listpaciente = pacienteservice.listartodos();
        List<Vehiculo> listvehiculo = vehiculoservice.findAll();

        model.addAttribute("visita", visita);
        model.addAttribute("personal", listvisita);
        model.addAttribute("paciente", listpaciente);
        model.addAttribute("vehiculo", listvehiculo);

        return "/views/visitamed/visitamedform";
    }

    @PostMapping("/savevisita")
    public String guardar(@ModelAttribute Visita visita, RedirectAttributes attribute, BindingResult result) {
        visitaservice.guardar(visita);
        attribute.addFlashAttribute("success", "Visita Medica Guardado con Exito");
        System.out.println("Guardado con exito la Visita Medica");

        return "redirect:/views/visitamed/listvisitamedica";
    }

    @PostMapping(value = "/savevisitaDto")
    public ResponseEntity<String> guardarVisitaDto(VisitaDto visita) {
       try{

           var formato = new SimpleDateFormat("yyyy-MM-dd");
           var StringDates = visita.fecha.split(",");

           Visita visit;
           for (var fecha : StringDates) {
               Date date;
               try {
                   date = formato.parse(fecha);
               } catch (ParseException e) {
                   date = new Date();
               }
               visit = new Visita();

               //Pero
               visit.setIdper(visita.idper);

               //Enf
               visit.setIdenf(visita.idenf);
               visit.setApeenf(visita.apeenf);
               visit.setNomenf(visita.nomenf);
               visit.setPro_espenf(visita.pro_espenf);

               //Chofer
               visit.setIdchf(visita.idchf);
               visit.setApechf(visita.apechf);
               visit.setNomchf(visita.nomchf);

               //Tec
               visit.setIdtec(visita.idtec);
               visit.setApetec(visita.apetec);
               visit.setEspchf(visita.espchf);
               visit.setNomtec(visita.nomtec);

               //Vehi
               visit.setIdveh(visita.idveh);

               //Visita
               visit.setDist(visita.dist);
               visit.setDesc(visita.desc);
               visit.setHora(visita.hora);
               visit.setZona(visita.zona);
               visit.setTit(visita.tit);
               visit.setTime(visita.time);
               visit.setObs(visita.obs);
               //Fecha
               visit.setFecha(date);

               visitaservice.guardar(visit);
           }

           return new ResponseEntity<>("Las Brigada fueron creadas con exito", HttpStatus.CREATED);
       }catch (Exception ex){
           return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_GATEWAY);
       }
    }

    @GetMapping(value = "/editvisita/{id}")
    public String editar(@PathVariable(value = "id") int idvisita, Model model) {
        Visita visita = visitaservice.buscarporId(idvisita);

        model.addAttribute("Titulo", "Formulario:Editar Visita Medica");
        model.addAttribute("visita", visita);
        return "/views/visitamed/visitamedform";
    }

    @GetMapping("/deletevisita/{id}")
    public String eliminar(@PathVariable("id") int idvisita) {
        visitaservice.eliminar(idvisita);
        return "redirect:/views/visitamed/visitamedicalist";
    }

    @DeleteMapping("/deletevisita/{id}")
    public void  delete(@PathVariable("id") int idvisita) {
        visitaservice.eliminar(idvisita);
    }

}
