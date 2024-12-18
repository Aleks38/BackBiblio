package PROJET.AL.BIBLIO.demo.proxy;

import PROJET.AL.BIBLIO.demo.entity.Emprunt;
import PROJET.AL.BIBLIO.demo.entity.Status;
import PROJET.AL.BIBLIO.demo.repository.EmpruntRepository;
import PROJET.AL.BIBLIO.demo.repository.LivreRepository;
import PROJET.AL.BIBLIO.demo.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class BorrowBookService implements IBorrowBookService  {
    private final EmpruntRepository empruntRepository;
    private final LivreRepository livreRepository;
    private final StatusRepository statusRepository;
@Autowired
    public BorrowBookService(EmpruntRepository empruntRepository, LivreRepository livreRepository, StatusRepository statusRepository) {
        this.empruntRepository = empruntRepository;
        this.livreRepository = livreRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public EmpruntAnswer borrowBook(int userId, int livreId) {
        Emprunt emprunt = new Emprunt();
        emprunt.setLivreId(livreId);
        emprunt.setUtilisateurId(userId);
        emprunt.setDateEmprunt(LocalDate.now().toString());

        Status status = new Status();
        status.setId(1);  // Set the ID for status
        emprunt.setStatus(status);

        empruntRepository.save(emprunt);
        return new EmpruntAnswer(emprunt, "Emprunt successfully created.");
    }
}
