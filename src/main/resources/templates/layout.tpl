import org.springframework.boot.info.BuildProperties

modelTypes = {
    BuildProperties buildProperties
}

yieldUnescaped '<!DOCTYPE html>'
html(lang:'en') {
    head {
        meta('http-equiv':'"Content-Type" content="text/html; charset=utf-8"')
        title(title)
        link(rel: "stylesheet", type: "text/css", href: "/css/style.css")
    }
    body {
        header {
            p(class: "headerItem") { span(class: "boldText", "$username") }
            p(class: "headerItem", "&nbsp;|&nbsp;")
            a(class: "headerItem", href: "logout", "Logout")
        }
        div(id: "contents") { bodyContents() }
        footer {
            p { yield "Application name: "; span(class: "boldText", "$buildProperties.name") }
            p { yield "Application version: "; span(class: "boldText", "$buildProperties.version") }
            p { yield "Build time: "; span(class: "boldText", "$buildProperties.time") }
        }
    }
}