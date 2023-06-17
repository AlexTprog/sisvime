package com.sisvime.app.models.Service.Imp;

import com.sisvime.app.models.Dao.IUsuarioDao;
import com.sisvime.app.models.Service.IUsuarioService;
import com.sisvime.app.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioDao usuariodao;


    @Override
    public void guardar(Usuario usuario) {
        usuariodao.save(usuario);
    }

    @Override
    public void eliminar(Integer idUsuario) {
        usuariodao.deleteById(idUsuario);
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        return usuariodao.findByUsername(username);
    }

    @Override
    public List<Usuario> buscartodos() {
        return usuariodao.findAll();
    }

    @Transactional
    @Override
    public int bloquear(int idUsuario) {
        int rows = usuariodao.lock(idUsuario);
        return rows;
    }

    @Transactional
    @Override
    public int activar(int idUsuario) {
        int rows = usuariodao.unlock(idUsuario);
        return rows;
    }

    @Override
    public Usuario buscarporId(Integer id) {
        return usuariodao.findById(id).orElse(null);
    }


}
