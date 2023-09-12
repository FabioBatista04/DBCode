package br.edu.dombosco.dbcode.accessManagment.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class EmailController {

    private final JavaMailSender mailSender;

    public EmailController(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    public void sendEmailToResetPassword(String emailAddress){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailAddress);
        message.setSubject("Teste");
        message.setText("Teste Body");

        mailSender.send(message);
    }
}
