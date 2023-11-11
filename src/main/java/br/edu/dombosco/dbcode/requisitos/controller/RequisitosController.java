package br.edu.dombosco.dbcode.requisitos.controller;

import br.edu.dombosco.dbcode.requisitos.model.Requisito;
import br.edu.dombosco.dbcode.requisitos.repository.RequisitosRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class RequisitosController {

    @Autowired
    private RequisitosRepository repository;

    public Requisito salvar(Requisito requisito) {
        return repository.save(requisito);
    }


    public Requisito findRequisitoModelById(Long id){
        return repository.findRequisitoModelById(id);
    }


    public void delete(Long objectId) {
        repository.deleteById(objectId);
    }

    public List<Requisito> buscar10Primeiros() {
        return repository.findAll(PageRequest.of(0, 10));
    }

    public List<Requisito> buscarPorTitulo(String titulo) {
        return repository.findByNomeLike(titulo.concat("%"));
    }
}
