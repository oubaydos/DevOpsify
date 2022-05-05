package com.winchesters.devopsify.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade implements IAuthenticationFacade{
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getAuthenticatedUsername(){
        return this.getAuthentication().getName();
    }
    public String getAuthenticatedUserRole(){
        return this.getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(auth->auth.startsWith("ROLE_"))
                .map(auth->auth.substring(5))
                .findFirst()
                .get();
    }
}
