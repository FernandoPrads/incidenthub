package br.com.pradela.incidenthub.api;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final String SECRET = "minha-chave-super-secreta-123456789";

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String senha) {

        if (!"admin".equals(username) || !"admin123".equals(senha)) {
            throw new RuntimeException("Usuário ou senha inválidos");
        }

        SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(key)
                .compact();
    }
}