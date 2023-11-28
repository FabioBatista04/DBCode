package br.edu.dombosco.dbcode.bugs.repository;

import br.edu.dombosco.dbcode.bugs.model.Bug;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BugDao {

    private EntityManager em;


    public Bug findByIdAndProjectId(Long id, Long projectId) {
        try {
            return em.createQuery("SELECT b FROM Bug b WHERE b.id = :id AND b.projeto.id = :projectId", Bug.class)
                    .setParameter("id", id)
                    .setParameter("projectId", projectId)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}

