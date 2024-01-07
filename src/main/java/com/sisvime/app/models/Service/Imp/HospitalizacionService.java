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

            if (nombrePaciente != null && !nombrePaciente.isEmpty()) {
                String nombreCompletoPaciente = hospitalizacion.GetNombreCompletoPaciente();
                if (!nombreCompletoPaciente.toLowerCase().contains(nombrePaciente.toLowerCase())) {
                    cumpleCriterios = false;
                }
            }

            if (nombreMedico != null && !nombreMedico.isEmpty()) {
                String nombreCompletoMedico = hospitalizacion.GetNombreCompletoDoctor();
                if (!nombreCompletoMedico.toLowerCase().contains(nombreMedico.toLowerCase())) {
                    cumpleCriterios = false;
                }
            }

            if (fechaAdmision != null && !fechaAdmision.isEmpty()) {
                Date fechaAdmisionHospitalizacion = hospitalizacion.getFechaAdmision();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
