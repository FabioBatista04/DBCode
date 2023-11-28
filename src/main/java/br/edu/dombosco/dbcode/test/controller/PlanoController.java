package br.edu.dombosco.dbcode.test.controller;

import br.edu.dombosco.dbcode.test.model.Plano;
import br.edu.dombosco.dbcode.test.repository.PlanoDao;
import br.edu.dombosco.dbcode.test.repository.PlanoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlanoController {
    private PlanoRepository planoRepository;
    private PlanoDao planoDao;

    public Plano save(Plano plano){
        return planoRepository.save(plano);
    }

    public List<Plano> findFirst10(Long projectId) {
        return planoDao.findAll(projectId);
    }

    public List<Plano> findByNome(String nome, Long projetoId) {
        return planoDao.findByNomeAndProjectId(nome, projetoId);
    }

    public Plano buscarPorId(Long id, Long projetoId) {
        return planoDao.findById(id, projetoId);
    }

    public void delete(Long id) {
        planoDao.deleteById(id);
    }
}
