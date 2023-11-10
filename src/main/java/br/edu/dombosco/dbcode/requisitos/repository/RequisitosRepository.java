package br.edu.dombosco.dbcode.requisitos.repository;
import br.edu.dombosco.dbcode.requisitos.model.RequisitoModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequisitosRepository extends CrudRepository<RequisitoModel, Long> {
    RequisitoModel findRequisitoModelById(Long id);


}
