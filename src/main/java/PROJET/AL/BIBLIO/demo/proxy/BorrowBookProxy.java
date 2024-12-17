package PROJET.AL.BIBLIO.demo.proxy;

import PROJET.AL.BIBLIO.demo.entity.Membre;
import PROJET.AL.BIBLIO.demo.entity.Utilisateur;
import PROJET.AL.BIBLIO.demo.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowBookProxy implements IBorrowBookService {

    private final BorrowBookService realService;
    private final UtilisateurRepository userRepository;
    @Autowired
    public BorrowBookProxy(BorrowBookService realService, UtilisateurRepository userRepository) {
        this.realService = realService;
        this.userRepository = userRepository;
    }

    @Override
    public EmpruntAnswer borrowBook(int userId, int livreId) {
        // Access Control: Only "Membre" users are allowed
        Utilisateur userOptional = userRepository.findById(userId).orElse(null);

        if (!(userOptional instanceof Membre)) {
            return new EmpruntAnswer(null, "Access Denied: Only Membres can borrow books.");
        }

        // Delegate to the real service
        return realService.borrowBook(userId, livreId);
    }
}
