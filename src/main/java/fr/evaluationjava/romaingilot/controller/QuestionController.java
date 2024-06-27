package fr.evaluationjava.romaingilot.controller;

import fr.evaluationjava.romaingilot.dao.QuestionDao;
import fr.evaluationjava.romaingilot.model.Question;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionDao questionDao;

    @GetMapping("/liste")
    public List<Question> liste() {
        return questionDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> get(@PathVariable int id) {

        Optional<Question> optionalQuestion = questionDao.findById(id);

        if(optionalQuestion.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalQuestion.get(),HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Question> add(@RequestBody @Valid Question question) {
        question.setId(null);
        questionDao.save(question);
        return new ResponseEntity<>(question, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> update(
            @PathVariable int id,
            @RequestBody @Valid Question utilisateur) {
        utilisateur.setId(id);

        Optional<Question> optionalQuestion = questionDao.findById(id);

        if(optionalQuestion.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        questionDao.save(utilisateur);

        return new ResponseEntity<>(utilisateur, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Question> delete(@PathVariable int id) {

        Optional<Question> optionalQuestion = questionDao.findById(id);

        if(optionalQuestion.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        questionDao.deleteById(id);

        return new ResponseEntity<>(optionalQuestion.get(), HttpStatus.OK);
    }

}
