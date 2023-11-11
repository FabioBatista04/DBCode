package br.edu.dombosco.dbcode.requisitos.repository;
import br.edu.dombosco.dbcode.requisitos.model.Requisito;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequisitosRepository extends CrudRepository<Requisito, Long> {
    Requisito findRequisitoModelById(Long id);


}
