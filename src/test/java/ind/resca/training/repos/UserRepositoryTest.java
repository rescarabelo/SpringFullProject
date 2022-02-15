package ind.resca.training.repos;

import ind.resca.training.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void ShouldFindUserByUsername() {
        String username = "default";

        Optional<User> userByUsername = userRepository.findByUsername(username);
        Optional<User> userById = userRepository.findById(UUID.fromString("092afba2-218b-4504-bf78-fd738ef62832"));

        assert userByUsername.isPresent();
        assert userById.isPresent();
        assert userById.equals(userByUsername);
    }
}