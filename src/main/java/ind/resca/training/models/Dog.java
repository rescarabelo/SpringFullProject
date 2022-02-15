package ind.resca.training.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ind.resca.training.utils.enums.Breed;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "dog")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Enumerated(EnumType.STRING)
    private Breed breed;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public Dog(String name, Integer age, Owner owner, Breed breed) {
        this.name = name;
        this.age = age;
        this.owner = owner;
        this.breed = breed;
    }
}