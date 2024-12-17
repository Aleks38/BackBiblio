package PROJET.AL.BIBLIO.demo.repository;

import PROJET.AL.BIBLIO.demo.entity.Bibliothecaire;
import PROJET.AL.BIBLIO.demo.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface BibliothecaireRepository extends JpaRepository<Bibliothecaire, Integer> {
}
