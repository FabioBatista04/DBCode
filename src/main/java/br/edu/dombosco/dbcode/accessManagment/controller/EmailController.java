package br.edu.dombosco.dbcode.accessManagment.controller;

import br.edu.dombosco.dbcode.accessManagment.model.User;
import br.edu.dombosco.dbcode.accessManagment.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Log4j2
@Service
@AllArgsConstructor
public class EmailController {
    private JavaMailSender mailSender;
    private UserRepository userRepository;

    public User sendEmailToResetPassword(String emailAddress){
        SimpleMailMessage message = new SimpleMailMessage();
        var user = userRepository.findUserByEmail(emailAddress);
        if ( user == null ) return null;
        String code = generateCode();
        user.setResetCode(code);
        userRepository.save(user);

        message.setFrom("dbcode2023@outlook.com");
        message.setTo(emailAddress);
        message.setSubject("Redefinição de Senha");
        message.setText("Olá! \nSegue código de redefinição de senha:\n   " + code + "     \n\n\n\nOBS: Caso não tenha sido você que solicitou, desconsidere esse email. ");
        try {
            mailSender.send(message);
            return user;
        } catch (Exception e){
            log.error("Error to send email { }", e);
        }
        return null;
    }

    private String generateCode() {
        StringBuilder code = new StringBuilder();
        var r = new Random();

        for (int i = 2; i <= 7; i++ ){
            if(i % 2 == 0 ){
                int base = r.nextBoolean() ? 'A' : 'a';
                char wValue = (char) (base + r.nextInt(26));
                 code.append(String.valueOf(wValue));
            } else {
                code.append(String.valueOf(r.nextInt(10)));
            }
        }
        return code.toString();
    }
}
