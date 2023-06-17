package com.sisvime.app.service;

import com.sisvime.app.entity.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;


    public void SendRegisterCite(User user) throws MessagingException {

        var message = mailSender.createMimeMessage();
        var helper = new MimeMessageHelper(message, true);
        var context = new Context();

        helper.setTo(user.getEmail());
        helper.setSubject("Nueva Cita Registrada!");
        context.setVariable("nombreUsuario", user.getName());
        var content = templateEngine.process("registerCite", context);

        helper.setText(content, true);

        mailSender.send(message);

    }

    public void sendWithAttach(String from, String to, String subject,
                               String text, String attachName,
                               InputStreamSource inputStream) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        helper.addAttachment(attachName, inputStream);
        mailSender.send(message);
    }

    public void send(String from, String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
