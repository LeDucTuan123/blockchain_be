package com.nft.BanTranhNFT.repository;

import com.nft.BanTranhNFT.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
