package com.sisvime.app.controller;

import com.sisvime.app.models.Service.*;
import com.sisvime.app.models.entity.Cita;
import com.sisvime.app.models.entity.Enfermedad;
import com.sisvime.app.models.entity.Hora;
import com.sisvime.app.models.entity.Medicamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/views/citas")
public class CitaMedicaController {

    @Autowired
    private ICitaService citaservice;

    @Autowired
    public IEmailService emailService;

    @Autowired
    private IHoraService horaservice;

    @Autowired
    private IEnfermedadService enfermedadservice;

    @Autowired
    private IMedicamentoService medicamentoservice;

    @GetMapping("/reservacitas")
    public String listarcita(Model model) {
        var listcita = citaservice.listartodos().stream()
                .filter(p -> !p.getIsDelete())
                .collect(Collectors.toList());
        model.addAttribute("titulo", "Lista de Citas");
        model.addAttribute("citas", listcita);

        return "/views/citas/listareservacita";
    }

    @GetMapping(value = "/visitalista", produces = { "application/json" })
    public @ResponseBody List<Cita> listarCitas() {
        return citaservice.listartodos();
    }

    @GetMapping(value = "/vercita/{id}")
    public String ver(@PathVariable(value = "id") int idver, Model model) {

        Cita cita = citaservice.buscarporId(idver);

        model.addAttribute("cita", cita);
        model.addAttribute("titulo", "Detalle de la Cita : " + ' ' + cita.getId()
                + " | Fecha: " + cita.getFecha());

        return "/views/citas/vercita";
    }

    @GetMapping("/formcita")
    public String formulariocitas(Model model) {

        Cita cita = new Cita();
        List<Hora> listHora = horaservice.listaHora();
        List<Enfermedad> listEnfermedades = enfermedadservice.findall();
        List<Medicamento> listMedicamento = medicamentoservice.listarmedicamento();

        model.addAttribute("cita", cita);
        model.addAttribute("horas", listHora);
        model.addAttribute("enfermedade", listEnfermedades);
        model.addAttribute("medicamento", listMedicamento);

        return "/views/citas/createcita";
    }

    @GetMapping("/atendercita/{id}")
    public String atendercitas(@PathVariable(value = "id") int idcita, Model model) {
        Cita cita = citaservice.buscarporId(idcita);

        model.addAttribute("cita", cita);

        return "/views/citas/atendercita";
    }

    @PostMapping("/savereservacita")
    public String guardar(@ModelAttribute Cita cita,
            RedirectAttributes attribute,
            BindingResult result,
            HttpSession session) {
        citaservice.guardar(cita);
        attribute.addFlashAttribute("success", "Reserva de Cita Medica");
        System.out.println("Reserva de Cita Medica Reservada");

        emailService.SendRegisterCite(cita.getPac().getCorreo(), cita.getPac().getNombre());
        return "redirect:/views/citas/reservacitas";
    }

    @GetMapping("/deletereservacita/{id}")
    public String eliminar(@PathVariable("id") int idcita) {
        citaservice.eliminar(idcita);
        return "redirect:/views/citas/listareservacita";
    }

    @GetMapping("/softdeletereservacita/{id}")
    public String sofDelete(@PathVariable("id") int idcita) {
        citaservice.softDelete(idcita);
        return "redirect:/views/citas/listareservacita";
    }

}
