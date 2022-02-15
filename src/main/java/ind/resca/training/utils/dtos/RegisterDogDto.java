package ind.resca.training.utils.dtos;

import ind.resca.training.models.Dog;
import ind.resca.training.models.Owner;
import ind.resca.training.utils.enums.Breed;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class RegisterDogDto implements Serializable {

    @NotNull
    @NotEmpty
    @Length(min = 3, max = 15)
    private final String name;

    @NotNull
    @Min(1)
    private final Integer age;

    @NotNull
    private final Breed breed;

    private final Owner owner;

    public Dog convert() {
        return new Dog(name, age, owner, breed);
    }
}
