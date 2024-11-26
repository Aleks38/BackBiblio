package PROJET.AL.BIBLIO.demo.repository;

import PROJET.AL.BIBLIO.demo.entity.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

}
