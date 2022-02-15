package ind.resca.training.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "owner")
@Getter
@Setter
@NoArgsConstructor
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "cpf", nullable = false, length = 14)
    private String cpf;

    @Column(name = "name", nullable = false)
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "owner", targetEntity = Dog.class, cascade = CascadeType.ALL)
    private Set<Dog> dogs;

    public Owner(String cpf, String name) {
        this.cpf = cpf;
        this.name = name;
    }
}