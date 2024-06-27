package fr.evaluationjava.romaingilot.controller;

import fr.evaluationjava.romaingilot.security.AppUserDetails;
import fr.evaluationjava.romaingilot.security.IsAdmin;
import fr.evaluationjava.romaingilot.security.IsUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "<h1>Hello anonyme</h1>";
    }

    @IsUser
    @GetMapping("/hello-user")
    public String helloUser(
            @AuthenticationPrincipal AppUserDetails userDetails) {
        return "<h1>Hello " + userDetails.getUtilisateur().getId() + "</h1>";
    }

    @IsAdmin
    @GetMapping("/hello-admin")
    public String helloAdmin() {
        return "<h1>Hello admin</h1>";
    }

}
