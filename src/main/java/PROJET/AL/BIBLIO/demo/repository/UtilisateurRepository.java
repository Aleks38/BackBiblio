package PROJET.AL.BIBLIO.demo.repository;

import PROJET.AL.BIBLIO.demo.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/utilisateurs")
@CrossOrigin(origins = "http://localhost:4200")
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    public Optional<Utilisateur> findById(Long id);
}
