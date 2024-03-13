package praktikum.model;

public class AuthorizationRequest {
    private final String email;
    private final String password;

    public AuthorizationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}