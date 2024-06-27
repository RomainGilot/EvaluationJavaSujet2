package fr.evaluationjava.romaingilot.dao;

import fr.evaluationjava.romaingilot.model.ResponsePossible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsePossibleDao extends JpaRepository<ResponsePossible, Integer> {

}
