package br.edu.dombosco.dbcode.test.controller;

import br.edu.dombosco.dbcode.test.model.CasoDetalhado;
import br.edu.dombosco.dbcode.test.repository.CasoDetalhadoDao;
import br.edu.dombosco.dbcode.test.repository.CasoDetalhadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CasoDetalhadoController {
    private CasoDetalhadoRepository casoRepository;
    private CasoDetalhadoDao casoDao;

    public CasoDetalhado save(CasoDetalhado plano){
        return casoRepository.save(plano);
    }

    public List<CasoDetalhado> findFirst10(Long casoId) {
        return casoDao.findAll(casoId);
    }

    public List<CasoDetalhado> findByPasso(String nome, Long casoId) {
        return casoDao.findByPassoLike(nome, casoId);
    }

    public CasoDetalhado buscarPorId(Long id, Long casoId) {
        return casoDao.findById(id,casoId);
    }

    public void delete(Long id) {
        casoRepository.deleteById(id);
    }


}
