package com.sisvime.app.controller;

import com.sisvime.app.models.Dto.OrdenDietaDto;
import com.sisvime.app.models.Dto.OrdenDto;
import com.sisvime.app.models.Dto.OrdenMedicamentoDto;
import com.sisvime.app.models.Dto.OrdenServiciosDto;
import com.sisvime.app.models.Service.IMedicamentoService;
import com.sisvime.app.models.Service.Imp.*;
import com.sisvime.app.models.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/views/ordenes")
public class OrdenesController {

    @Autowired
    protected ModelMapper modelMapper;
    @Autowired
    private HospitalizacionService hospitalizacionService;
    @Autowired
    private IMedicamentoService medicamentoService;
    @Autowired
    private OrdenService ordenServicio;
    @Autowired
    private OrdenMedicamentoService ordenMedicamentoService;
    @Autowired
    private OrdenServicioService ordenServicioService;
    @Autowired
    private OrdenDietaService ordenDietaService;

    @GetMapping(value = "/Ordenes/{id}")
    public String OrdenesMed(@PathVariable(value = "id") int idPaciente, Model model) {

        var hospt = hospitalizacionService.getById(idPaciente);
        var edadPac = hospt.Paciente.calculaEdad();
        model.addAttribute("hospitalizacion", hospt);
        model.addAttribute("PacEdad", edadPac);
        return "/views/hospitalizacion/Ordenes";
    }

    @GetMapping("/medicamentos")
    @ResponseBody
    public List<Medicamento> medicamentosLista() {
        var meds = medicamentoService.listarmedicamento();
        return meds;
    }

    @PostMapping("/saveOrden")
    @ResponseBody
    public Orden crearOrden(@RequestBody OrdenDto orden) {
        Orden response;
        try {
            response = new Orden();
            response.Observacion = orden.indicaciones;
            response.FechaInicio = new Date();

            response = ordenServicio.create(response);
            //Enlazar a hospitalizacion
            var hosp = hospitalizacionService.getById(orden.idHospitalizacion);
            hosp.Orden = response;
            hospitalizacionService.create(hosp);

            for (var medicamentoOrden : orden.medicamentos) {
                var med = new OrdenMedicamento();
                med.Cantidad = medicamentoOrden.Cantidad;
                med.Medicamento = medicamentoOrden.Medicamento;
                med.TipoCantidad = medicamentoOrden.TipoCantidad;
                med.Frecuencia = medicamentoOrden.Frecuencia;
                med.ViaAdmision = medicamentoOrden.ViaAdmision;
                med.Administrado = medicamentoOrden.Administrado;
                med.FechaInicio = medicamentoOrden.FechaInicio;
                med.FechaFin = medicamentoOrden.FechaFin;
                med.Orden = response;
                ordenMedicamentoService.create(med);
            }

            for (var servicioOrden : orden.servicios) {
                var service = new OrdenServicio();
                service.Servicio = servicioOrden.Servicio;
                service.Prioridad = servicioOrden.Prioridad;
                service.SolicitadoA = servicioOrden.SolicitadoA;
                service.FechaInicio = servicioOrden.FechaInicio;
                service.Orden = response;
                ordenServicioService.create(service);
            }

            for (var dietaOrden : orden.dietas) {
                var dieta = new OrdenDieta();
                dieta.Dieta = dietaOrden.Dieta;
                dieta.Comentario = dietaOrden.Comentario;
                dieta.FechaInicio = dietaOrden.FechaInicio;
                dieta.Orden = response;
                ordenDietaService.create(dieta);
            }

        } catch (Exception ex) {
            response = new Orden();
        }
        return response;
    }

    @GetMapping("/lastOrden/{id}")
    @ResponseBody
    public OrdenDto getLasOrden(@PathVariable(value = "id") int hospitalizacionID) {

        var response = new OrdenDto();
        var hospt = hospitalizacionService.getById(hospitalizacionID);

        response.servicios = new ArrayList<>();
        response.medicamentos = new ArrayList<>();
        response.dietas = new ArrayList<>();

        response.indicaciones = hospt.Orden.Observacion;

        for (var servicio : new ArrayList<>(ordenServicioService.getAll())) {
            var dto = new OrdenServiciosDto();
            if (servicio.Orden.Id == hospt.Orden.Id) {
                dto.Prioridad = servicio.Prioridad;
                dto.Servicio = servicio.Servicio;
                dto.SolicitadoA = servicio.SolicitadoA;
                dto.FechaInicio = servicio.FechaInicio;
                response.servicios.add(dto);
            }
        }

        for (var medicamento : new ArrayList<>(ordenMedicamentoService.findAll())) {
            var dto = new OrdenMedicamentoDto();
            if (medicamento.Orden.Id == hospt.Orden.Id) {
                dto.Medicamento = medicamento.Medicamento;
                dto.Cantidad = medicamento.Cantidad;
                dto.Frecuencia = medicamento.Frecuencia;
                dto.ViaAdmision = medicamento.ViaAdmision;
                dto.Administrado = medicamento.Administrado;
                dto.TipoCantidad = medicamento.TipoCantidad;
                dto.FechaInicio = medicamento.FechaInicio;
                dto.FechaFin = medicamento.FechaFin;
                response.medicamentos.add(dto);
            }
        }

        for (var dieta : new ArrayList<>(ordenDietaService.getAll())) {
            var dto = new OrdenDietaDto();
            if (dieta.Orden.Id == hospt.Orden.Id) {
                dto.Dieta = dieta.Dieta;
                dto.Comentario = dieta.Comentario;
                dto.FechaInicio = dieta.FechaInicio;
                response.dietas.add(dto);
            }
        }

        return response;
    }
}
