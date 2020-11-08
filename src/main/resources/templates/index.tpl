layout "layout.tpl", true, title: "index", bodyContents: contents {
    h1(buildProperties.name)
    p("Welcome, $username!")
    a(href: "/todos", "Todo List")
}