package project.frmr.dto;

public class LoginDTO {
    private String username;
    private String password;

    // Boş argümanlı kurucu metot
    public LoginDTO() {}

    // Kullanıcı adı ve şifre içeren kurucu metot
    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter ve setter metotları
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
