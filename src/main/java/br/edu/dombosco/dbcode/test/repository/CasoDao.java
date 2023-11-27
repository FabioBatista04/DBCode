package br.edu.dombosco.dbcode.test.repository;

import br.edu.dombosco.dbcode.test.model.Caso;
import br.edu.dombosco.dbcode.test.model.Cenario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class CasoDao {

    private EntityManager em;


    public Caso findByIdAndProjectId(Long id, Long cenarioId) {
        return em.createQuery("SELECT c FROM Caso c WHERE c.id = :id AND c.cenario.id = :cenarioId", Caso.class)
                .setParameter("id", id)
                .setParameter("cenarioId", cenarioId)
                .getSingleResult();
    }

    public List<Caso> findByNomeLike(String nome, Long cenarioId) {
        log.info(">>>>> findByNomeLike: nome: {}, cenarioId: {}", nome, cenarioId);
        TypedQuery<Caso> query = em.createQuery("SELECT c FROM Caso c WHERE c.cenario.id = :cenarioId AND c.nome LIKE :nome", Caso.class);
        query.setParameter("nome", "%".concat(nome).concat("%"));
        query.setParameter("cenarioId", cenarioId);
        return query.setMaxResults(10).getResultList();


    }

    public List<Caso> findAll(Long cenarioId) {
        TypedQuery<Caso> query = em.createQuery("SELECT c FROM Caso c WHERE c.cenario.id = :cenarioId", Caso.class);
        query.setParameter("cenarioId", cenarioId);
        return query.setMaxResults(10).getResultList();
    }

    public Caso findById(Long id, Long cenarioId) {
        TypedQuery<Caso> query = em.createQuery("SELECT c FROM Caso c WHERE c.id = :id AND c.cenario.id = :cenarioId", Caso.class);
        query.setParameter("cenarioId", cenarioId);
        query.setParameter("id", id);
        return query.getSingleResult();

    }

    @Transactional
    public void deleteById(Long id) {
        em.createQuery("DELETE FROM CasoDetalhado cd WHERE cd.caso.id = :id")
                .setParameter("id", id)
                .executeUpdate();

        // Em seguida, deleta o registro em 'Plano'
        em.createQuery("DELETE FROM Caso c WHERE c.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
