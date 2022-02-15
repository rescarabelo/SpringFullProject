package ind.resca.training.repos;

import ind.resca.training.models.Dog;
import ind.resca.training.models.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@DataJpaTest
@ActiveProfiles("test")
class DogRepositoryTest {

    @Autowired
    private DogRepository repository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    void shouldFindDogsByOwnerId() {
        UUID ownerId = UUID.fromString("1784fe90-be61-4162-8298-6454b0f66978");

        Page<Dog> dogsPage = repository.findByOwnerId(ownerId, null);
        Set<Dog> dogsSet = convertDogPageToSet(dogsPage);

        Owner owner1 = ownerRepository.findById(ownerId, null).getContent().get(0);

        Assertions.assertNotNull(dogsPage);
        Assertions.assertEquals(dogsSet, owner1.getDogs());
    }

    private Set<Dog> convertDogPageToSet(Page<Dog> page) {
        List<Dog> list = page.getContent();
        return new HashSet<>(list);
    }
}