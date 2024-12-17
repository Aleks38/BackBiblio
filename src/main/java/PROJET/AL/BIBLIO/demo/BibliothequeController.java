package PROJET.AL.BIBLIO.demo;

import PROJET.AL.BIBLIO.demo.entity.Emprunt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BibliothequeController {

    @Autowired
    private UtilisateurFacade utilisateurFacade;

    @PostMapping("/emprunts/{id}/prolonger")
    public ResponseEntity<String> prolongerEmprunt(@PathVariable int id, @RequestParam int jours) {
        String result = utilisateurFacade.prolongerEmprunt(id, jours);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/livres/{id}/disponible")
    public ResponseEntity<Boolean> verifierDisponibiliteLivre(@PathVariable int id) {
        boolean disponible = utilisateurFacade.verifierDisponibiliteLivre(id);
        return ResponseEntity.ok(disponible);
    }

    @GetMapping("/emprunts/retards")
    public ResponseEntity<List<Emprunt>> getEmpruntsEnRetard() {
        List<Emprunt> empruntsEnRetard = utilisateurFacade.getEmpruntsEnRetard();
        return ResponseEntity.ok(empruntsEnRetard);
    }


    @PostMapping("/emprunts/return/{id}")
    public ResponseEntity<String> returnLivre(@PathVariable int id) {
        String message = utilisateurFacade.returnLivre(id);
        return ResponseEntity.ok(message);
    }

}

