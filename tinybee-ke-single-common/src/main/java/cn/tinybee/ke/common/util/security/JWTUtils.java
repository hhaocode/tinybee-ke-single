package cn.tinybee.ke.common.util.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JWTUtils {

	private static long EXPIRE_TIME = 1000 * 60 * 60 * 24;
	private static String SECRET = "tinybee-secret";

	/**
	 * 
	 * @description 
	 * @param username
	 * @return
	 */
	public static String generateToken(String username, String loginIp) {
		try {
			Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
			Algorithm algorithm = Algorithm.HMAC256(SECRET);

			String token = JWT.create().withClaim("username", username)
					.withClaim("loginIp", loginIp)
					.withExpiresAt(date).sign(algorithm);
			return token;
		}catch (Exception e) {
			return null;
		}
		
	}

	
	public static boolean verify(String token, String username) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(SECRET);
			 //在token中附带了username信息
			JWTVerifier verifier = JWT.require(algorithm)
					.withClaim("username", username).build();
			verifier.verify(token);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    } 
	
	public static String getLoginIp(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("loginIp").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    } 
}
