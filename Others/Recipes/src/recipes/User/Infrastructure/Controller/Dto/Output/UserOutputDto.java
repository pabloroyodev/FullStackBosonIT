package recipes.User.Infrastructure.Controller.Dto.Output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @NoArgsConstructor @AllArgsConstructor
public class UserOutputDto implements Serializable {
    private String email;
    private String password;
}
