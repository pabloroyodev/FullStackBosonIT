package recipes.User.Infrastructure.Controller.Dto.Input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data @NoArgsConstructor @AllArgsConstructor
public class
UserInputDto implements Serializable {

    @Email(regexp = ".+[@].+[\\.].+") @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Length(min = 8)
    private String password;
}
