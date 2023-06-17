package com.sisvime.app.models.Dao;

import com.sisvime.app.models.entity.Vehiculo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface IVehiculoDao extends PagingAndSortingRepository<Vehiculo, Integer> {

    @Modifying
    @Query("UPDATE Vehiculo u SET u.estatus=0 WHERE u.id = :paramVehiculo")
    int lock(@Param("paramVehiculo") int idVehiculo);

    @Modifying
    @Query("UPDATE Vehiculo u SET u.estatus=1 WHERE u.id = :paramVehiculo")
    int unlock(@Param("paramVehiculo") int idVehiculo);
}
