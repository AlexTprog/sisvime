package com.sisvime.app.models.Service;

import org.springframework.core.io.InputStreamSource;

import javax.mail.MessagingException;

public interface IEmailService {
    public void send(String from, String to, String subject, String text);

    public void SendRegisterCite(String to,String UserName);

    public void sendWithAttach(String from, String to, String subject, String text, String attachName, InputStreamSource inputStream) throws MessagingException;
}
