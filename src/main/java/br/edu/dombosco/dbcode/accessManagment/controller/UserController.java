package br.edu.dombosco.dbcode.accessManagment.controller;

import br.edu.dombosco.dbcode.accessManagment.model.User;
import br.edu.dombosco.dbcode.accessManagment.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    public User create(User user){
        var all = userRepository.findAll();
        all.forEach(log::info);
        if( user.containsNullFields()){
            log.error("Algum dos dados estão incorretos");
        }
        log.info("user salvo com sucesso");
        return userRepository.save(user);
    }

    public boolean login(User user){
       var userDataBase = userRepository.findUserByUsername(user.getUsername());
       return userDataBase != null;
    }

    public void verifyCode(String code) {
        //receber email ou receber usuário para encontrar user

    }
}
