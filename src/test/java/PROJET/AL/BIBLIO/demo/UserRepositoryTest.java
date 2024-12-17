package PROJET.AL.BIBLIO.demo;

import PROJET.AL.BIBLIO.demo.entity.Utilisateur;
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
        Utilisateur membre = utilisateurRepository.findById(1).orElse(null);

        Utilisateur savedUtilisateur = utilisateurRepository.save(membre);

        assertNotNull(savedUtilisateur.getId());
        assertEquals("pierre.dupont@example.com", savedUtilisateur.getEmail());
        assertEquals("Dupont", savedUtilisateur.getNom());
        assertEquals("Pierre", savedUtilisateur.getPrenom());
    }
}
