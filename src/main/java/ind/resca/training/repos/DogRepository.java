package ind.resca.training.repos;

import ind.resca.training.models.Dog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DogRepository extends JpaRepository<Dog, UUID> {

    Page<Dog> findByOwnerId(UUID ownerId, Pageable pageable);

    Page<Dog> findById(UUID dogId, Pageable pageable);
}