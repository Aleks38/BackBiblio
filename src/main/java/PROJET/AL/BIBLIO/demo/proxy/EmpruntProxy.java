package PROJET.AL.BIBLIO.demo.proxy;

import PROJET.AL.BIBLIO.demo.apiModel.BorrowModel;
import PROJET.AL.BIBLIO.demo.entity.Emprunt;
import PROJET.AL.BIBLIO.demo.entity.Livre;
import PROJET.AL.BIBLIO.demo.entity.UserType;
import PROJET.AL.BIBLIO.demo.entity.Utilisateur;
import PROJET.AL.BIBLIO.demo.repository.EmpruntRepository;
import PROJET.AL.BIBLIO.demo.repository.LivreRepository;
import PROJET.AL.BIBLIO.demo.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmpruntProxy {

    private final EmpruntRepository empruntRepository;
    private final UtilisateurRepository userRepository;
    private final LivreRepository livreRepository;

    public EmpruntProxy(EmpruntRepository empruntRepository, UtilisateurRepository userRepository, LivreRepository livreRepository) {
        this.empruntRepository = empruntRepository;
        this.userRepository = userRepository;
        this.livreRepository = livreRepository;
    }

    public BorrowModel borrowBook(int userId, int livreId) {
        // Fetch the user's role from the database
        Utilisateur user = userRepository.findById(userId);
        Livre livre = livreRepository.findById(livreId);

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
        emprunt.setStatus(null);

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

    public String acceptBorrowRequest(int empruntId) {
        // Fetch the Emprunt from the database
        Emprunt emprunt = empruntRepository.findById(empruntId);

        if (emprunt == null) {
            return "Error: Emprunt not found.";
        }

        // Check if the Emprunt is pending
        if (!"pending".equals(emprunt.getStatus())) {
            return "Error: Emprunt is not pending.";
        }

        // Update the Emprunt status to "accepted"
//        emprunt.setStatus();

        empruntRepository.save(emprunt);

        return "Borrow request " + empruntId + " has been accepted.";
    }

    public String rejectBorrowRequest(int empruntId) {
        // Fetch the Emprunt from the database
        Emprunt emprunt = empruntRepository.findById(empruntId);

        if (emprunt == null) {
            return "Error: Emprunt not found.";
        }

        // Check if the Emprunt is pending
        if (!"pending".equals(emprunt.getStatus())) {
            return "Error: Emprunt is not pending.";
        }

        // Update the Emprunt status to "rejected"
//        emprunt.setStatus(EnumStatus.);

        empruntRepository.save(emprunt);

        return "Borrow request " + empruntId + " has been rejected.";
    }
}
