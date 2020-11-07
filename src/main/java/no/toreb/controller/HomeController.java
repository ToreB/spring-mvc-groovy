package no.toreb.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    private final String applicationName;

    public HomeController(@Value("${spring.application.name}") final String applicationName) {
        this.applicationName = applicationName;
    }

    @GetMapping("/")
    public String index(final Model model, final Principal principal) {
        model.addAttribute("applicationName", applicationName);
        model.addAttribute("username", principal.getName());
        return "index";
    }
}
