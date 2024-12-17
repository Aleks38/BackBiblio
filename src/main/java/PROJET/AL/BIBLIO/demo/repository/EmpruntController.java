package PROJET.AL.BIBLIO.demo.repository;

import PROJET.AL.BIBLIO.demo.proxy.EmpruntProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emprunt")
public class EmpruntController {
    @Autowired
    private  EmpruntProxy empruntProxy;


    @PostMapping("/borrow")
    public String borrowBook(@RequestParam int userId, @RequestParam int livreId) {
        System.out.println("THIS IS USER'S id" );

        return empruntProxy.borrowBook(userId, livreId);
    }}
