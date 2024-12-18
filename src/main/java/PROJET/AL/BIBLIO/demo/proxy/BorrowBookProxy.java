package PROJET.AL.BIBLIO.demo.proxy;

import PROJET.AL.BIBLIO.demo.entity.Livre;
import PROJET.AL.BIBLIO.demo.entity.Membre;
import PROJET.AL.BIBLIO.demo.entity.Utilisateur;
import PROJET.AL.BIBLIO.demo.repository.LivreRepository;
import PROJET.AL.BIBLIO.demo.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowBookProxy implements IBorrowBookService {

    private final BorrowBookService realService;
    private final UtilisateurRepository userRepository;
    private final LivreRepository livreRepository;
    @Autowired
    public BorrowBookProxy(BorrowBookService realService, UtilisateurRepository userRepository, LivreRepository livreRepository) {
        this.realService = realService;
        this.userRepository = userRepository;
        this.livreRepository = livreRepository;
    }

    @Override
    public EmpruntAnswer borrowBook(int userId, int livreId) {
        // Access Control: Only "Membre" users are allowed
        Utilisateur userOptional = userRepository.findById(userId).orElse(null);

        if (!(userOptional instanceof Membre)) {
            return new EmpruntAnswer(null, "Access Denied: Only Membres can borrow books.");
        }

        // Find the book by livreId
        Livre livre = livreRepository.findById(livreId).orElse(null);
        if (livre == null) {
            return new EmpruntAnswer(null, "Book not found.");
        }

        // Decrement the quantity
        if (livre.getExemplairesDisponibles() > 0) {
            livre.setExemplairesDisponibles(livre.getExemplairesDisponibles() - 1);
            livreRepository.save(livre);
        } else {
            return new EmpruntAnswer(null, "No more copies available.");
        }

        // Delegate to the real service
        return realService.borrowBook(userId, livreId);
    }
}
