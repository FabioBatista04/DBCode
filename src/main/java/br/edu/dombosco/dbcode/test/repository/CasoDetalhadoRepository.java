package br.edu.dombosco.dbcode.test.repository;

import br.edu.dombosco.dbcode.test.model.Caso;
import br.edu.dombosco.dbcode.test.model.CasoDetalhado;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CasoDetalhadoRepository extends CrudRepository<CasoDetalhado, Long> {

}
