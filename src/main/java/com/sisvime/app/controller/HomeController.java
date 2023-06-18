package com.sisvime.app.controller;

import com.sisvime.app.models.Dao.IPerfilesDao;
import com.sisvime.app.models.Service.IUsuarioService;
import com.sisvime.app.models.entity.Usuario;
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
public class HomeController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUsuarioService serviceUsuario;
    @Autowired
    private IPerfilesDao perfilesDao;

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "login";
        }
        return "home";
    }

    @GetMapping("/calendarbrigada")
    public String brigada(Authentication authentication, HttpSession session) {
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

        for (GrantedAuthority rol : authentication.getAuthorities()) {
            System.out.println("ROL: " + rol.getAuthority());
        }

        if (session.getAttribute("usuario") == null) {
            var usuario = serviceUsuario.buscarPorUsername(username);
            session.setAttribute("usuario", usuario);
        }
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String registrarse(Usuario usuario) {
        return "formRegistro";
    }

    @PostMapping("/signup")
    public String guardarRegistro(Usuario usuario, RedirectAttributes attributes) {
        var pwdPlano = usuario.getPassword();
        var pwdEncriptado = passwordEncoder.encode(pwdPlano);
        usuario.setPassword(pwdEncriptado);

        usuario.setEstatus(1);// Activado por defecto

        // Creamos el Perfil que le asignaremos al usuario nuevo-ACA OCURRE EL PROBLEMA
        var perfil = perfilesDao.getReferenceById(2);

        usuario.agregar(perfil);
        serviceUsuario.guardar(usuario);
        attributes.addFlashAttribute("msg", "El registro fue guardado correctamente!");

        return "redirect:/views/usuario/index";
    }

    @GetMapping("/bcrypt/{texto}")
    @ResponseBody
    public String encriptar(@PathVariable("texto") String texto) {
        return texto + "Encriptado en Bcrypt: " + passwordEncoder.encode(texto);
    }

}
