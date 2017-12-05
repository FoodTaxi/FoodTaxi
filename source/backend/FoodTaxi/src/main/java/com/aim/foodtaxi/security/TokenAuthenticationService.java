package com.aim.foodtaxi.security;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.aim.foodtaxi.repositories.DriverRepository;
import com.aim.foodtaxi.repositories.ShopUserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

class TokenAuthenticationService {
	static final long EXPIRATIONTIME = 864_000_000; // 10 days
	static final String SECRET = "ThisIsASecret";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";
	
	static void addAuthentication(HttpServletResponse res, String username) {
		String JWT = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
	}

	static Authentication getAuthentication(HttpServletRequest request, DriverRepository driverRepo, ShopUserRepository shopUserRepo) {
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			// parse the token.
			String user = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody()
					.getSubject();

			if (user != null) {
				Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
				if(driverRepo.findOneByUsername(user).isPresent()){
					grantedAuthorities.add(new SimpleGrantedAuthority("driver"));
				} else if (shopUserRepo.findOneByUsername(user).isPresent()){
					grantedAuthorities.add(new SimpleGrantedAuthority("shop"));
				}
				return new UsernamePasswordAuthenticationToken(user, null, grantedAuthorities);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
}
