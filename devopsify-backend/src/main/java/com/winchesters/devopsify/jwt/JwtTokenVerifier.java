package com.winchesters.devopsify.jwt;

import com.google.common.base.Strings;
import com.winchesters.devopsify.exception.jwt.JwtTokenNotValidException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtTokenVerifier extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;

    public JwtTokenVerifier(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String key = jwtConfig.getSecretKey();
        String tokenPrefix = jwtConfig.getTokenPrefix();
        Cookie cookie = WebUtils.getCookie(request, "Authorization");
        String authorizationHeader=null;
        if(cookie != null)
            authorizationHeader = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
        else
            authorizationHeader=request.getHeader("Authorization");
        if(authorizationHeader!=null && authorizationHeader.contains("%20")) authorizationHeader = URLDecoder.decode(authorizationHeader,StandardCharsets.UTF_8);
        
        if(Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(tokenPrefix)){
            filterChain.doFilter(request,response);
            return;
        }
        String token = authorizationHeader.replace(tokenPrefix, "");

        try{
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(key.getBytes()))
                    .build()
                    .parseClaimsJws(token);

            Claims body = claimsJws.getBody();
            String username = body.getSubject();
            var authorities =(List<Map<String,String>>) body.get("authorities");
            List<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
                    .map(m -> new SimpleGrantedAuthority(m.get("authority")))
                    .collect(Collectors.toList());
            Authentication authentication =new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    simpleGrantedAuthorities
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch(JwtException e){
            throw new JwtTokenNotValidException();
        }
        filterChain.doFilter(request,response);
    }
}
