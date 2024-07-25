package com.nft.BanTranhNFT.repository;

import com.nft.BanTranhNFT.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
