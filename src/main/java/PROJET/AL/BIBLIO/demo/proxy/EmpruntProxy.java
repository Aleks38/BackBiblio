package PROJET.AL.BIBLIO.demo.proxy;

import PROJET.AL.BIBLIO.demo.entity.UserType;
import org.springframework.stereotype.Service;

import PROJET.AL.BIBLIO.demo.entity.Emprunt;
import PROJET.AL.BIBLIO.demo.entity.Utilisateur;
import PROJET.AL.BIBLIO.demo.repository.EmpruntRepository;
import PROJET.AL.BIBLIO.demo.repository.UtilisateurRepository;

import java.time.LocalDate;

@Service
public class EmpruntProxy {

    private final EmpruntRepository empruntRepository;
    private final UtilisateurRepository userRepository;
    public EmpruntProxy(EmpruntRepository empruntRepository, UtilisateurRepository userRepository) {
        this.empruntRepository = empruntRepository;
        this.userRepository = userRepository;
    }



    public String borrowBook(int userId, int livreId) {
        // Fetch the user's role from the database
        Utilisateur user = userRepository.findById(userId);

        if (user == null) {
            return "Error: User not found.";
        }

        // Check if the user is a "Membre"
        if (!UserType.MEMBRE.equals(user.getRole().getNom())){
            return "Access denied: Only Membres can borrow books.";
        }

        // Create and save a new Emprunt
        Emprunt emprunt = new Emprunt();
        emprunt.setUserId(userId);
        emprunt.setLivreId(livreId);
        emprunt.setDateEmprunt(LocalDate.now().toString());
        emprunt.setDateRetour(null);

        empruntRepository.save(emprunt);

        return "Book " + livreId + " has been borrowed by user " + userId + ".";
    }
}
