package com.project.shop.repository;


import com.project.shop.domain.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByMember_MemberIdOrderByCreateDateDesc(@Param("memberId") Long memberId, Pageable pageable);

    List<Item> findByCategory_IdOrderByCreateDateDesc(@Param("categoryId") Long categoryId, Pageable pageable);

}
