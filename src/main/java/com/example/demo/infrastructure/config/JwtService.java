package com.example.demo.infrastructure.config;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service

public class JwtService {

    @Value("${jwt_secret}")
    private  String SECRET;

    public String gerarToken(String email, String perfil){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .claim("role", perfil)
                .setExpiration(new Date(System.currentTimeMillis() + 2100000))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public String validarToken(String token){
        return Jwts
                .parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String obterRole(String token){
       Claims claims = Jwts
               .parser()
               .setSigningKey(SECRET)
               .parseClaimsJws(token)
               .getBody();

       return claims.get("role", String.class);

    }


}
