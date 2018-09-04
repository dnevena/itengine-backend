package com.example.nevena.internship.config.security;

import com.example.nevena.internship.domain.User;
import com.example.nevena.internship.domain.enumeration.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Date;


public final class JWTUtils {

    // one month in seconds, adjust as necessary
    private static final long VALIDITY = 30 * 24 * 60 * 60;

    private static final String AUTHORITIES_KEY = "auth";
    private static final String SECRET_KEY = "secret-key";

    private JWTUtils() {
    }

    public static String createToken(User user) {

        final ZonedDateTime validity = ZonedDateTime.now(ZoneId.of("UTC")).plusSeconds(VALIDITY);

        return Jwts.builder().setSubject(user.getId().toString()).claim(AUTHORITIES_KEY, user.getRole().name()).signWith(SignatureAlgorithm.HS512, SECRET_KEY).setExpiration(Date.from(validity.toInstant())).compact();
    }

    public static Authentication getAuthentication(String token, String secretKey) {

        final Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        final Long userId = Long.valueOf(claims.getSubject());
        final String role = claims.get(AUTHORITIES_KEY).toString();

        return new PreAuthenticatedAuthenticationToken(userId, null, Collections.singletonList(new SimpleGrantedAuthority(role)));
    }

}
