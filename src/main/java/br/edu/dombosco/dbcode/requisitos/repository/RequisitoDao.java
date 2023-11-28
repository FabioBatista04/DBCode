package br.edu.dombosco.dbcode.requisitos.repository;

import br.edu.dombosco.dbcode.requisitos.model.Projeto;
import br.edu.dombosco.dbcode.requisitos.model.Requisito;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class RequisitoDao {

    private EntityManager em;

    public List<Requisito> findFirst10(Long projetoId) {
        log.info("Buscando os 10 primeiros requisitos do projeto com id: {}", projetoId);
        TypedQuery<Requisito> query = em.createQuery("SELECT r FROM Requisito r WHERE r.projeto.id = :projetoId", Requisito.class);
        query.setParameter("projetoId", projetoId);
        return query.setMaxResults(10).getResultList();
    }

    public List<Requisito> findByName(String nome, Long projetoId) {
        log.info("Buscando os 10 primeiros requisitos do projeto com id: {}", projetoId);
        TypedQuery<Requisito> query = em.createQuery("SELECT r FROM Requisito r WHERE r.projeto.id = :projetoId AND r.nome LIKE :nome", Requisito.class);
        query.setParameter("projetoId", projetoId);
        query.setParameter("nome", "%".concat(nome).concat("%"));
        return query.setMaxResults(10).getResultList();
    }

    public Requisito findRequisitoModelById(Long id, Long projetoId) {
        log.info("Buscando o requisito com id: {}", id);
        TypedQuery<Requisito> query = em.createQuery("SELECT r FROM Requisito r WHERE r.projeto.id = :projetoId AND r.id = :id", Requisito.class);
        query.setParameter("projetoId", projetoId);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
