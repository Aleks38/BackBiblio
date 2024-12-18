package PROJET.AL.BIBLIO.demo.repository;

import PROJET.AL.BIBLIO.demo.entity.Emprunt;
import PROJET.AL.BIBLIO.demo.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpruntService {

    @Autowired
    private EmpruntRepository empruntRepository;

    @Autowired
    private StatusRepository statusRepository;

    public List<Emprunt> getEmpruntsEnRetard() {
        LocalDate aujourdHui = LocalDate.now();

        return empruntRepository.findAll().stream()
                .filter(emprunt ->
                        LocalDate.parse(emprunt.getDateEmprunt()).isBefore(aujourdHui) &&
                                (emprunt.getStatus() == null || emprunt.getStatus().getId() != 3))
                .collect(Collectors.toList());
    }

    public String prolongerEmprunt(int idEmprunt, int joursSupplementaires) {
        Emprunt emprunt = empruntRepository.findById(idEmprunt)
                .orElseThrow(() -> new RuntimeException("Emprunt non trouvé"));

        Status status = emprunt.getStatus();
        if (status.getId() == 3) {
            throw new RuntimeException("L'emprunt est déjà retourné, il ne peut pas être prolongé.");
        }

        LocalDate nouvelleDateRetour = LocalDate.parse(emprunt.getDateRetour()).plusDays(joursSupplementaires);
        emprunt.setDateRetour(String.valueOf(nouvelleDateRetour));

        empruntRepository.save(emprunt);

        return "Emprunt prolongé jusqu'au " + nouvelleDateRetour.toString();
    }
}
