package br.edu.dombosco.dbcode.test.repository;

import br.edu.dombosco.dbcode.requisitos.model.Requisito;
import br.edu.dombosco.dbcode.test.model.Plano;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class PlanoDao {

    private EntityManager em;


    public Plano findByIdAndProjectId(Long id, Long projectId) {
        return em.createQuery("SELECT p FROM Plano p WHERE p.id = :id AND p.projeto.id = :projectId", Plano.class)
                .setParameter("id", id)
                .setParameter("projectId", projectId)
                .getSingleResult();
    }

    public List<Plano> findByNomeAndProjectId(String nome, Long projectId) {
        TypedQuery<Plano> query = em.createQuery("SELECT p FROM Plano p WHERE p.projeto.id = :projectId AND p.nome LIKE :nome", Plano.class);
        query.setParameter("nome", "%".concat(nome).concat("%"));
        query.setParameter("projectId", projectId);
        return query.setMaxResults(10).getResultList();


    }

    public List<Plano> findAll(Long projectId) {
        TypedQuery<Plano> query = em.createQuery("SELECT p FROM Plano p WHERE p.projeto.id = :projectId", Plano.class);
        query.setParameter("projectId", projectId);
        return query.setMaxResults(10).getResultList();
    }

    public Plano findById(Long id, Long projectId) {
        TypedQuery<Plano> query = em.createQuery("SELECT p FROM Plano p WHERE p.id = :id AND p.projeto.id = :projectId", Plano.class);
        query.setParameter("projectId", projectId);
        query.setParameter("id", id);
        return query.getSingleResult();

    }
    @Transactional
    public void deleteById(Long id) {
        em.createNativeQuery("DELETE FROM caso_detalhado WHERE CASO_ID IN (" +
                        "SELECT c.id FROM caso c " +
                            "INNER JOIN cenario cn ON c.cenario_id = cn.id " +
                            "INNER JOIN plano p ON cn.plano_id = p.id " +
                        "WHERE p.id = :id)")
                .setParameter("id", id)
                .executeUpdate();

        em.createNativeQuery("DELETE FROM caso WHERE CENARIO_ID IN (SELECT id FROM cenario WHERE PLANO_ID = :id)")
                .setParameter("id", id)
                .executeUpdate();

        em.createQuery("DELETE FROM Cenario c WHERE c.plano.id = :id")
                .setParameter("id", id)
                .executeUpdate();

        // Em seguida, deleta o registro em 'Plano'
        em.createQuery("DELETE FROM Plano p WHERE p.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
