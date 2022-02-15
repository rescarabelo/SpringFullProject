package ind.resca.training.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Profile implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    @Type(type = "uuid-char")
    private UUID id;

    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
