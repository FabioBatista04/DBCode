package br.edu.dombosco.dbcode.requisitos.repository;
import br.edu.dombosco.dbcode.requisitos.model.Projeto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetoRepository extends CrudRepository<Projeto, Long> {
    List<Projeto> findByTituloLike(String titulo);
    List<Projeto> findAll(Pageable pageable);
}
