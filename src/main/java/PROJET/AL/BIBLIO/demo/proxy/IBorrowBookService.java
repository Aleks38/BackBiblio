package PROJET.AL.BIBLIO.demo.proxy;

public interface IBorrowBookService {
    EmpruntAnswer borrowBook(int userId, int livreId);
}
