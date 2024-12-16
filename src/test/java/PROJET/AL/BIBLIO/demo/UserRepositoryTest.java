package PROJET.AL.BIBLIO.demo;

import PROJET.AL.BIBLIO.demo.entity.Utilisateur;
import PROJET.AL.BIBLIO.demo.entity.UtilisateurFactory;
import PROJET.AL.BIBLIO.demo.repository.UtilisateurRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Test
    public void testSaveUtilisateurMembre() {
        Utilisateur membre = UtilisateurFactory.CreateUtilisateur(
                "membre", "John", "Doe", "john.doe@example.com", "password", null
        );

        Utilisateur savedUtilisateur = utilisateurRepository.save(membre);

        assertNotNull(savedUtilisateur.getId());
        assertEquals("john.doe@example.com", savedUtilisateur.getEmail());
        assertEquals("John", savedUtilisateur.getNom());
        assertEquals("Doe", savedUtilisateur.getPrenom());

        utilisateurRepository.delete(savedUtilisateur);
    }
}
