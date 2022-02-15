package ind.resca.training.utils.dtos;

import ind.resca.training.models.Owner;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class RegisterOwnerDto implements Serializable {
    @NotNull
    @NotEmpty
    @Length(min = 14, max = 14)
    private final String cpf;

    @NotNull
    @NotEmpty
    private final String name;

    public Owner convert() {
        return new Owner(cpf, name);
    }
}
