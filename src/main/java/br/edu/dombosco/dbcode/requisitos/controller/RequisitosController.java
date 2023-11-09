package br.edu.dombosco.dbcode.requisitos.controller;

import br.edu.dombosco.dbcode.requisitos.model.RequisitoModel;
import br.edu.dombosco.dbcode.requisitos.repository.RequisitosRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class RequisitosController {

    @Autowired
    private RequisitosRepository repository;

    public RequisitoModel salvar(RequisitoModel requisitoModel) {
        return repository.save(requisitoModel);
    }

    public RequisitoModel editar(RequisitoModel requisitoModel) {
        return repository.save(requisitoModel);
    }
    
    
}
