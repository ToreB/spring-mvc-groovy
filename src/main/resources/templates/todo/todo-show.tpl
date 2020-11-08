import no.toreb.domain.Todo

modelTypes = {
    Todo todo;
}

layout "layout.tpl", true, title: todo.name, bodyContents: contents {
    h1("$todo.name Details")

    nav {
        a(href: "#", onclick: "goBack()", "Back")
        span("&nbsp;|&nbsp;")
        a(href: "/todos/$todo.id/edit", "Edit")
        span("&nbsp;|&nbsp;")
        a(href: "/todos/$todo.id/delete", onclick: "return confirmDelete('$todo.name')", "Delete")
    }

    br()
    div {
        table(class: "centered", border: "0") {
            tr {
                td("id:")
                td(todo.id)
            }
            tr {
                td("Name:")
                td(todo.name)
            }
            tr {
                td("Description:")
                td(todo.description)
            }
            tr {
                td("Completed:")
                td(todo.completed)
            }
            tr {
                td("Created at:")
                td(todo.createdAt)
            }
            tr {
                td("Updated at:")
                td(todo.updatedAt)
            }
        }
    }
}