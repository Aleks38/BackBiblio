package PROJET.AL.BIBLIO.demo.repository;

import PROJET.AL.BIBLIO.demo.proxy.BorrowBookProxy;
import PROJET.AL.BIBLIO.demo.proxy.EmpruntProxy;
import PROJET.AL.BIBLIO.demo.proxy.EmpruntAnswer;
import PROJET.AL.BIBLIO.demo.entity.Emprunt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emprunt")
public class EmpruntController {
    private final EmpruntProxy empruntProxy;
    private final BorrowBookProxy borrowBookProxy;

    @Autowired
    public EmpruntController(EmpruntProxy empruntProxy, BorrowBookProxy borrowBookProxy) {
        this.empruntProxy = empruntProxy;
        this.borrowBookProxy = borrowBookProxy;
    }

    // Endpoint to borrow a book
    @PostMapping("/borrow")
    public EmpruntAnswer borrowBook(@RequestParam int userId, @RequestParam int livreId) {
        return borrowBookProxy.borrowBook(userId, livreId);  // Corrected
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

    // Endpoint to return a borrowed book
    @PostMapping("/return")
    public Emprunt returnBorrowedBook(@RequestParam Integer empruntId) {
        return empruntProxy.returnBorrowedBook(empruntId);
    }
}
