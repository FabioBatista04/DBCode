package br.edu.dombosco.dbcode.requisitos.controller;

import br.edu.dombosco.dbcode.requisitos.model.Projeto;
import br.edu.dombosco.dbcode.requisitos.model.Requisito;
import br.edu.dombosco.dbcode.requisitos.repository.RequisitoDao;
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
    @Autowired
    private RequisitoDao dao;

    public Requisito salvar(Requisito requisito) {
        return repository.save(requisito);
    }


    public Requisito findRequisitoModelById(Long id, Long projetoId){
        return dao.findRequisitoModelById(id, projetoId);
    }


    public void delete(Long objectId) {
        repository.deleteById(objectId);
    }

    public List<Requisito> buscar10Primeiros(Projeto projeto){
        return dao.findFirst10(projeto.getId());
    }

    public List<Requisito> buscarPorTitulo(String nome, Long projetoId) {
        return dao.findByName(nome, projetoId);
    }
}
