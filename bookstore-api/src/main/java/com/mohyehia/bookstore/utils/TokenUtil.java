package com.mohyehia.bookstore.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtil {
	
	private final String CLAIMS_SUBJECT = "sub";
	private final String CLAIMS_CREATED = "created";
	private final String AUTHORITIES = "auth";
	
	@Value("${auth.expiration}")
	private Long TOKEN_VALIDITY = 604800L;
	
	@Value("${auth.secret}")
	private String TOKEN_SECRET;
	
	public String generateToken(UserDetails userDetails) {
		/**
		 * 1 => Claims
		 * 2 => Expiration
		 * 3 => Sign
		 * 4 => Compact
		 */
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIMS_SUBJECT, userDetails.getUsername());
		claims.put(CLAIMS_CREATED, new Date());
		claims.put(AUTHORITIES, userDetails.getAuthorities());
		
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(generateExpirationDate())
				.signWith(SignatureAlgorithm.HS256, TOKEN_SECRET)
				.compact();
	}

	private Date generateExpirationDate() {
		return new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000);
	}
}
