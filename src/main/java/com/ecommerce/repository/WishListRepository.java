package com.ecommerce.repository;

import com.ecommerce.modal.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<WishList, Long> {

    WishList findByUserId(Long userId);

}
