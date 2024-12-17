package PROJET.AL.BIBLIO.demo.proxy;

import PROJET.AL.BIBLIO.demo.entity.Status;
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



    public empruntAnswer borrowBook(int userId, int livreId) {
        // Fetch the user's role from the database

        Optional<Utilisateur> userOptional = Optional.ofNullable(userRepository.findById(userId));

        if (userOptional.isEmpty()) {
            return new empruntAnswer(null, "Error: User not found.");
        }
        Utilisateur user = userOptional.get();



        // Check if the user is a "Membre"
        if (!UserType.Utilisateur.equals(user.getRole().getNom())){
            return new empruntAnswer(null, "Access denied: Only Membres can borrow books.");
        }

        // Create and save a new Emprunt
        Emprunt emprunt = new Emprunt();

        emprunt.setLivreId(livreId);
        emprunt.setUtilisateurId(userId);
        emprunt.setDateEmprunt(LocalDate.now().toString());
        emprunt.setDateRetour(LocalDate.now().plusDays(15).toString());
        Status status = new Status();
        status.setId(1);  // Set the ID to 1 directly

        emprunt.setStatus(status);
        empruntRepository.save(emprunt);

        return new empruntAnswer(emprunt, "emprunt successfully.");

    }
}
