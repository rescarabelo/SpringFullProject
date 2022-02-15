package ind.resca.training.config;

import ind.resca.training.models.User;
import ind.resca.training.repos.UserRepository;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@AllArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = getToken(request);
        boolean isValid = tokenService.isValid(jwt);

        if (isValid) {
            authenticate(jwt);
        }

        filterChain.doFilter(request, response);
    }

    private void authenticate(String jwt) {
        UUID userId = tokenService.getUserId(jwt);
        User user = userRepository.findById(userId).get();
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private String getToken(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization");
        if (jwt == null || jwt.isEmpty()) return null;
        return jwt;
    }
}
