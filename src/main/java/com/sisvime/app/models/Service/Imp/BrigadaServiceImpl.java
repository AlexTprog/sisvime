package com.sisvime.app.models.Service.Imp;


import com.sisvime.app.models.Dao.IBrigadaDao;
import com.sisvime.app.models.Service.IBrigadaService;
import com.sisvime.app.models.entity.Brigada;
import com.sisvime.app.models.entity.DistritoParentesco;
import com.sisvime.app.models.entity.TitularParentesco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrigadaServiceImpl implements IBrigadaService {

    @Autowired
    private IBrigadaDao brigadadao;

    @Override
    public List<Brigada> listartodos() {
        return (List<Brigada>) brigadadao.findAll();
    }

    @Override
    public void guardar(Brigada brigada) {
        brigadadao.save(brigada);

    }

    @Override
    public Brigada buscarporId(int id) {

        return brigadadao.findById(id).orElse(null);
    }

    @Override
    public void eliminar(int id) {

        brigadadao.deleteById(id);
    }

    @Override
    public List<DistritoParentesco> distritoParentesco(int month, int year) {
        List<DistritoParentesco> dp = new ArrayList<>();
        brigadadao.distritoParentesco(month, year).forEach(x -> {
            DistritoParentesco dis = new DistritoParentesco();
            dis.setNumero(Integer.parseInt(String.valueOf(x[0])));
            dis.setDistrito(String.valueOf(x[1]));
            dis.setParentesco(String.valueOf(x[2]));
            dp.add(dis);

        });

        return dp;
    }

    @Override
    public List<DistritoParentesco> distritoParentescoActual() {
        List<DistritoParentesco> dp = new ArrayList<>();
        brigadadao.distritoParentescoActual().forEach(x -> {
            DistritoParentesco dis = new DistritoParentesco();
            dis.setNumero(Integer.parseInt(String.valueOf(x[0])));
            dis.setDistrito(String.valueOf(x[1]));
            dis.setParentesco(String.valueOf(x[2]));
            dp.add(dis);

        });

        return dp;
    }

    @Override
    public ArrayList<TitularParentesco> titularParentesco(int month, int year) {
        var tp = new ArrayList<TitularParentesco>();
        brigadadao.titularParentesco(month, year).forEach(x -> {
            TitularParentesco titularParentesco = new TitularParentesco();
            titularParentesco.setNumero(Integer.parseInt(String.valueOf(x[0])));
            titularParentesco.setTitular(String.valueOf(x[1]));
            titularParentesco.setParentesco(String.valueOf(x[2]));
            tp.add(titularParentesco);
        });

        return tp;
    }

    @Override
    public List<TitularParentesco> titularParentescoActual() {
        List<TitularParentesco> tp = new ArrayList<>();
        brigadadao.titularParentescoActual().forEach(x -> {
            TitularParentesco titularParentesco = new TitularParentesco();
            titularParentesco.setNumero(Integer.parseInt(String.valueOf(x[0])));
            titularParentesco.setTitular(String.valueOf(x[1]));
            titularParentesco.setParentesco(String.valueOf(x[2]));
            tp.add(titularParentesco);

        });

        return tp;
    }

}
