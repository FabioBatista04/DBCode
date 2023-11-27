package br.edu.dombosco.dbcode.test.repository;

import br.edu.dombosco.dbcode.test.model.CasoDetalhado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class CasoDetalhadoDao {

    private EntityManager em;


    public CasoDetalhado findByIdAndProjectId(Long id, Long casoId) {
        return em.createQuery("SELECT c FROM CasoDetalhado c WHERE c.id = :id AND c.caso.id = :casoId", CasoDetalhado.class)
                .setParameter("id", id)
                .setParameter("casoId", casoId)
                .getSingleResult();
    }

    public List<CasoDetalhado> findByPassoLike(String passo, Long casoId) {
        log.info(">>>>> findByNomeLike: nome: {}, casoId: {}", passo, casoId);
        TypedQuery<CasoDetalhado> query = em.createQuery("SELECT c FROM CasoDetalhado c WHERE c.caso.id = :casoId AND c.passo LIKE :nome", CasoDetalhado.class);
        query.setParameter("nome", "%".concat(passo).concat("%"));
        query.setParameter("casoId", casoId);
        return query.setMaxResults(10).getResultList();


    }

    public List<CasoDetalhado> findAll(Long casoId) {
        TypedQuery<CasoDetalhado> query = em.createQuery("SELECT c FROM CasoDetalhado c WHERE c.caso.id = :casoId", CasoDetalhado.class);
        query.setParameter("casoId", casoId);
        return query.setMaxResults(10).getResultList();
    }

    public CasoDetalhado findById(Long id, Long casoId) {
        TypedQuery<CasoDetalhado> query = em.createQuery("SELECT c FROM CasoDetalhado c WHERE c.id = :id AND c.caso.id = :casoId", CasoDetalhado.class);
        query.setParameter("casoId", casoId);
        query.setParameter("id", id);
        return query.getSingleResult();

    }
}
