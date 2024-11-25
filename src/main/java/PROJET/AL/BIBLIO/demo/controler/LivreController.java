package PROJET.AL.BIBLIO.demo.controler;

import PROJET.AL.BIBLIO.demo.entity.Livre;
import PROJET.AL.BIBLIO.demo.repository.LivreRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/livres")
public class LivreController {

    private final LivreRepository livreRepository;

    public LivreController(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    @GetMapping
    @Operation(summary = "Liste des livres", description = "Récupère tous les livres disponibles dans la bibliothèque.")
    public ResponseEntity<List<Livre>> getAllLivres() {
        List<Livre> livres = livreRepository.findAll();
        return ResponseEntity.ok(livres);
    }
}
