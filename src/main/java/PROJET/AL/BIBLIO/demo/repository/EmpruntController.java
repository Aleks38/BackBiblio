package PROJET.AL.BIBLIO.demo.repository;

import PROJET.AL.BIBLIO.demo.proxy.EmpruntProxy;
import PROJET.AL.BIBLIO.demo.proxy.EmpruntAnswer;
import PROJET.AL.BIBLIO.demo.entity.Emprunt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emprunt")
public class EmpruntController {
    private final EmpruntProxy empruntProxy;

    @Autowired
    public EmpruntController(EmpruntProxy empruntProxy) {
        this.empruntProxy = empruntProxy;
    }

    // Endpoint to borrow a book
    @PostMapping("/borrow")
    public EmpruntAnswer borrowBook(@RequestParam int userId, @RequestParam int livreId) {
        return empruntProxy.borrowBook(userId, livreId);
    }

    // Endpoint to accept a borrow request
    @PostMapping("/accept")
    public Emprunt acceptBorrowRequest(@RequestParam Integer empruntId) {
        return empruntProxy.acceptBorrowRequest(empruntId);
    }

    // Endpoint to reject a borrow request
    @PostMapping("/reject")
    public Boolean rejectBorrowRequest(@RequestParam Integer empruntId) {
        return empruntProxy.rejectBorrowRequest(empruntId);
    }
}
