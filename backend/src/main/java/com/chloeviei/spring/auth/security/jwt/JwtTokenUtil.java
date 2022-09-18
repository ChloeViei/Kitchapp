package com.chloeviei.spring.auth.security.jwt;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.chloeviei.spring.auth.models.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long EXPIRE_DURATION = 5 * 60 * 60 * 1000; // 24 hour
		
	@Value("${chloeviei.app.jwtSecret}")
	private String SECRET_KEY;
	
	public String generateAccessToken(User user) {
		return Jwts.builder()
				.setSubject(String.format("%s,%s", user.getId(), user.getEmail()))
				.setIssuer("ChloeViei")
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.compact();
				
	}
}
