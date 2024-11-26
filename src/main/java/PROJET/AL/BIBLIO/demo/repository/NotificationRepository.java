package PROJET.AL.BIBLIO.demo.repository;

import PROJET.AL.BIBLIO.demo.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
