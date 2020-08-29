package com.javaJwtexample.util;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 
 * @author Keshab
 *
 */
public class JwtUtil {

	private String secret_key;
	private String userName;
	private String issueUser;
	private String userId;

	public JwtUtil(String secret_key, String userName, String issueUser, String userId) {
		super();
		this.secret_key = secret_key;
		this.userName = userName;
		this.issueUser = issueUser;
		this.userId = userId;
	}

	/**
	 * 
	 * @return the jwt token
	 */
	public String getJwtToken() {

		return Jwts.builder().setId(this.userId).setSubject(this.userName).setIssuer(this.issueUser)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10)))
				.signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode(secret_key.getBytes())).compact();
	}

	public Claims getClaims(String key, String token) {
		return Jwts.parser().setSigningKey(Base64.getEncoder().encode(key.getBytes())).parseClaimsJws(token).getBody();
	}

	/**
	 * 
	 * @param key   key
	 * @param token token
	 * @return the boolean
	 */
	public boolean isValidToken(String key, String token) {
		return this.getClaims(key, token).getExpiration().after(new Date(System.currentTimeMillis()));
	}
}
