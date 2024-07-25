package com.nft.BanTranhNFT.service;

import com.nft.BanTranhNFT.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    public Order addOrder(Order order);

    public Order updateOrder(int id, Order order);

    public boolean deleteOrder(int id);

    public List<Order> getAllOrder();

    public Optional<Order> getOneOrder(int id);
}
