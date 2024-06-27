package fr.evaluationjava.romaingilot.dao;

import fr.evaluationjava.romaingilot.model.ResponseUtilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseUtilisateurDao extends JpaRepository<ResponseUtilisateur, Integer> {

}
