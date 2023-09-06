package br.edu.dombosco.domsoft.accessManagment.repository;

import br.edu.dombosco.domsoft.accessManagment.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUsername(String username);
}
