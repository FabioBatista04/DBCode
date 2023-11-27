package br.edu.dombosco.dbcode.test.controller;

import br.edu.dombosco.dbcode.test.model.Caso;
import br.edu.dombosco.dbcode.test.repository.CasoDao;
import br.edu.dombosco.dbcode.test.repository.CasoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CasoController {
    private CasoRepository casoRepository;
    private CasoDao casoDao;

    public Caso save(Caso plano){
        return casoRepository.save(plano);
    }

    public List<Caso> findFirst10(Long cenarioId) {
        return casoDao.findAll(cenarioId);
    }

    public List<Caso> findByNome(String nome, Long cenarioId) {
        return casoDao.findByNomeLike(nome, cenarioId);
    }

    public Caso buscarPorId(Long casoId, Long cenarioId) {
        return casoDao.findById(casoId,cenarioId);
    }

    public void delete(Long id) {
        casoDao.deleteById(id);
    }


}
