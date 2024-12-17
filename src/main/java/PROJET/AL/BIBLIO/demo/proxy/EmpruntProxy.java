package PROJET.AL.BIBLIO.demo.proxy;

import PROJET.AL.BIBLIO.demo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PROJET.AL.BIBLIO.demo.repository.EmpruntRepository;
import PROJET.AL.BIBLIO.demo.repository.UtilisateurRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class EmpruntProxy {
    @Autowired
    private  EmpruntRepository empruntRepository;
    @Autowired
    private  UtilisateurRepository userRepository;



    public String borrowBook(int userId, int livreId) {
        Utilisateur userOptional = userRepository.findById(userId).orElse(null);


// Vérifier si l'utilisateur est de type Membre
        if (!(userOptional instanceof Membre)) {
            return "Access denied: Only Membres can borrow books.";
        }

// Créer et sauvegarder un nouvel Emprunt
        Emprunt emprunt = new Emprunt();
        emprunt.setUserId(userId);
        emprunt.setLivreId(livreId);
        emprunt.setDateEmprunt(LocalDate.now().toString());
        emprunt.setDateRetour(null);

        empruntRepository.save(emprunt); // Sauvegarde dans le repository
        return "Emprunt successfully created.";

    }
}
