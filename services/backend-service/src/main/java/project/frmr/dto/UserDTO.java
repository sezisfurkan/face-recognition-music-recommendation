package project.frmr.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {
    private String id;
  /*  private byte[] picture;*/

    @NotBlank(message = "Name can not be empty")
    @Size(min = 2, message = "Name must contain at least 2 characters.")
    private String name;

    @NotBlank(message = "First name can not be empty")
    @Size(min = 2, message = "First name must contain at least 2 characters.")
    private String fname;

    @NotBlank(message = "Surname can not be empty")
    @Size(min = 2, message = "Surname must contain at least 2 characters.")
    private String sname;

    @NotBlank(message = "Username can not be empty")
    @Size(min = 2, message = "Username must contain at least 2 characters.")
    private String username;

    @NotBlank(message = "Password can not be empty")
    @Size(min = 8, message = "Password must contain at least 8 characters.")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
            message = "Password must contain at least 1 capital letter and at least 1 symbol.")
    private String password;

    @Email(message = "Wrong email address format")
    private String email;
}
