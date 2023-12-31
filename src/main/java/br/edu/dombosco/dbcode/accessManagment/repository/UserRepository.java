package br.edu.dombosco.dbcode.accessManagment.repository;

import br.edu.dombosco.dbcode.accessManagment.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUsername(String username);
    User findUserByEmail(String email);
}
