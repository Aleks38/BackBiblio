package PROJET.AL.BIBLIO.demo.repository;

import PROJET.AL.BIBLIO.demo.entity.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emprunts")
@CrossOrigin(origins = "http://localhost:4200")
public interface EmpruntRepository extends JpaRepository<Emprunt, Integer> {
    Optional<Emprunt> findById(Integer id);
    List<Emprunt> findByUtilisateurId(Integer utilisateurId);

}
