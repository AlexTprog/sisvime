package com.sisvime.app.controller;

import com.sisvime.app.models.Service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utils/mail")
public class EmailController {
    @Autowired
    public IEmailService emailService;

    @GetMapping(value = "/sendemail", produces = "application/json")
    public @ResponseBody String sendEmail(
            @RequestParam("to") String to,
            @RequestParam("username") String UserName) {

        emailService.SendRegisterCite(to, UserName);

        return "OK";
    }
}
