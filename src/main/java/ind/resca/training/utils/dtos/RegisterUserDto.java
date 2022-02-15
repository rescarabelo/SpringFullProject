package ind.resca.training.utils.dtos;

import ind.resca.training.models.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Getter
@Setter
public class RegisterUserDto implements Serializable {
    @NotNull
    @NotEmpty
    private final String username;

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private final String password;

    @NotNull
    @NotEmpty
    private final String email;

    public User convert() {
        return new User(username, new BCryptPasswordEncoder().encode(password), email);
    }
}
