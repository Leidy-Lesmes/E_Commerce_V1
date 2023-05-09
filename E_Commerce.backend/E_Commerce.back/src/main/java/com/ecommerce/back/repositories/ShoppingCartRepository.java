package com.ecommerce.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.back.entities.ShoppingCart;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, String> {
    List<ShoppingCart> findByClient_Id(String clientId);

    List<ShoppingCart> findByClient_UserName(String clientEmail);

    void deleteByClient_Id(String clientId);

    Long countByClient_Id(String id);
}