package br.edu.dombosco.dbcode.accessManagment.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@AllArgsConstructor
public class EmailController {
    private JavaMailSender mailSender;

    public void sendEmailToResetPassword(String emailAddress){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dbcode2023@outlook.com");
        message.setTo(emailAddress);
        message.setSubject("Redefinição de Senha");
        message.setText("Olá! \nSegue código de redefinição de senha:\n          \n\n\n\nOBS: Caso não tenha sido você que solicitou, desconsidere esse email. ");
        try {
            mailSender.send(message);
        } catch (Exception e){
            log.error("Error to send email { }", e);
        }

    }
}
