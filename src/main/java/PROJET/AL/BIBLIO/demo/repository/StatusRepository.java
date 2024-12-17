package PROJET.AL.BIBLIO.demo.repository;

import PROJET.AL.BIBLIO.demo.entity.EnumStatus;
import PROJET.AL.BIBLIO.demo.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/status")
@CrossOrigin(origins = "http://localhost:4200")
public interface StatusRepository extends JpaRepository<Status, Long> {
}
