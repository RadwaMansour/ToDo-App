package com.example.springjwt.auth.services;

import com.example.springjwt.auth.entities.User;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class JwtService {


    private final String secret_key = "BF7FD11ACE545745B7BA1AF98B6F156D127BC7BB544BAB6A4FD74E4FC7";

    private final long accessTokenValidity = 30*60*1000;  //valid till 30 minutes

    private final JwtParser jwtParser;
    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";
    public JwtService()
    {
        this.jwtParser = Jwts.parser().setSigningKey(secret_key);
    }

    public String createToken(User user , Map<String , Object> extraClaims) {


        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getEmail())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenValidity))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .compact();
    }

    private Claims parseJwtClaims(String token)
    {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public String resolveToken(HttpServletRequest request)
    {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    public Claims resolveClaims(HttpServletRequest req)
    {
        try {
            String token = resolveToken(req);
            if (token != null) {
                return parseJwtClaims(token);
            }
            return null;
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            req.setAttribute("invalid", ex.getMessage());
            throw ex;
        }
    }


    public boolean isTokenExpired(Date expirationDate) throws AuthenticationException
    {
        try {
            if(expirationDate.before(new Date()))
                return true;
            else
                return false;
        } catch (Exception e) {
            throw e;
        }
    }


    public boolean isTokenValid(String accessToken , UserDetails userDetails) {
        String username = userDetails.getUsername();
        Claims claims = parseJwtClaims(accessToken);
        return username.equals(claims.getSubject()) && !isTokenExpired(claims.getExpiration());
    }
    public String extractToken(String token){
        return token.substring(TOKEN_PREFIX.length());
    }
    public Claims getUserInfo(String token){
        String bearerToken = extractToken(token);
        System.out.println("BearerToken in jwtService:  "+bearerToken);
        Claims claims = parseJwtClaims(bearerToken);
        return claims;
    }
    private List<String> getRoles(Claims claims) {
        return (List<String>) claims.get("roles");
    }

}
