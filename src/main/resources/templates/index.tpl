yieldUnescaped '<!DOCTYPE html>'
html(lang:'en') {
    head {
        meta('http-equiv':'"Content-Type" content="text/html; charset=utf-8"')
        title("$applicationName")
        link(rel: "stylesheet", type: "text/css", href: "/css/style.css")
    }
    body {
        h1("$applicationName")
        p("Welcome, $username!")
        p {
            a(href: "logout", "logout")
        }
    }
}