package com.project.shop.domain.userDetails;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class Account extends User {
    private Long id;
    private String nickname;

    public Account(String username, String password, Collection<? extends GrantedAuthority> authorities, Long id, String nickname) {
        super(username, password, authorities);
        this.id = id;
        this.nickname = nickname;
    }
}
