package br.edu.dombosco.dbcode.accessManagment.controller;

import br.edu.dombosco.dbcode.accessManagment.model.User;
import br.edu.dombosco.dbcode.accessManagment.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Log4j2
@Service
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    public User create(User user){
        validFieldsToRegister(user);
        if(!user.getFields().isEmpty())
            return user;
        var userByUsername = userRepository.findUserByUsername(user.getUsername());
        if(userByUsername != null){
            user.getFields().add("usuario já existe");
            return user;
        }

        var userEmail = userRepository.findUserByEmail(user.getEmail());
        if( userEmail != null){
            user.getFields().add("Email já existe");
            return user;
        }
        var userSaved =  userRepository.save(user);
        log.info("user salvo com sucesso");
        return userSaved;
    }

    private void validFieldsToRegister(User user) {
        String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);

        if(user.getUsername().isEmpty())
            user.getFields().add("Usuário");
        if(user.getPassword().isEmpty())
            user.getFields().add("Senha");
        if(user.getEmail().isEmpty())
            user.getFields().add("Email");
        if(!pattern.matcher(user.getEmail()).matches())
            user.getFields().add("Email");
        if(user.getProfile() == null)
            user.getFields().add("Perfil");
    }

    public User login(User user){
       validFields(user);
       if(user.getFields().isEmpty()){
           var userDb = userRepository.findUserByUsername(user.getUsername());
           if(userDb == null){
               user.getFields().add("Usuário");
               return user;
           }else {
               if(!userDb.getPassword().equals(user.getPassword())){
                   user.getFields().add("Senha");
                   return user;
               }
           }
            return userDb;
       }
       return user;
    }

    private void validFields(User user) {
        if(user.getUsername().isEmpty())
            user.getFields().add("Usuário");
        if(user.getPassword().isEmpty())
            user.getFields().add("Senha");
    }
    public User changePassword(User user){
        if(user == null) return null;
        var userDb = userRepository.findUserByUsername(user.getUsername());
        if(user.getPassword() == null || user.getPassword().isEmpty()) return null;
        userDb.setPassword(user.getPassword());
        return userRepository.save(userDb);
    }



    public void verifyCode(String code) {
        //receber email ou receber usuário para encontrar user

    }

    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User update(User user) {
        if(user == null) return null;
        return userRepository.save(user);
    }

    public User getUser(String usuario){
        return userRepository.findUserByUsername(usuario);
    }
}
