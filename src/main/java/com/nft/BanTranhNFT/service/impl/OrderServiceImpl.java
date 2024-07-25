package com.nft.BanTranhNFT.service.impl;

import com.nft.BanTranhNFT.model.Order;
import com.nft.BanTranhNFT.repository.OrderRepository;
import com.nft.BanTranhNFT.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order addOrder(Order order) {
        if(order != null){
            return orderRepository.save(order);
        }
        return null;
    }

    @Override
    public Order updateOrder(int id, Order order) {
        if(order != null){
            Order order1 = orderRepository.getById(id);
            if(order1 != null){
                order1.setCustomer(order.getCustomer());
                order1.setOrderStatus(order.getOrderStatus());
                order1.setTotalAmount(order.getTotalAmount());
                order1.setCreatedAt(order.getCreatedAt());
                // order1.setOrderItems(order.getOrderItems());
                return orderRepository.save(order1);
            }
        }
        return null;
    }

    @Override
    public boolean deleteOrder(int id) {
        if(id >= 1){
            Order order = orderRepository.getById(id);
            if(order != null){
                orderRepository.delete(order);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOneOrder(int id) {
        return orderRepository.findById(id);
    }
}
