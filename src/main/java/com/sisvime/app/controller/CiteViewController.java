package com.sisvime.app.controller;

import com.sisvime.app.entity.Cite;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/views/citas")
public class CiteViewController {
//    @Autowired
//    private ICitaService citaservice;
//
//    @Autowired
//    public IEmailService emailService;
//
//    @Autowired
//    private IHoraService horaservice;
//
//    @Autowired
//    private IEnfermedadService enfermedadservice;
//
//    @Autowired
//    private IMedicamentoService medicamentoservice;


    @GetMapping("/reservacitas")
    public String listarcita(Model model) {
//        List<Cita> listcita = citaservice.listartodos();
//        model.addAttribute("titulo", "Lista de Citas");
//        model.addAttribute("citas", listcita);

        return "/views/citas/listareservacita";
    }

//    @GetMapping(value = "/visitalista", produces = {"application/json"})
//    public @ResponseBody List<Cita> listarCitas() {
//        return citaservice.listartodos();
//    }

//    @GetMapping(value = "/vercita/{id}")
//    public String ver(@PathVariable(value = "id") int idver, Model model) {
//
//        Cita cita = citaservice.buscarporId(idver);
//
//        model.addAttribute("cita", cita);
//        model.addAttribute("titulo", "Detalle de la Cita : " + ' ' + cita.getId()
//                + " | Fecha: " + cita.getFecha());
//
//        return "/views/citas/vercita";
//    }

    @GetMapping("/formcita")
    public String formulariocitas(Model model) {

//        Cita cita = new Cita();
//        List<Hora> listHora = horaservice.listaHora();
//        List<Enfermedad> listEnfermedades = enfermedadservice.findall();
//        List<Medicamento> listMedicamento = medicamentoservice.listarmedicamento();
//
//        model.addAttribute("cita", cita);
//        model.addAttribute("horas", listHora);
//        model.addAttribute("enfermedade", listEnfermedades);
//        model.addAttribute("medicamento", listMedicamento);

        return "/views/citas/createcita";
    }

    @GetMapping("/atendercita/{id}")
    public String atendercitas(@PathVariable(value = "id") int idcita, Model model) {
//        Cita cita = citaservice.buscarporId(idcita);
//
//        model.addAttribute("cita", cita);
        return "/views/citas/atendercita";
    }

//	@GetMapping(value = "/atendercita/{id}")
//	public String editar(@PathVariable(value = "id") int id, Model model) {
//
//		Cita cita =citaservice.buscarporId(id);
//
//
//		model.addAttribute("titulo", "Formulario: Atender Cita");
//		model.addAttribute("cita", cita);
//
//		return "/views/citas/AtenderCita";
//
//	}

    @PostMapping("/savereservacita")
    public String guardar(@ModelAttribute Cite cita, RedirectAttributes attribute, BindingResult result) {
//        citaservice.guardar(cita);
//        attribute.addFlashAttribute("success", "Reserva de Cita Medica");
//        System.out.println("Reserva de Cita Medica Reservada");
        return "redirect:/views/citas/reservacitas";
    }

    @GetMapping("/deletereservacita/{id}")
    public String eliminar(@PathVariable("id") int idcita) {
//        citaservice.eliminar(idcita);
        return "redirect:/views/citas/listareservacita";
    }


}
