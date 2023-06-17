package com.sisvime.app.models.Service.Imp;

import com.sisvime.app.models.Dao.IVehiculoDao;
import com.sisvime.app.models.Service.IVehiculoService;
import com.sisvime.app.models.entity.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

    @Autowired
    private IVehiculoDao vehiculodao;

    @Override
    @Transactional(readOnly = true)
    public List<Vehiculo> findAll() {

        return (List<Vehiculo>) vehiculodao.findAll();
    }

    @Override
    @Transactional
    public void save(Vehiculo vehiculo) {
        vehiculodao.save(vehiculo);

    }

    @Override
    @Transactional(readOnly = true)
    public Vehiculo findOne(Integer id) {

        return vehiculodao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        vehiculodao.deleteById(id);

    }

    @Override
    @Transactional(readOnly = true)
    public Page<Vehiculo> findAll(Pageable pageable) {

        return vehiculodao.findAll(pageable);
    }

    @Transactional
    @Override
    public int bloquear(int idVehiculo) {
        int rows = vehiculodao.lock(idVehiculo);
        return rows;
    }

    @Transactional
    @Override
    public int activar(int idVehiculo) {
        int rows = vehiculodao.unlock(idVehiculo);
        return rows;
    }


}
