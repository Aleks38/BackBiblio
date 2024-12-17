package PROJET.AL.BIBLIO.demo.login;

public class LoginResponse {
    private Integer id;
    private String message;

    public LoginResponse(Integer id, String message) {
        this.id = id;
        this.message = message;
    }

    // Getters et setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
