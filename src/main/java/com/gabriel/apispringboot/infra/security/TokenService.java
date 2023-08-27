package com.gabriel.apispringboot.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.gabriel.apispringboot.entities.User;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String secret;
	
	public  String generateToken(User user)
	{
		
			try {
			
				Algorithm algo = Algorithm.HMAC256(secret);
				String token = JWT.create().withIssuer("api-spring-boot")
						.withSubject(user.getEmail())
						.withExpiresAt(generateExpirationDate())
						.sign(algo);
				
				return token;
			}catch(JWTCreationException e)
			{
				throw new RuntimeException("error generating exception",e);
			}
	
    }
	
	//it will return the subject (email)
	public String validateToken(String token)
	{
		try {
			
			Algorithm algo = Algorithm.HMAC256(secret);
			return JWT.require(algo)
					.withIssuer("api-spring-boot")
					.build()
					.verify(token)
					.getSubject();
		}catch(JWTVerificationException e)
		{
			return "";
		}
	}
	
	
	private Instant generateExpirationDate()
	{
		return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
	}
	
}
