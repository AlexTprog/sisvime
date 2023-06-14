package com.sisvime.app.controller;

import com.sisvime.app.entity.users.User;
import com.sisvime.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/views/user")
public class UserViewController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        List<User> lista = userService.getAll();
        model.addAttribute("titulo", "Lista de Usuario");
        model.addAttribute("usuarios", lista);
        return "/views/usuario/usuarioform";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") Long idUser, RedirectAttributes attributes) {


        userService.deleteById(idUser);

        attributes.addFlashAttribute("msg", "El usuario fue eliminado!.");
        return "redirect:/views/usuario/index";
    }

    /**
     * Método para activar un usuario
     *
     * @param idUser
     * @param attributes
     * @return
     */
    @GetMapping("/unlock/{id}")
    public String activar(@PathVariable("id") Long idUser, RedirectAttributes attributes) {
        userService.activeUser(idUser);
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
    public String bloquear(@PathVariable("id") Long idUsuario, RedirectAttributes attributes) {
        userService.blockUser(idUsuario);
        attributes.addFlashAttribute("msg", "El usuario fue bloqueado y no tendra acceso al sistema.");
        return "redirect:/views/usuario/index";
    }


    @GetMapping(value = "/veruser/{id}")
    public String ver(@PathVariable(value = "id") Long id, Model model) {
        User usuario = userService.getById(id).orElse(null);

        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "Detalle del Usuario");

        return "/views/usuario/verusuario";
    }


}
