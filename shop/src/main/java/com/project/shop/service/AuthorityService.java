package com.project.shop.service;

import com.project.shop.domain.Authority;
import com.project.shop.exceptions.NoSuchAuthorityException;
import com.project.shop.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorityService {

    private final AuthorityRepository authorityRepository;
    Logger logger = LoggerFactory.getLogger(this.getClass());


    public Authority getUserAuthority() {
        return authorityRepository.findByAuthority("USER").orElseThrow(NoSuchAuthorityException::new);
    }

    public Authority getSellerAuthority() {
        return authorityRepository.findByAuthority("SELLER").orElseThrow(NoSuchAuthorityException::new);
    }

}
