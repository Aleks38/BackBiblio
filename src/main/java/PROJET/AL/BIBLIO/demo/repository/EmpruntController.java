package PROJET.AL.BIBLIO.demo.repository;

import PROJET.AL.BIBLIO.demo.proxy.EmpruntProxy;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emprunt")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpruntController {

    private final EmpruntProxy empruntProxy;

    public EmpruntController(EmpruntProxy empruntProxy) {
        this.empruntProxy = empruntProxy;
    }

    @PostMapping("/borrow")
    public String borrowBook(@RequestParam int userId, @RequestParam int livreId) {
        return empruntProxy.borrowBook(userId, livreId);
    }
}
