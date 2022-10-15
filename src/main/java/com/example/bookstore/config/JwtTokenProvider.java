package com.example.bookstore.config;

import com.example.bookstore.domain.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;

public class JwtTokenProvider implements Serializable {

    private static final long serialVersionUID=2569800841756370596L;

    private  String secretKey="java1";

    protected  void init(){
        secretKey= Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    private final long validityInMilliseconds =10*60*60;

    public  String createToken(String username, Role role){
        Claims claims= Jwts.claims().setSubject(username);
        claims.put("auth", role);

        Date now=new  Date();
        return Jwts.builder().setClaims(claims).setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+validityInMilliseconds))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    @Autowired
    private UserDetailsService userDetailsService;
    public boolean validateToken(String token) {
        Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return  true;
    }

    public Authentication getAuthentication(String token) {
        String username=Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        UserDetails userDetails=userDetailsService.loadUserByUsername(username);
        return  new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
