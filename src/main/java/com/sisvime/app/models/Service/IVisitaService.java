package com.sisvime.app.models.Service;


import com.sisvime.app.models.entity.GroupNumberVisitas;
import com.sisvime.app.models.entity.NumberVisitas;
import com.sisvime.app.models.entity.Visita;
import com.sisvime.app.models.entity.VisitasChofer;

import java.util.List;

public interface IVisitaService {


    public List<Visita> listartodos();

    public void guardar(Visita visita);

    public Visita buscarporId(int id);

    public void eliminar(int id);

    public List<NumberVisitas> visitaMedica();

    public List<NumberVisitas> visitaMedicaMes(int term, int year);

    public List<GroupNumberVisitas> grupoVisitaMedica();

    public List<GroupNumberVisitas> grupoVisitaMedicaMes(int term, int year);

    public List<VisitasChofer> visitaChofer();

    public List<VisitasChofer> visitaChoferMes(int term, int year);

}
