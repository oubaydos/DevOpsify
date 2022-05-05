package com.winchesters.devopsify.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationUserRole {
    ADMIN(Set.of()),
    CONTRIBUTOR(Set.of()),
    CLIENT(Set.of());

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public static ApplicationUserRole fromName(String value){
        for(ApplicationUserRole role : ApplicationUserRole.values()){
            if(role.name().equals(value)){
                return role;
            }
        }
        return null;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> grantedAuthorities = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return grantedAuthorities;
    }
}
