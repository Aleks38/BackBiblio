package PROJET.AL.BIBLIO.demo.repository;

import PROJET.AL.BIBLIO.demo.entity.Emprunt;
import PROJET.AL.BIBLIO.demo.entity.Livre;
import PROJET.AL.BIBLIO.demo.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LivreService {
    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private EmpruntRepository empruntRepository;

    @Autowired
    private StatusRepository statusRepository;


    public boolean verifierDisponibiliteLivre(int idLivre) {
        Livre livre = livreRepository.findById(idLivre)
                .orElseThrow(() -> new RuntimeException("Livre non trouvé"));

        return livre.getExemplairesDisponibles() > 0;
    }

    public String returnLivre(int idEmprunt) {
        Emprunt emprunt = empruntRepository.findById(idEmprunt)
                .orElseThrow(() -> new RuntimeException("Emprunt non trouvé"));

        Status status = emprunt.getStatus();
        if (status.getId() == 3) {
            throw new RuntimeException("Ce livre a déjà été retourné.");
        }
        emprunt.setDateRetour(String.valueOf(LocalDate.now()));

        Status statusRetourne = statusRepository.findById(2)
                .orElseThrow(() -> new RuntimeException("Statut 'retourné' non trouvé"));
        emprunt.setStatus(statusRetourne);

        Livre livre = livreRepository.findById(emprunt.getLivreId())
                .orElseThrow(() -> new RuntimeException("Livre associé à l'emprunt non trouvé."));

        livre.setExemplairesDisponibles(livre.getExemplairesDisponibles() + 1);

        livreRepository.save(livre);
        empruntRepository.save(emprunt);

        return "Livre retourné avec succès et stock mis à jour.";
    }
}
