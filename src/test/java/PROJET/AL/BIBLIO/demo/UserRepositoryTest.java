package PROJET.AL.BIBLIO.demo;

import PROJET.AL.BIBLIO.demo.entity.Membre;
import PROJET.AL.BIBLIO.demo.entity.Role;
import PROJET.AL.BIBLIO.demo.entity.Utilisateur;
import PROJET.AL.BIBLIO.demo.entity.UtilisateurFactory;
import PROJET.AL.BIBLIO.demo.repository.MembreRepository;
import PROJET.AL.BIBLIO.demo.repository.UtilisateurRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private MembreRepository membreRepository;

    @Test
    public void testSaveUtilisateurMembre() {
        Utilisateur membre = UtilisateurFactory.CreateUtilisateur(
                "membre", "John", "Doe", "john.doe@example.com", "password", null
        );

        Membre savedMembre = membreRepository.save((Membre) membre);

        assertNotNull(savedMembre.getId());
        assertEquals("john.doe@example.com", savedMembre.getEmail());
        assertEquals("John", savedMembre.getNom());
        assertEquals("Doe", savedMembre.getPrenom());

        membreRepository.delete(savedMembre);
    }
    @Test
    public void testSaveUtilisateurMembreDirect() {
        Membre membre = new Membre( "John", "Doe", "john.doe@example.com", "password", null);

        Membre savedMembre = membreRepository.save(membre);

        assertNotNull(savedMembre.getId());
        assertEquals("john.doe@example.com", savedMembre.getEmail());

        membreRepository.delete(savedMembre);
    }


}
