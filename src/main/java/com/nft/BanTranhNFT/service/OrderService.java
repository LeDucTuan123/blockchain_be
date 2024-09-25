package com.nft.BanTranhNFT.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.nft.BanTranhNFT.model.Order;
import com.nft.BanTranhNFT.model.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    public Order addOrder(Order order);

    // public Order updateOrder(int id, Order order);

    List<Order> getOrderInCart(Integer id);

    Order create(JsonNode order);

    Order payment(JsonNode data);

    List<Order> getAllOrdersSuccess();

    List<Order> getOrderSuccess(Integer id);

//     @Override
// public List<Order> getOrderInCart(Integer id) {
//     return orderRepository.getOrdersInCartByUser(id);
// }

    public boolean deleteOrder(int id);

    public List<Order> getAllOrder();

    public Optional<Order> getOneOrder(int id);

}
