package no.toreb.controller;

import no.toreb.model.TodoModel;
import no.toreb.service.TodoService;
import org.springframework.boot.info.BuildProperties;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.SessionScope;

import java.util.UUID;
import java.util.function.Supplier;


@Controller
@RequestMapping("/todos")
public class TodoController extends BaseController {

    @Component
    @SessionScope
    static class SessionState {

        String errorMessage;
    }

    private final TodoService todoService;

    private final SessionState sessionState;

    public TodoController(final Supplier<SecurityContext> securityContextSupplier,
                          final BuildProperties buildProperties,
                          final TodoService todoService,
                          final SessionState sessionState) {
        super(securityContextSupplier, buildProperties);
        this.todoService = todoService;
        this.sessionState = sessionState;
    }

    @GetMapping
    public String getAll(final Model model) {
        consumeErrorMessage(model);

        final var todos = todoService.getAll(username());

        model.addAttribute("todos", todos);

        return "todo/todo-list";
    }

    @GetMapping("/{todoId}")
    public String get(final Model model, @PathVariable("todoId") final UUID todoId) {
        final var optTodo = todoService.get(todoId);
        if (optTodo.isEmpty()) {
            return setErrorAndRedirectToRootView("Todo with id " + todoId + " does not exist.");
        }

        final var todo = optTodo.get();
        model.addAttribute("todo", todo);

        return "todo/todo-show";
    }

    @GetMapping("/add")
    public String showCreate(final Model model) {
        model.addAttribute("title", "Add New Todo");
        model.addAttribute("todo", new TodoModel());
        model.addAttribute("actionUrl", "/todos/add");

        return "todo/todo-edit";
    }

    @PostMapping("/add")
    public String create(final Model model, @ModelAttribute("todo") final TodoModel todoModel) {
        todoService.save(todoModel.toTodo());

        return "redirect:/todos";
    }

    @GetMapping("/{todoId}/edit")
    public String showUpdate(final Model model, @PathVariable("todoId") final UUID todoId) {
        model.addAttribute("title", "Edit Todo");

        final var optTodo = todoService.get(todoId);
        if (optTodo.isEmpty()) {
            return setErrorAndRedirectToRootView("Todo with id " + todoId + " does not exist.");
        }

        final var todo = optTodo.get();
        model.addAttribute("todo", TodoModel.of(todo));
        model.addAttribute("actionUrl", "/todos/" + todo.getId() + "/edit");

        return "todo/todo-edit";
    }

    @PostMapping("/{todoId}/edit")
    public String update(final Model model,
                         @PathVariable("todoId") final UUID todoId,
                         @ModelAttribute("todo") final TodoModel todoModel) {
        final var optTodo = todoService.get(todoId);
        if (optTodo.isEmpty()) {
            return setErrorAndRedirectToRootView("Todo with id " + todoId + " does not exist.");
        }

        final var updatedTodo = optTodo.get()
                                       .toBuilder()
                                       .name(todoModel.getName())
                                       .description(todoModel.getDescription())
                                       .completed(todoModel.isCompleted())
                                       .build();
        todoService.save(updatedTodo);

        return "redirect:/todos";
    }

    @GetMapping("/{todoId}/delete")
    public String delete(final Model model, @PathVariable("todoId") final UUID todoId) {
        final var optTodo = todoService.get(todoId);
        if (optTodo.isEmpty()) {
            return setErrorAndRedirectToRootView("Todo with id " + todoId + " does not exist.");
        }

        todoService.delete(todoId);
        return "redirect:/todos";
    }

    private String setErrorAndRedirectToRootView(final String errorMessage) {
        sessionState.errorMessage = errorMessage;
        return "redirect:/todos";
    }

    private void consumeErrorMessage(final Model model) {
        model.addAttribute("errorMessage", sessionState.errorMessage);
        sessionState.errorMessage = null;
    }
}
