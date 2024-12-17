package PROJET.AL.BIBLIO.demo.repository;

import PROJET.AL.BIBLIO.demo.login.LoginResponse;
import PROJET.AL.BIBLIO.demo.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "*") // Autorise tous les domaines à appeler l'API
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<LoginResponse> loginUser(@RequestParam String email, @RequestParam String password) {
        LoginResponse response = loginService.verifyUser(email, password);

        if (response.getId() != null) {
            return ResponseEntity.ok(response); // 200 OK
        } else {
            return ResponseEntity.status(401).body(response); // 401 Unauthorized
        }
    }
}
