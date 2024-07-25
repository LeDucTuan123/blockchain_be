package com.nft.BanTranhNFT.service;

import com.nft.BanTranhNFT.model.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderItemService {
    public OrderItem addOrderItem(OrderItem orderItem);

    public OrderItem updateOrderItem(int id, OrderItem orderItem);

    public boolean deleteOrderItem(int id);

    public List<OrderItem> getAllOrderItem();

    public Optional<OrderItem> getOneOrderItem(int id);
}
