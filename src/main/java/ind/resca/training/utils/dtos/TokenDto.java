package ind.resca.training.utils.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TokenDto {
    private String accessToken;
    private String authType;
}
