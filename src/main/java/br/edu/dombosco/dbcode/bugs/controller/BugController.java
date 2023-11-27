package br.edu.dombosco.dbcode.bugs.controller;

import br.edu.dombosco.dbcode.bugs.model.Bug;
import br.edu.dombosco.dbcode.bugs.repository.BugDao;
import br.edu.dombosco.dbcode.bugs.repository.BugRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@AllArgsConstructor
public class BugController {

    private final BugRepository bugRepository;
    private final BugDao bugDao;

    public Bug create(Bug bug){
        if(bug.containsNullFields()){
            log.error("Falha ao cadastrar bug");
            return null;
        }
        else{
            log.info("Bug cadastrado com sucesso");
            return this.bugRepository.save(bug);
        }
    }

    public Bug query(Long id, Long projectId){
        return bugDao.findByIdAndProjectId(id, projectId);
    }

    public List<Bug> listBugs(String status){

        List<Bug> bugsQuery;

        if (status.equals("Todos")){
            bugsQuery = (List<Bug>) this.bugRepository.findAll();
        }
        else{
            bugsQuery = this.bugRepository.findByStatus(status);
        }
        return bugsQuery;
    }

    public void delete(Long id){
        this.bugRepository.deleteById(id);
    }

    // Método de edição de bugs para o usuário do tipo testador.
    // SE o status do bug estiver "aberto".
    public void editBugAberto(
            Long id,
            String titulo,
            String status,
            String descricao,
            String reproducao,
            String file,
            String classificacao,
            String prioridade,
            Long projetoId)
    {
        Bug bug = this.query(id, projetoId);

        if (bug != null){
            bug.setTitulo(titulo);
            bug.setStatus(status);
            bug.setDescricao(descricao);
            bug.setReproducao(reproducao);
            bug.setFile(file);
            bug.setClassificacao(classificacao);
            bug.setPrioridade(prioridade);
            this.bugRepository.save(bug);
        }
    }

    // Método de edição de bugs para os usuários dev e testador.
    // Só será utilizado para o usuário testador SE o status do bug != "aberto"
    public void editStatusClassificacao(
            Long id,
            String status,
            String classificacao,
            Long projetoId)
    {
        Bug bug = this.query(id, projetoId);

        if (bug != null){
            bug.setStatus(status);
            bug.setClassificacao(classificacao);
            this.bugRepository.save(bug);
        }
    }

}
