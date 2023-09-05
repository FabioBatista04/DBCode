package br.edu.dombosco.domsoft.accessManagment.controller;

import br.edu.dombosco.domsoft.accessManagment.model.Profile;
import br.edu.dombosco.domsoft.accessManagment.model.User;
import br.edu.dombosco.domsoft.accessManagment.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    public User create(User user){
        if( user.containsNullFields()){
            log.error("Algum dos dados estão incorretos");
        }
        log.info("user salvo com sucesso");
        user.setProfile(Profile.ACCESS);
        return userRepository.save(user);



    }
}
