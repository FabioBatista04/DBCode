package br.edu.dombosco.dbcode.test.controller;

import br.edu.dombosco.dbcode.test.model.Cenario;
import br.edu.dombosco.dbcode.test.repository.CenarioDao;
import br.edu.dombosco.dbcode.test.repository.CenarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CenarioController {
    private CenarioRepository cenarioRepository;
    private CenarioDao cenarioDao;

    public Cenario save(Cenario plano){
        return cenarioRepository.save(plano);
    }

    public List<Cenario> findFirst10(Long planoId) {
        return cenarioDao.findAll(planoId);
    }

    public List<Cenario> findByNome(String nome, Long planoId) {
        return cenarioDao.findByNomeLike(nome, planoId);
    }

    public Cenario buscarPorId(Long id, Long planoId) {
        return cenarioDao.findById(id, planoId);
    }

    public void delete(Long id) {
        cenarioDao.deleteById(id);
    }


}
