package com.nft.BanTranhNFT.repository;

import com.nft.BanTranhNFT.model.Order;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    	// @Query("select o from Order o where o.statuss.id = 1 and o.user.id = ?1")
		// Order getOrderInCartByUser(Integer id);

		// @Query("select o from Order o where o.statuss.id = 1 and o.user.id = ?1")
		// Order getOrderInCartByUser(Integer id);

		@Query("select o from Order o where o.statuss.id = 1 and o.user.id = ?1")
		List<Order> getOrderInCartByUser(Integer id);	

		@Query("select o from Order o where o.statuss.id in (2, 3, 4) and o.user.id = ?1")
		List<Order> getOrderSuccess(Integer id);

}
