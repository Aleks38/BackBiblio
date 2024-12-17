package PROJET.AL.BIBLIO.demo.proxy;

import PROJET.AL.BIBLIO.demo.apiModel.BorrowModel;
import PROJET.AL.BIBLIO.demo.entity.*;
import PROJET.AL.BIBLIO.demo.repository.EmpruntRepository;
import PROJET.AL.BIBLIO.demo.repository.LivreRepository;
import PROJET.AL.BIBLIO.demo.repository.StatusRepository;
import PROJET.AL.BIBLIO.demo.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    public BorrowModel borrowBook(Long userId, Long livreId) {
        Utilisateur user = userRepository.findById(userId).orElse(null);
        Livre livre = livreRepository.findById(livreId).orElse(null);
        Status statusWaiting = statusRepository.findByNom(EnumStatus.Waiting).orElse(null);

        if (user == null) {
            throw new RuntimeException("User not found.");
        }

        // Check if the user is a "Membre"
        if (!UserType.Utilisateur.equals(user.getRole().getNom())) {
            throw new RuntimeException("User is not a member.");
        }

        // Create and save a new Emprunt
        Emprunt emprunt = new Emprunt();
        emprunt.setUtilisateurId(userId);
        emprunt.setLivreId(livreId);
        emprunt.setDateEmprunt(LocalDate.now().toString());
        emprunt.setDateRetour(null);
        emprunt.setStatus(statusWaiting);

        empruntRepository.save(emprunt);

        BorrowModel borrowModel = new BorrowModel();
        borrowModel.setUserId(userId);
        borrowModel.setUserName(user.getNom());
        borrowModel.setUserSurname(user.getPrenom());
        borrowModel.setLivreId(livreId);
        borrowModel.setLivreTitle(livre.getTitre());
        borrowModel.setStatus(emprunt.getStatus());

        return borrowModel;
    }

    public Emprunt acceptBorrowRequest(Long empruntId) {
        // Fetch the Emprunt from the database
        Emprunt emprunt = empruntRepository.findById(empruntId).orElse(null);
        Status statusAccepted = statusRepository.findByNom(EnumStatus.Accepted).orElse(null);

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

    public Boolean rejectBorrowRequest(Long empruntId) {
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

    public List<Emprunt> getEmpruntsByUserId(Long userId) {
        return empruntRepository.findByUtilisateurId(userId);
    }

}
