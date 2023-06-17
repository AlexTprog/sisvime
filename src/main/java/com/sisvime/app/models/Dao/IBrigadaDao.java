package com.sisvime.app.models.Dao;

import com.sisvime.app.models.entity.Brigada;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IBrigadaDao extends CrudRepository<Brigada, Integer> {


    @Query("SELECT count(b.obs), b.obs,p.parentesco FROM Brigada b LEFT JOIN Paciente p ON b.idpac = p.id where month(b.fecha)= ?1 and year(b.fecha)=?2 group by b.obs,p.parentesco")
    public List<Object[]> distritoParentesco(int month, int year);

    @Query("SELECT count(b.obs), b.obs,p.parentesco FROM Brigada b LEFT JOIN Paciente p ON b.idpac = p.id where month(b.fecha)=month(now())  AND year(b.fecha)=year(now()) group by b.obs,p.parentesco")
    public List<Object[]> distritoParentescoActual();


    @Query("SELECT count(p.parentesco), p.titular,p.parentesco FROM Brigada b LEFT JOIN Paciente p ON b.idpac = p.id where month(b.fecha)= ?1 and year(b.fecha)=?2 group by p.titular,p.parentesco")
    public List<Object[]> titularParentesco(int month, int year);

    @Query("SELECT count(p.parentesco), p.titular,p.parentesco FROM Brigada b LEFT JOIN Paciente p ON b.idpac = p.id where month(b.fecha)=month(now())  AND year(b.fecha)=year(now())  group by p.titular,p.parentesco")
    public List<Object[]> titularParentescoActual();
}
