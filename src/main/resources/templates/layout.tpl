import org.springframework.boot.info.BuildProperties

modelTypes {
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
            p("Application name: $buildProperties.name")
            p("Application version: $buildProperties.version")
            p("Build time: $buildProperties.time")
        }
    }
}