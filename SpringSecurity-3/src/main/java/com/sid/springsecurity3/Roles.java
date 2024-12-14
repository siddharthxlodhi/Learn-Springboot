package com.sid.springsecurity3;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.sid.springsecurity3.Permission.*;


@Getter
@RequiredArgsConstructor
public enum Roles {

    USER(
            Set.of(
                    USER_READ,
                    USER_CREATE,
                    USER_UPDATE,
                    USER_DELETE
            )
    ),
    ADMIN(
            Set.of(
                    ADMIN_CREATE,
                    ADMIN_READ,
                    ADMIN_DELETE,
                    ADMIN_UPDATE
            )
    );


    private final Set<Permission> permission;

    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authoritySet = getPermission().stream().map(permission1 -> new SimpleGrantedAuthority(permission1.getPermission())).distinct().collect(Collectors.toSet());
        authoritySet.add(new SimpleGrantedAuthority("ROLE_" + this.name())); //--> not need because it its is below in request matchers and not checked
        //here role prefix are added explicitly because we are not using the set role method
        return authoritySet;
    }


}
