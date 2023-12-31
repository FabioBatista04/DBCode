package br.edu.dombosco.dbcode.test.repository;

import br.edu.dombosco.dbcode.test.model.Plano;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanoRepository extends CrudRepository<Plano, Long> {
    
    List<Plano> findAll(Pageable pageable);

    List<Plano> findByNomeLike(String concat);
}
