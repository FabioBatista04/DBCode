package br.edu.dombosco.dbcode.test.repository;

import br.edu.dombosco.dbcode.test.model.Cenario;
import br.edu.dombosco.dbcode.test.model.Plano;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CenarioRepository extends CrudRepository<Cenario, Long> {
    
    List<Cenario> findAll(Pageable pageable);

    List<Cenario> findByNomeLike(String concat);
}
