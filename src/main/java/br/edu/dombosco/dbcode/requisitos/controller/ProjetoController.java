package br.edu.dombosco.dbcode.requisitos.controller;

import br.edu.dombosco.dbcode.requisitos.model.Projeto;
import br.edu.dombosco.dbcode.requisitos.repository.ProjetoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ProjetoController {
    private ProjetoRepository projetoRepository;


    public Projeto salvar(Projeto projeto){
        return projetoRepository.save(projeto);
    }

    public void deletar(Long id) {
        projetoRepository.deleteById(id);
    }

    public Projeto buscarPorId(Long id) {
        return projetoRepository.findById(id).orElse(null);
    }

    public List<Projeto> buscarPorTitulo(String text) {
        return projetoRepository.findByTituloLike("%"+text+"%");
    }

    public List<Projeto> buscar10Primeiros() {
        return projetoRepository.findAll(PageRequest.of(0, 10, Sort.by("Id")));
    }
}
