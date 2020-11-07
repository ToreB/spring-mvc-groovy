package no.toreb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BuildProperties buildProperties;

    @GetMapping("/")
    public String index(final Model model, final Principal principal) {
        model.addAttribute("username", principal.getName());
        model.addAttribute("buildProperties", buildProperties);
        return "index";
    }
}
