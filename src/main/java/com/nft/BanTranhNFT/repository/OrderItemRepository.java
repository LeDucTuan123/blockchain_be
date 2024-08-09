package com.nft.BanTranhNFT.repository;

import com.nft.BanTranhNFT.model.OrderItem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    @Query("select d from OrderItem d where d.order.id = ?1")
	List<OrderItem> getOrderDetailByOrderId(Integer id);

    @Modifying
	@Query("delete from OrderItem od where od.id = ?1")
	void deleteOrderDetailById(Integer id);

	
}
