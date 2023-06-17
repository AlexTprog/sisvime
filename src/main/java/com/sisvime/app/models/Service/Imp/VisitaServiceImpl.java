package com.sisvime.app.models.Service.Imp;


import com.sisvime.app.models.Dao.IVisitaDao;
import com.sisvime.app.models.Service.IVisitaService;
import com.sisvime.app.models.entity.GroupNumberVisitas;
import com.sisvime.app.models.entity.NumberVisitas;
import com.sisvime.app.models.entity.Visita;
import com.sisvime.app.models.entity.VisitasChofer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VisitaServiceImpl implements IVisitaService {

    @Autowired
    private IVisitaDao visitadao;

    @Override
    public List<Visita> listartodos() {
        return (List<Visita>) visitadao.findAll();
    }

    @Override
    public void guardar(Visita visita) {
        visitadao.save(visita);
    }

    @Override
    public Visita buscarporId(int id) {
        return visitadao.findById(id).orElse(null);
    }

    @Override
    public void eliminar(int id) {
        visitadao.deleteById(id);

    }

    @Override
    public List<NumberVisitas> visitaMedica() {
        List<NumberVisitas> lnv = new ArrayList<>();
        visitadao.visitaPersonal().forEach(x -> {
            NumberVisitas mv = new NumberVisitas();
            mv.setNumero(Integer.parseInt(String.valueOf(x[0])));
            mv.setNombre(String.valueOf(x[1]));
            mv.setApellido(String.valueOf(x[2]));
            lnv.add(mv);
        });
        return lnv;
    }

    @Override
    public List<NumberVisitas> visitaMedicaMes(int term, int year) {
        List<NumberVisitas> lnv = new ArrayList<>();
        visitadao.visitaPersonalMes(term, year).forEach(x -> {
            NumberVisitas mv = new NumberVisitas();
            mv.setNumero(Integer.parseInt(String.valueOf(x[0])));
            mv.setNombre(String.valueOf(x[1]));
            mv.setApellido(String.valueOf(x[2]));
            lnv.add(mv);
        });
        return lnv;
    }

    @Override
    public List<GroupNumberVisitas> grupoVisitaMedica() {
        List<GroupNumberVisitas> listGroupNumberVisit = new ArrayList<>();
        visitadao.grupoVisitaPersonal().forEach(x -> {
            GroupNumberVisitas groupNumberVisitas = new GroupNumberVisitas();
            groupNumberVisitas.setNumero(Integer.parseInt(String.valueOf(x[0])));
            groupNumberVisitas.setNombreMedico(String.valueOf(x[1]));
            groupNumberVisitas.setApellidoMedico(String.valueOf(x[2]));
            groupNumberVisitas.setNombreEnfermera(String.valueOf(x[3]));
            groupNumberVisitas.setApellidoEnfermera(String.valueOf(x[4]));
            groupNumberVisitas.setNombreTecnico(String.valueOf(x[5]));
            groupNumberVisitas.setApellidoTecnico(String.valueOf(x[6]));
            listGroupNumberVisit.add(groupNumberVisitas);
        });

        return listGroupNumberVisit;
    }

    @Override
    public List<GroupNumberVisitas> grupoVisitaMedicaMes(int term, int year) {
        List<GroupNumberVisitas> listGroupNumberVisit = new ArrayList<>();
        visitadao.grupoVisitaPersonalMes(term, year).forEach(x -> {
            GroupNumberVisitas groupNumberVisitas = new GroupNumberVisitas();
            groupNumberVisitas.setNumero(Integer.parseInt(String.valueOf(x[0])));
            groupNumberVisitas.setNombreMedico(String.valueOf(x[1]));
            groupNumberVisitas.setApellidoMedico(String.valueOf(x[2]));
            groupNumberVisitas.setNombreEnfermera(String.valueOf(x[3]));
            groupNumberVisitas.setApellidoEnfermera(String.valueOf(x[4]));
            groupNumberVisitas.setNombreTecnico(String.valueOf(x[5]));
            groupNumberVisitas.setApellidoTecnico(String.valueOf(x[6]));
            listGroupNumberVisit.add(groupNumberVisitas);
        });

        return listGroupNumberVisit;
    }

    @Override
    public List<VisitasChofer> visitaChofer() {
        List<VisitasChofer> visitasChofer = new ArrayList<>();
        visitadao.visitaChofer().forEach(x -> {
            VisitasChofer visitaChofer = new VisitasChofer();
            visitaChofer.setNumero(Integer.parseInt(String.valueOf(x[0])));
            visitaChofer.setNombre(String.valueOf(x[1]));
            visitaChofer.setApellido(String.valueOf(x[2]));
            visitasChofer.add(visitaChofer);
        });
        return visitasChofer;
    }

    @Override
    public List<VisitasChofer> visitaChoferMes(int term, int year) {
        List<VisitasChofer> visitasChofer = new ArrayList<>();
        visitadao.visitaChoferMes(term, year).forEach(x -> {
            VisitasChofer visitaChofer = new VisitasChofer();
            visitaChofer.setNumero(Integer.parseInt(String.valueOf(x[0])));
            visitaChofer.setNombre(String.valueOf(x[1]));
            visitaChofer.setApellido(String.valueOf(x[2]));
            visitasChofer.add(visitaChofer);
        });
        return visitasChofer;
    }


}
