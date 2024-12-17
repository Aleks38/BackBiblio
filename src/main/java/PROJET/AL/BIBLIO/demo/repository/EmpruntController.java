package PROJET.AL.BIBLIO.demo.repository;

import PROJET.AL.BIBLIO.demo.apiModel.BorrowModel;
import PROJET.AL.BIBLIO.demo.entity.Emprunt;
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
    public ResponseEntity<BorrowModel> borrowBook(@RequestParam Long userId, @RequestParam Long livreId) {
        BorrowModel newBorrowModel = empruntProxy.borrowBook(userId, livreId);
        return ResponseEntity.ok(newBorrowModel);
    }

    @PostMapping("/accept")
    public ResponseEntity<Emprunt> acceptBorrowRequest(@RequestParam Long empruntId) {
        Emprunt acceptBorrowRequest = empruntProxy.acceptBorrowRequest(empruntId);
        return ResponseEntity.ok(acceptBorrowRequest);
    }

    @PostMapping("/reject")
    public ResponseEntity<Boolean> rejectBorrowRequest(@RequestParam Long empruntId) {
        Boolean rejectBorrowRequest = empruntProxy.rejectBorrowRequest(empruntId);
        return ResponseEntity.ok(rejectBorrowRequest);
    }
}
