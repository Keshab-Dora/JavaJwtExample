package com.javaJwtexample.main;

import com.javaJwtexample.util.JwtUtil;

/**
 * 
 * @author Keshab
 *
 */
public class Mainclass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String secret_key = "test123";
		String userName = "Derrick paul";
		String issueUser = "keshab";
		String userId = "E003";
		JwtUtil jwt = new JwtUtil(secret_key, userName, issueUser, userId);
		String token = jwt.getJwtToken();
		System.out.println("Jwt generated Token :: " + token);
		System.out.println("Jwt get subject token :: " + jwt.getClaims(secret_key, token));
		System.out.println("validate Jwt Token :: "+jwt.isValidToken(secret_key, token));
	}

}
