package PROJET.AL.BIBLIO.demo.apiModel;

import PROJET.AL.BIBLIO.demo.entity.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowModel {
    int userId;
    String userName;
    String userSurname;
    int livreId;
    String livreTitle;
    Status status;

}
