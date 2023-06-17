package com.sisvime.app.models.Dao;

import com.sisvime.app.models.entity.Visita;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVisitaDao extends CrudRepository<Visita, Integer> {
    @Query("SELECT count(v.idper) as visitas, p.nombre,p.apellidopat FROM Visita v LEFT JOIN Personal p ON v.idper = p.id where month(v.fecha)=month(now()) and year(v.fecha)=year(now()) group by v.idper")
    public List<Object[]> visitaPersonal();

    @Query("SELECT count(v.idper) as visitas, p.nombre,p.apellidopat FROM Visita v LEFT JOIN Personal p ON v.idper = p.id where month(v.fecha)= ?1 and year(v.fecha)=?2 group by v.idper")
    public List<Object[]> visitaPersonalMes(int term, int year);

    @Query("SELECT count(v.idper), p.nombre,p.apellidopat,v.nomenf,v.apeenf,v.nomtec,v.apetec FROM Visita v LEFT JOIN Personal p ON v.idper = p.id where month(v.fecha)=month(now()) and year(v.fecha)=year(now()) group by v.idper,v.idenf,v.idtec")
    public List<Object[]> grupoVisitaPersonal();

    @Query("SELECT count(v.idper), p.nombre,p.apellidopat,v.nomenf,v.apeenf,v.nomtec,v.apetec FROM Visita v LEFT JOIN Personal p ON v.idper = p.id where month(v.fecha)= ?1 and year(v.fecha)=?2 group by v.idper,v.idenf,v.idtec")
    public List<Object[]> grupoVisitaPersonalMes(int term, int year);


    @Query("SELECT count(v.idchf), v.nomchf, v.apechf FROM Visita v  where month(v.fecha)=month(now()) and year(v.fecha)=year(now()) group by v.idchf")
    public List<Object[]> visitaChofer();

    @Query("SELECT count(v.idchf), v.nomchf, v.apechf FROM Visita v  where month(v.fecha)= ?1 and year(v.fecha)=?2 group by v.idchf")
    public List<Object[]> visitaChoferMes(int term, int year);
}
