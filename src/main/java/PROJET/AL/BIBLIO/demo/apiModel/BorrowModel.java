package PROJET.AL.BIBLIO.demo.apiModel;

import PROJET.AL.BIBLIO.demo.entity.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowModel {
    Long userId;
    String userName;
    String userSurname;
    Long livreId;
    String livreTitle;
    Status status;

}
