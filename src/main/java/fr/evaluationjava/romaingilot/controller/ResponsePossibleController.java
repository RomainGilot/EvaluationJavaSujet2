package fr.evaluationjava.romaingilot.controller;

import fr.evaluationjava.romaingilot.dao.ResponsePossibleDao;
import fr.evaluationjava.romaingilot.model.ResponsePossible;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import fr.evaluationjava.romaingilot.security.IsAdmin;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/response-possible")
public class ResponsePossibleController {

    @Autowired
    ResponsePossibleDao responsePossibleDao;

    @GetMapping("/liste")
    public List<ResponsePossible> liste() {
        return responsePossibleDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePossible> get(@PathVariable int id) {

        Optional<ResponsePossible> optionalQuestion = responsePossibleDao.findById(id);

        if(optionalQuestion.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalQuestion.get(),HttpStatus.OK);
    }

    @IsAdmin
    @PostMapping("")
    public ResponseEntity<ResponsePossible> add(@RequestBody @Valid ResponsePossible question) {
        question.setId(null);
        responsePossibleDao.save(question);
        return new ResponseEntity<>(question, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponsePossible> update(
            @PathVariable int id,
            @RequestBody @Valid ResponsePossible question) {
        question.setId(id);

        Optional<ResponsePossible> optionalQuizz = responsePossibleDao.findById(id);

        if(optionalQuizz.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        responsePossibleDao.save(question);

        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponsePossible> delete(@PathVariable int id) {

        Optional<ResponsePossible> optionalQuestion = responsePossibleDao.findById(id);

        if(optionalQuestion.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        responsePossibleDao.deleteById(id);

        return new ResponseEntity<>(optionalQuestion.get(), HttpStatus.OK);
    }

}