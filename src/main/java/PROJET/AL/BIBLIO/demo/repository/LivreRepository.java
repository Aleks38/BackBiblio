package PROJET.AL.BIBLIO.demo.repository;

import PROJET.AL.BIBLIO.demo.entity.Livre;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LivreRepository extends JpaRepository<Livre, Long> {
}
