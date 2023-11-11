package br.edu.dombosco.dbcode.bugs.repository;

import br.edu.dombosco.dbcode.bugs.model.Bug;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BugRepository extends CrudRepository<Bug, Long> {
    Bug findBugById(Long id);
    List<Bug> findByStatus(String status);
}
