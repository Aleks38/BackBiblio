package PROJET.AL.BIBLIO.demo.repository;

import PROJET.AL.BIBLIO.demo.entity.Livre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/livres")
public class LivreController {
    @Autowired
    private LivreRepository livreRepository;

    @GetMapping
    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }


}
