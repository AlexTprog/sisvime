package com.sisvime.app.models.Service.Imp;

import com.sisvime.app.models.Dao.IHospitalizacionRepostiory;
import com.sisvime.app.models.entity.Hospitalizacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HospitalizacionService {
    @Autowired
    private IHospitalizacionRepostiory hospitalizacionRepostiory;

    public List<Hospitalizacion> getAll() {
        return hospitalizacionRepostiory.findAll();
    }

    public Hospitalizacion create(Hospitalizacion hospitalizacion) {
        return hospitalizacionRepostiory.save(hospitalizacion);
    }

    public Hospitalizacion getById(Integer id) {
        return hospitalizacionRepostiory.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        hospitalizacionRepostiory.deleteById(id);
    }

    public List<Hospitalizacion> filtrarHospitalizaciones(String nombrePaciente, String nombreMedico, String fechaAdmision) {
        List<Hospitalizacion> hospitalizacionesFiltradas = new ArrayList<>();

        for (Hospitalizacion hospitalizacion : getAll()) {
            boolean cumpleCriterios = true;

            // Filtrar por nombre del paciente (si se proporciona)
            if (nombrePaciente != null && !nombrePaciente.isEmpty()) {
                String nombreCompletoPaciente = hospitalizacion.GetNombreCompletoPaciente(); // Define este método en tu entidad Paciente
                if (!nombreCompletoPaciente.toLowerCase().contains(nombrePaciente.toLowerCase())) {
                    cumpleCriterios = false;
                }
            }

            // Filtrar por nombre del médico (si se proporciona)
            if (nombreMedico != null && !nombreMedico.isEmpty()) {
                String nombreCompletoMedico = hospitalizacion.GetNombreCompletoDoctor(); // Define este método en tu entidad Personal
                if (!nombreCompletoMedico.toLowerCase().contains(nombreMedico.toLowerCase())) {
                    cumpleCriterios = false;
                }
            }

            // Filtrar por fecha de admisión (si se proporciona)
            if (fechaAdmision != null && !fechaAdmision.isEmpty()) {
                Date fechaAdmisionHospitalizacion = hospitalizacion.getFechaAdmision();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Ajusta el formato según tus necesidades
                String fechaAdmisionStr = sdf.format(fechaAdmisionHospitalizacion);
                if (!fechaAdmisionStr.equals(fechaAdmision)) {
                    cumpleCriterios = false;
                }
            }

            if (cumpleCriterios) {
                hospitalizacionesFiltradas.add(hospitalizacion);
            }
        }

        return hospitalizacionesFiltradas;
    }
}
