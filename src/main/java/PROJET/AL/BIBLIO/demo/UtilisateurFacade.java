package PROJET.AL.BIBLIO.demo;

import PROJET.AL.BIBLIO.demo.entity.Emprunt;
import PROJET.AL.BIBLIO.demo.entity.Livre;
import PROJET.AL.BIBLIO.demo.entity.Status;
import PROJET.AL.BIBLIO.demo.entity.Utilisateur;
import PROJET.AL.BIBLIO.demo.repository.EmpruntRepository;
import PROJET.AL.BIBLIO.demo.repository.LivreRepository;
import PROJET.AL.BIBLIO.demo.repository.StatusRepository;
import PROJET.AL.BIBLIO.demo.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtilisateurFacade {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

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

    public String prolongerEmprunt(int idEmprunt, int joursSupplementaires) {
        Emprunt emprunt = empruntRepository.findById(idEmprunt)
                .orElseThrow(() -> new RuntimeException("Emprunt non trouvé"));

        if (emprunt.getDateRetour() != null) {
            throw new RuntimeException("L'emprunt est déjà retourné, il ne peut pas être prolongé.");
        }

        LocalDate nouvelleDateRetour = LocalDate.parse(emprunt.getDateEmprunt()).plusDays(joursSupplementaires);
        emprunt.setDateRetour(String.valueOf(nouvelleDateRetour));

        empruntRepository.save(emprunt);

        return "Emprunt prolongé jusqu'au " + nouvelleDateRetour.toString();
    }

    public List<Emprunt> getEmpruntsEnRetard() {
        LocalDate aujourdHui = LocalDate.now();

        return empruntRepository.findAll().stream()
                .filter(emprunt -> emprunt.getDateRetour() == null &&
                        LocalDate.parse(emprunt.getDateEmprunt()).isBefore(aujourdHui))
                .collect(Collectors.toList());
    }

    public String returnLivre(int idEmprunt) {
        Emprunt emprunt = empruntRepository.findById(idEmprunt)
                .orElseThrow(() -> new RuntimeException("Emprunt non trouvé"));

        if (emprunt.getDateRetour() != null) {
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
