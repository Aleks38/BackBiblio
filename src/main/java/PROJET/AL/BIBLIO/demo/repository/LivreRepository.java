package PROJET.AL.BIBLIO.demo.repository;

import PROJET.AL.BIBLIO.demo.entity.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/livres")
@CrossOrigin(origins = "http://localhost:4200")
public interface LivreRepository extends JpaRepository<Livre, Long> {
    Optional<Livre> findById(Long id);
}
