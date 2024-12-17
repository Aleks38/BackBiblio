package PROJET.AL.BIBLIO.demo.proxy;

import PROJET.AL.BIBLIO.demo.entity.*;
import PROJET.AL.BIBLIO.demo.repository.EmpruntRepository;
import PROJET.AL.BIBLIO.demo.repository.LivreRepository;
import PROJET.AL.BIBLIO.demo.repository.StatusRepository;
import PROJET.AL.BIBLIO.demo.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class EmpruntProxy {
    private final EmpruntRepository empruntRepository;
    private final UtilisateurRepository userRepository;
    private final LivreRepository livreRepository;
    private final StatusRepository statusRepository;

    @Autowired
    public EmpruntProxy(EmpruntRepository empruntRepository, UtilisateurRepository userRepository, LivreRepository livreRepository, StatusRepository statusRepository) {
        this.empruntRepository = empruntRepository;
        this.userRepository = userRepository;
        this.livreRepository = livreRepository;
        this.statusRepository = statusRepository;
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
    public Emprunt acceptBorrowRequest(Integer empruntId) {
        // Fetch the Emprunt from the database
        Emprunt emprunt = empruntRepository.findById(empruntId).orElse(null);
        Status statusAccepted = (Status) statusRepository.findByNom(EnumStatus.Accepted).orElse(null);

        if (emprunt == null) {
            throw new RuntimeException("Emprunt not found.");
        }

        // Fetch the Livre associated with the Emprunt
        Livre livre = livreRepository.findById(emprunt.getLivreId()).orElse(null);

        if (livre == null) {
            throw new RuntimeException("Livre not found.");
        }

        // Check if there are available copies of the book
        if (livre.getExemplairesDisponibles() <= 0) {
            throw new RuntimeException("No available copies of the book.");
        }

        // Decrement the number of available copies
        livre.setExemplairesDisponibles(livre.getExemplairesDisponibles() - 1);
        livreRepository.save(livre);

        // Update the Emprunt status to "accepted"
        emprunt.setStatus(statusAccepted);
        empruntRepository.save(emprunt);

        return emprunt;
    }

    public Boolean rejectBorrowRequest(Integer empruntId) {
        // Fetch the Emprunt from the database
        Emprunt emprunt = empruntRepository.findById(empruntId).orElse(null);

        if (emprunt == null) {
            throw new RuntimeException("Emprunt not found.");
        }

        // Fetch the Livre associated with the Emprunt
        Livre livre = livreRepository.findById(emprunt.getLivreId()).orElse(null);

        if (livre == null) {
            throw new RuntimeException("Livre not found.");
        }

        // Increment the number of available copies
        livre.setExemplairesDisponibles(livre.getExemplairesDisponibles() + 1);
        livreRepository.save(livre);

        // Delete the Emprunt
        empruntRepository.delete(emprunt);

        return true;
    }

    public Optional<Emprunt> getEmpruntsByUserId(Integer userId) {
        return empruntRepository.findById(userId);
    }

}
