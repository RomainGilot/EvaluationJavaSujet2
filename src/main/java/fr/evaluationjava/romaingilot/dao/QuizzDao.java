package fr.evaluationjava.romaingilot.dao;

import fr.evaluationjava.romaingilot.model.Quizz;
import fr.evaluationjava.romaingilot.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizzDao extends JpaRepository<Quizz, Integer> {

}
