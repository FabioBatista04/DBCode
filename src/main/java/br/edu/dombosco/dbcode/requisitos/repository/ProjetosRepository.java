package br.edu.dombosco.dbcode.requisitos.repository;
import br.edu.dombosco.dbcode.requisitos.model.ProjetosModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetosRepository extends CrudRepository<ProjetosModel, Long> {
}
