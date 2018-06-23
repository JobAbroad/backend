package jobabroad.security;

import java.util.Calendar;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtil {

	private static String key = "JOB_ABROAD'S_SECRET_TOKEN";

	public String create(String subject) {

		return Jwts.builder().setSubject(subject).setExpiration(getExpirationDate())
				.signWith(SignatureAlgorithm.HS512, key).compact();
	}

	public Jws<Claims> decode(String token) {
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token);
	}

	private Date getExpirationDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR_OF_DAY, 1);
		return cal.getTime();
	}
}