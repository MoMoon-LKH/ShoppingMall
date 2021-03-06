package com.project.shop.repository;


import com.project.shop.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByMember_Id(@Param("memberId") Long memberId, Pageable pageable);

    List<Item> findAllByCategory_Id(@Param("categoryId") Long categoryId, Pageable pageable);

    Long countAllBy();

    Long countAllByCategory_Id(@Param("categoryId") Long categoryId);

    @Query("select i.imgUrl from Item i where i.name = :name")
    String getImgUrlByItemName(@Param("name") String itemName);
}

