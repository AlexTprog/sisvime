package com.sisvime.app.models.Service;

import com.sisvime.app.models.entity.Vehiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IVehiculoService {

    public List<Vehiculo> findAll();

    public Page<Vehiculo> findAll(Pageable pageable);

    public void save(Vehiculo vehiculo);

    public Vehiculo findOne(Integer id);

    public void delete(Integer id);

    int bloquear(int idVehiculo);

    int activar(int idVehiculo);
}
