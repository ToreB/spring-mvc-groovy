package no.toreb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import no.toreb.domain.Todo;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoModel {

    UUID id;

    String name;

    String description;

    boolean completed;

    String owner;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    public Todo toTodo() {
        return Todo.builder()
                   .id(id)
                   .name(name)
                   .description(description)
                   .completed(completed)
                   .owner(owner)
                   .createdAt(createdAt)
                   .updatedAt(updatedAt)
                   .build();
    }

    public static TodoModel of(final Todo todo) {
        return new TodoModel(todo.getId(),
                             todo.getName(),
                             todo.getDescription(),
                             todo.isCompleted(),
                             todo.getOwner(),
                             todo.getCreatedAt(),
                             todo.getUpdatedAt());
    }
}
