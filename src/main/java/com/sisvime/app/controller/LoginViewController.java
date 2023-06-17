package com.sisvime.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class LoginViewController {

    @GetMapping("/login")
    public String login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model,
            Principal principal,
            RedirectAttributes attribute) {
        if (error != null) {
            model.addAttribute("error",
                    "ERROR DE ACCESO: Usuario y/o Contraseña Incorrectos");
        }

        if (principal != null) {
            attribute.addFlashAttribute(
                    "warning",
                    "ATENCION: Usted ya a iniciado sesion anteriormente");

            return "redirect:/index";
        }

        if (logout != null) {
            model.addAttribute("success", "ATENCION: Ha finalizado sesion con exito");
        }

        return "login";
    }
}
