package no.toreb.service;

import lombok.RequiredArgsConstructor;
import no.toreb.domain.Todo;
import no.toreb.repository.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    private final IdGenerator idGenerator;

    public List<Todo> getAll(final String owner) {
        return todoRepository.findAllByOwner(owner);
    }

    public Optional<Todo> get(final UUID id) {
        return todoRepository.findById(id);
    }

    public Todo save(final Todo todo) {
        return todoRepository.save(todo);
    }

    public void delete(final UUID todoId) {
        todoRepository.deleteById(todoId);
    }
}
