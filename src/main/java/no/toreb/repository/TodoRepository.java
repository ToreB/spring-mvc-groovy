package no.toreb.repository;

import no.toreb.domain.Todo;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TodoRepository extends CrudRepository<Todo, UUID> {

    @Query("select * from todo where owner = :owner order by id")
    List<Todo> findAllByOwner(String owner);
}
