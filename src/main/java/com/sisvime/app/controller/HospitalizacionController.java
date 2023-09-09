package com.sisvime.app.controller;

import com.sisvime.app.models.Dao.IPacienteDao;
import com.sisvime.app.models.Dao.IPerfilesDao;
import com.sisvime.app.models.Dto.HospitalizacionDto;
import com.sisvime.app.models.Service.IPacienteService;
import com.sisvime.app.models.Service.IPersonaService;
import com.sisvime.app.models.Service.IUsuarioService;
import com.sisvime.app.models.Service.Imp.CamaService;
import com.sisvime.app.models.Service.Imp.HospitalizacionService;
import com.sisvime.app.models.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/views/hospitalizacion")
public class HospitalizacionController {

    @Autowired
    protected ModelMapper modelMapper;
    @Autowired
    private HospitalizacionService hospitalizacionService;
    @Autowired
    private CamaService camaService;
    @Autowired
    private IPersonaService personalService;
    @Autowired
    private IPacienteService pacienteservice;


    @GetMapping("/IndicacionesMedicas")
    public String Hospitalizacion(Model model) {

        var listaHospitalizacion = hospitalizacionService.getAll();

        model.addAttribute("nombrePacienteFiltro", "");
        model.addAttribute("nombreMedicoFiltro", "");
        model.addAttribute("fechaFiltro", "");
        model.addAttribute("hospitalizaciones", listaHospitalizacion);

        return "/views/hospitalizacion/IndicacionesMedicas";
    }

    @GetMapping("/CamasLibres")
    @ResponseBody
    public List<Cama> Camas() {
        List<Cama> listaCamas = camaService.getAll();

        return listaCamas.stream()
                .filter(cama -> !cama.getEstaOcupado())
                .collect(Collectors.toList());
    }

    @GetMapping("/DoctoresAutorizados")
    @ResponseBody
    public List<Personal> Doctores() {
        List<Personal> listaPersonal = personalService.listartodos();

        List<String> especialidadesNoAdmitidas = new ArrayList<>(Arrays.asList("Enfermera", "Psicologo", "Chofer", "Técnica en Enfermería"));

        return listaPersonal.stream().filter(personal -> !especialidadesNoAdmitidas.contains(personal.getEspec().getNomespecialidad())).collect(Collectors.toList());
    }

    @GetMapping("/PacienteNoHospitalizados")
    @ResponseBody
    public List<Paciente> Pacientes() {
        List<Paciente> listaPacientes = pacienteservice.listartodos();
        return listaPacientes.stream().filter(paciente -> !paciente.getEstaHospitalizado()).collect(Collectors.toList());
    }

    @PostMapping("/HospitalizarPaciente")
    @ResponseBody
    public Hospitalizacion crearHospitalizacion(@RequestBody HospitalizacionDto hospitalizacion) {
        Hospitalizacion response;
        try {
            var camaDto = camaService.getById(hospitalizacion.Cama);
            var personalDto = personalService.buscarporId(hospitalizacion.Personal);
            var pacienteDto = pacienteservice.buscarporId(hospitalizacion.Paciente);
            pacienteDto.setEstaHospitalizado(true);
            pacienteservice.guardar(pacienteDto);

            camaDto.setEstaOcupado(true);
            camaService.create(camaDto);

            var mapHosp = modelMapper.map(hospitalizacion, Hospitalizacion.class);
            mapHosp.Cama = camaDto;
            mapHosp.Paciente = pacienteDto;
            mapHosp.Personal = personalDto;

            response = hospitalizacionService.create(mapHosp);
        } catch (Exception ex) {
            response = new Hospitalizacion();
        }
        return response;
    }


    @GetMapping("/filtrar")
    public String filtrarHospitalizaciones(@RequestParam(name = "nombrePaciente", required = false) String nombrePaciente,
                                           @RequestParam(name = "nombreMedico", required = false) String nombreMedico,
                                           @RequestParam(name = "fechaAdmision", required = false) String fechaAdmision,
                                           Model model) {

        List<Hospitalizacion> hospitalizacionesFiltradas = hospitalizacionService.filtrarHospitalizaciones(nombrePaciente, nombreMedico, fechaAdmision);


        model.addAttribute("nombrePacienteFiltro", nombrePaciente);
        model.addAttribute("nombreMedicoFiltro", nombreMedico);
        model.addAttribute("fechaFiltro", fechaAdmision);
        model.addAttribute("hospitalizaciones", hospitalizacionesFiltradas);

        return "/views/hospitalizacion/IndicacionesMedicas";
    }


}
