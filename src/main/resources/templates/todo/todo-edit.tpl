import no.toreb.model.TodoModel

modelTypes = {
    TodoModel todo;
}

layout "layout.tpl", true, bodyContents: contents {
    h1(title)

    nav {
        a(href: "#", onclick: "goBack()", "Back")
    }

    if (!todo) {
        return;
    }

    br()
    br()
    div {
        form(id: "editForm", action: "$actionUrl", method: "post") {
            table(class: "centered", border: "0") {
                tr {
                    td("Name:")
                    td {
                        input(name: "name", type: "text", value: todo.name ?: '')
                    }
                }
                tr {
                    td("Description:")
                    td {
                        input(name: "description", type: "text", value: todo.description ?: '')
                    }
                }
                tr {
                    td("Completed:")
                    td {
                        final def checked = todo.completed ? "checked" : ""
                        yieldUnescaped '<input name="completed" type="checkbox" ' + checked + '/>'
                    }
                }
            }
            br()
            input(type: "submit", value: "Save")
        }
    }
}