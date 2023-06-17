package com.sisvime.app.models.Service;

import com.sisvime.app.models.entity.Usuario;

import java.util.List;

public interface IUsuarioService {

    public List<Usuario> buscartodos();

    public void guardar(Usuario usuario);

    public void eliminar(Integer idUsuario);

    Usuario buscarPorUsername(String username);

    public Usuario buscarporId(Integer id);

    int bloquear(int idUsuario);

    int activar(int idUsuario);


}
