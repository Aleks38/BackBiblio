package PROJET.AL.BIBLIO.demo.repository;

import PROJET.AL.BIBLIO.demo.entity.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface LivreRepository extends JpaRepository<Livre, Long> {

    // Méthode pour rechercher les livres par titre (contient une chaîne de caractères)
    List<Livre> findByTitreContainingIgnoreCase(String titre);

    // Méthode pour rechercher les livres par auteur (exact)
    List<Livre> findByAuteurIgnoreCase(String auteur);

    // Méthode pour rechercher les livres par genre (exact)
    List<Livre> findByGenreIgnoreCase(String genre);
}
