package com.project.shop.repository;

import com.project.shop.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface AuthorityRepository extends JpaRepository<Authority,Long> {

    Optional<Authority> findByAuthority(@Param("authority") String authority);

}
