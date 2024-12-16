package PROJET.AL.BIBLIO.demo.repository;

import PROJET.AL.BIBLIO.demo.apiModel.BorrowModel;
import PROJET.AL.BIBLIO.demo.proxy.EmpruntProxy;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BorrowModel> borrowBook(@RequestParam int userId, @RequestParam int livreId) {
        BorrowModel newBorrowModel = empruntProxy.borrowBook(userId, livreId);
        return ResponseEntity.ok(newBorrowModel);
    }

    @PostMapping("/accept")
    public String acceptBorrowRequest(@RequestParam int empruntId) {
        return empruntProxy.acceptBorrowRequest(empruntId);
    }

    @PostMapping("/reject")
    public String rejectBorrowRequest(@RequestParam int empruntId) {
        return empruntProxy.rejectBorrowRequest(empruntId);
    }
}
