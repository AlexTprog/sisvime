package com.sisvime.app.controller;

import com.sisvime.app.entity.users.User;
import com.sisvime.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class HomeViewController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/calendarbrigada")
    public String brigada(Model model) {
        return "/calendarbrigada";

    }

    @GetMapping("/calendarvisitamedica")
    public String visitamedica(Model model) {
        return "/calendarvisitamed";
    }

    @GetMapping("/calendareservacita")
    public String reservacita(Model model) {
        return "/calendareservacita";

    }

    @GetMapping("/graficosmedicos")
    public String graficosmedicos(Model model) {
        return "/graficos";

    }

    @GetMapping("/graficosbrigada")
    public String graficosbrigada(Model model) {
        return "/graficosbrigada";

    }

    @GetMapping("/graficosvisita")
    public String graficosvisita(Model model) {
        return "/graficosvisita";

    }

    @GetMapping("/index")
    public String mostrarIndex(Authentication authentication, HttpSession session) {
        String username = authentication.getName();

        System.out.println("Nombre de Usuario: " + username);

        for (GrantedAuthority rol : authentication.getAuthorities()) {
            System.out.println("ROL: " + rol.getAuthority());
        }

        if (session.getAttribute("usuario") == null) {
            var usuario = userService.getByUsername(username);
            usuario.setPassword(usuario.getPassword());
            System.out.println("Usuario: " + usuario);
            session.setAttribute("usuario", usuario);
        }

        return "redirect:/";
    }

    @GetMapping("/signup")
    public String registrarse() {
        return "formRegistro";
    }

    @PostMapping("/signup")
    public String guardarRegistro() {
        //TODO
        //        String pwdPlano = usuario.getPassword();
//        String pwdEncriptado = passwordEncoder.encode(pwdPlano);
//        usuario.setPassword(pwdEncriptado);
//
//        usuario.setStatus(UserStatus.Active);// Activado por defecto
//
//        // TODO Creamos el Perfil que le asignaremos al usuario nuevo-ACA OCURRE EL PROBLEMA
////        var rol = new Rol();
////        rol.setId(1);
////        usuario.setRoles();
//        /**
//         * Guardamos el usuario en la base de datos. El Perfil se guarda automaticamente
//         */
//        userService.create(usuario);
//        attributes.addFlashAttribute("msg", "El registro fue guardado correctamente!");

        return "redirect:/views/usuario/index";
    }

    @GetMapping("/bcrypt/{texto}")
    @ResponseBody
    public String encriptar(@PathVariable("texto") String texto) {
        return texto + "Encriptado en Bcrypt: " + passwordEncoder.encode(texto);
    }
}
