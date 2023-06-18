package com.sisvime.app.controller;

import com.sisvime.app.models.Service.IUsuarioService;
import com.sisvime.app.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/views/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioservice;

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        List<Usuario> lista = usuarioservice.buscartodos();
        model.addAttribute("titulo", "Lista de Usuario");
        model.addAttribute("usuarios", lista);
        return "/views/usuario/usuarioform";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {
        // Eliminamos el usuario
        usuarioservice.eliminar(idUsuario);
        attributes.addFlashAttribute("msg", "El usuario fue eliminado!.");
        return "redirect:/views/usuario/index";
    }

    /**
     * Método para activar un usuario
     *
     * @param idUsuario
     * @param attributes
     * @return
     */
    @GetMapping("/unlock/{id}")
    public String activar(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {
        usuarioservice.activar(idUsuario);
        attributes.addFlashAttribute("msg", "El usuario fue activado y ahora tiene acceso al sistema.");
        return "redirect:/views/usuario/index";
    }

    /**
     * Método para bloquear un usuario
     *
     * @param idUsuario
     * @param attributes
     * @return
     */
    @GetMapping("/lock/{id}")
    public String bloquear(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {
        usuarioservice.bloquear(idUsuario);
        attributes.addFlashAttribute("msg", "El usuario fue bloqueado y no tendra acceso al sistema.");
        return "redirect:/views/usuario/index";
    }


    @GetMapping(value = "/veruser/{id}")
    public String ver(@PathVariable(value = "id") int id, Model model) {
        Usuario usuario = usuarioservice.buscarporId(id);
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "Detalle del Usuario");
        return "/views/usuario/verusuario";
    }


}
