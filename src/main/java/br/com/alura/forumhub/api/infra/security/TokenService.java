package br.com.alura.forumhub.api.infra.security;

import br.com.alura.forumhub.api.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}") // pega do application.properties
    private String secret;

    // Tempo de expiração (ex.: 2 horas)
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 2;

    /**
     * Gera um token JWT para o usuário logado.
     */
    public String gerarToken(Usuario usuario) {
        Date agora = new Date();
        Date expiracao = new Date(agora.getTime() + EXPIRATION_TIME);

        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setIssuer("ForumHub API")           // quem gerou o token
                .setSubject(usuario.getLogin())      // "dono" do token (usuário)
                .setIssuedAt(agora)                  // data de criação
                .setExpiration(expiracao)            // expiração
                .signWith(key, SignatureAlgorithm.HS256) // assinatura
                .compact();
    }

    /**
     * Extrai o login (subject) de dentro do token.
     */
    public String getSubject(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
