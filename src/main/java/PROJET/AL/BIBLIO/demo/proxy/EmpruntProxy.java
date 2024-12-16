package PROJET.AL.BIBLIO.demo.proxy;

import PROJET.AL.BIBLIO.demo.entity.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PROJET.AL.BIBLIO.demo.entity.Emprunt;
import PROJET.AL.BIBLIO.demo.entity.Utilisateur;
import PROJET.AL.BIBLIO.demo.repository.EmpruntRepository;
import PROJET.AL.BIBLIO.demo.repository.UtilisateurRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class EmpruntProxy {
    @Autowired
    private final EmpruntRepository empruntRepository;
    @Autowired
    private final UtilisateurRepository userRepository;

    public EmpruntProxy(EmpruntRepository empruntRepository, UtilisateurRepository userRepository, UtilisateurRepository utilisateurRepository) {
        this.empruntRepository = empruntRepository;
        this.userRepository = userRepository;
    }



    public String borrowBook(int userId, int livreId) {
        // Fetch the user's role from the database

        Optional<Utilisateur> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            return "Error: User not found.";
        }
        Utilisateur user = userOptional.get();



        // Check if the user is a "Membre"
        if (!UserType.Utilisateur.equals(user.getRole().getNom())){
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
