package br.edu.dombosco.dbcode.test.controller;

import br.edu.dombosco.dbcode.test.model.Plano;
import br.edu.dombosco.dbcode.test.repository.PlanoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlanoController {
    private PlanoRepository planoRepository;

    public Plano salvar(Plano plano){
        return planoRepository.save(plano);
    }
}
