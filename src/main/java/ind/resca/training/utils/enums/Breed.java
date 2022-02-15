package ind.resca.training.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Table;

@Getter
@AllArgsConstructor
@Table(name = "breed")
public enum Breed {
    BEAGLE("Beagle"),
    SPANIEL("Cavalier King Charles Spaniel"),
    RETRIEVER("Labrador Retriever"),
    BERNARD("Saint Bernard"),
    DACHSHUND("Dachshund");

    private String name;
}
