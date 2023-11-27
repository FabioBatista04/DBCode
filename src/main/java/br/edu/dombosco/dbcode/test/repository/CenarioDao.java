package br.edu.dombosco.dbcode.test.repository;

import br.edu.dombosco.dbcode.test.model.Cenario;
import br.edu.dombosco.dbcode.test.model.Plano;
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
public class CenarioDao {

    private EntityManager em;


    public Cenario findByIdAndProjectId(Long id, Long planoId) {
        return em.createQuery("SELECT c FROM Cenario c WHERE c.id = :id AND c.plano.id = :planoId", Cenario.class)
                .setParameter("id", id)
                .setParameter("planoId", planoId)
                .getSingleResult();
    }

    public List<Cenario> findByNomeLike(String nome, Long planoId) {
        log.info(">>>>> findByNomeLike: nome: {}, planoId: {}", nome, planoId);
        TypedQuery<Cenario> query = em.createQuery("SELECT c FROM Cenario c WHERE c.plano.id = :planoId AND c.nome LIKE :nome", Cenario.class);
        query.setParameter("nome", "%".concat(nome).concat("%"));
        query.setParameter("planoId", planoId);
        return query.setMaxResults(10).getResultList();


    }

    public List<Cenario> findAll(Long planoId) {
        TypedQuery<Cenario> query = em.createQuery("SELECT c FROM Cenario c WHERE c.plano.id = :planoId", Cenario.class);
        query.setParameter("planoId", planoId);
        return query.setMaxResults(10).getResultList();
    }

    public Cenario findById(Long id, Long planoId) {
        TypedQuery<Cenario> query = em.createQuery("SELECT c FROM Cenario c WHERE c.id = :id AND c.plano.id = :planoId", Cenario.class);
        query.setParameter("planoId", planoId);
        query.setParameter("id", id);
        return query.getSingleResult();

    }

    @Transactional
    public void deleteById(Long id) {
        em.createNativeQuery("DELETE FROM caso_detalhado WHERE CASO_ID IN (SELECT id FROM caso WHERE cenario_id = :id)")
                .setParameter("id", id)
                .executeUpdate();

        // Primeiro, deleta registros relacionados em 'Caso'
        em.createQuery("DELETE FROM Caso c WHERE c.cenario.id = :id")
                .setParameter("id", id)
                .executeUpdate();

        // Primeiro, deleta registros relacionados em 'Cenario'
        em.createQuery("DELETE FROM Cenario c WHERE c.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
