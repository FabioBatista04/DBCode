package br.edu.dombosco.dbcode.test.repository;

import br.edu.dombosco.dbcode.test.model.Plano;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanoRepository extends CrudRepository<Plano, Long> {
}
