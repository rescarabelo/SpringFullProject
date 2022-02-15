package ind.resca.training.repos;

import ind.resca.training.models.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OwnerRepository extends JpaRepository<Owner, UUID> {
    Page<Owner> findById(UUID ownerId, Pageable pageable);
}