package br.edu.dombosco.dbcode.requisitos.controller;

import br.edu.dombosco.dbcode.requisitos.model.Requisito;
import br.edu.dombosco.dbcode.requisitos.repository.RequisitosRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class RequisitosController {

    @Autowired
    private RequisitosRepository repository;

    public Requisito salvar(Requisito requisito) {
        return repository.save(requisito);
    }

    public Requisito editar(Requisito requisito) {
        return repository.save(requisito);
    }

    public Requisito findRequisitoModelById(Long id){
        return repository.findRequisitoModelById(id);
    }


    public void delete(Long objectId) {
        repository.deleteById(objectId);
    }
}
