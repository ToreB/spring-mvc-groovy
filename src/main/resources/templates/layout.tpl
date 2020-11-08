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
        bodyContents()
        hr()
        footer {
            p { yield "Application name: "; span(class: "boldText", "$buildProperties.name") }
            p { yield "Application version: "; span(class: "boldText", "$buildProperties.version") }
            p { yield "Build time: "; span(class: "boldText", "$buildProperties.time") }
        }
    }
}