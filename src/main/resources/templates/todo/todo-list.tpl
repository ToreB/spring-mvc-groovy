import no.toreb.domain.Todo

modelTypes = {
    List<Todo> todos
}

final def title = "Todo List";
layout "layout.tpl", true, title: title, bodyContents: contents {
    h1(title)

    div {
        a(href: "/todos/add", "Add new Todo")
    }

    br()

    table(class: "centered", border: "1") {
        tr {
            th("Id")
            th("Name")
            th("Description")
            th("Completed")
            th("Created at")
            th("Updated at")
            th("Edit")
            th("Delete")
        }
        todos.each { final todo ->
            tr {
                td { a(href:"/todos/$todo.id", "$todo.id") }
                td(todo.name)
                td(todo.description)
                td(todo.completed)
                td(todo.createdAt)
                td(todo.updatedAt)
                td { a(href: "/todos/$todo.id/edit", "Edit") }
                td { a(href: "/todos/$todo.id/delete", onclick: "return confirmDelete('$todo.name')", "Delete") }
            }
        }
    }

}