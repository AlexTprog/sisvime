package com.sisvime.app.controller;

import com.sisvime.app.models.Dao.IPacienteDao;
import com.sisvime.app.models.Dao.IPerfilesDao;
import com.sisvime.app.models.Service.IUsuarioService;
import com.sisvime.app.models.entity.Paciente;
import com.sisvime.app.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/views/hospitalizacion")
public class HospitalizacionController {

    @GetMapping("/IndicacionesMedicas")
    public String Hospitalizacion(Model model) {
        return "/views/hospitalizacion/IndicacionesMedicas";

    }

    @GetMapping("/ModuloMedico")
    public String ModuloMedico(Model model) {
        return "/views/hospitalizacion/ModuloMedico";

    }
    @GetMapping("/Ordenes")
    public String OrdenesMed(Model model) {
        return "/views/hospitalizacion/Ordenes";

    }



}
