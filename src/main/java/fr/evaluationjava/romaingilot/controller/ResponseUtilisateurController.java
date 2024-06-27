package fr.evaluationjava.romaingilot.controller;

import fr.evaluationjava.romaingilot.dao.ResponseUtilisateurDao;
import fr.evaluationjava.romaingilot.model.ResponseUtilisateur;
import fr.evaluationjava.romaingilot.security.AppUserDetails;
import fr.evaluationjava.romaingilot.security.IsUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/response-utilisateur")
public class ResponseUtilisateurController {

    @Autowired
    ResponseUtilisateurDao responseUtilisateurDao;

    @GetMapping("/liste")
    public List<ResponseUtilisateur> liste() {
        return responseUtilisateurDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUtilisateur> get(@PathVariable int id) {

        Optional<ResponseUtilisateur> optionalQuestion = responseUtilisateurDao.findById(id);

        if(optionalQuestion.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalQuestion.get(),HttpStatus.OK);
    }

    @IsUser
    @PostMapping("")
    public ResponseEntity<ResponseUtilisateur> add(@RequestBody @Valid ResponseUtilisateur responseUtilisateur, @AuthenticationPrincipal AppUserDetails userDetails) {
        responseUtilisateur.setId(null);
        responseUtilisateur.setUtilisateur(userDetails.getUtilisateur());

        responseUtilisateurDao.save(responseUtilisateur);
        return new ResponseEntity<>(responseUtilisateur, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseUtilisateur> update(
            @PathVariable int id,
            @RequestBody @Valid ResponseUtilisateur question) {
        question.setId(id);

        Optional<ResponseUtilisateur> optionalQuizz = responseUtilisateurDao.findById(id);

        if(optionalQuizz.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        responseUtilisateurDao.save(question);

        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseUtilisateur> delete(@PathVariable int id) {

        Optional<ResponseUtilisateur> optionalQuestion = responseUtilisateurDao.findById(id);

        if(optionalQuestion.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        responseUtilisateurDao.deleteById(id);

        return new ResponseEntity<>(optionalQuestion.get(), HttpStatus.OK);
    }

}