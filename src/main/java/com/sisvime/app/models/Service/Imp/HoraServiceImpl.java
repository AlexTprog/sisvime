package com.sisvime.app.models.Service.Imp;

import com.sisvime.app.models.Dao.IHoraDao;
import com.sisvime.app.models.Service.IHoraService;
import com.sisvime.app.models.entity.Hora;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoraServiceImpl implements IHoraService {

    @Autowired
    private IHoraDao horadao;

    @Override
    public List<Hora> listaHora() {
        return (List<Hora>) horadao.findAll();
    }

    @Override
    public Hora Get(int id) {
        return horadao.findById(id).orElse(null);
    }

}
