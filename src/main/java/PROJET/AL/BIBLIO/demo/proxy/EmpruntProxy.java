package PROJET.AL.BIBLIO.demo.proxy;

import PROJET.AL.BIBLIO.demo.entity.Status;
import PROJET.AL.BIBLIO.demo.entity.UserType;
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
    private final EmpruntRepository empruntRepository;
    @Autowired
    private final UtilisateurRepository userRepository;

    public EmpruntProxy(EmpruntRepository empruntRepository, UtilisateurRepository userRepository, UtilisateurRepository utilisateurRepository) {
        this.empruntRepository = empruntRepository;
        this.userRepository = userRepository;
    }


    public EmpruntAnswer borrowBook(int userId, int livreId) {
        Utilisateur userOptional = userRepository.findById(userId).orElse(null);


        if (!(userOptional instanceof Membre)) {
            return new EmpruntAnswer(null, "Access denied: Only Membres can borrow books.");
        }

        Emprunt emprunt = new Emprunt();

        emprunt.setLivreId(livreId);
        emprunt.setUtilisateurId(userId);
        emprunt.setDateEmprunt(LocalDate.now().toString());
        emprunt.setDateRetour(LocalDate.now().plusDays(15).toString());
        Status status = new Status();
        status.setId(1);  // Set the ID to 1 directly
        emprunt.setStatus(status);
        empruntRepository.save(emprunt);

        return new EmpruntAnswer(emprunt, "emprunt successfully.");

    }
}
