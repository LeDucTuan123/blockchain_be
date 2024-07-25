package com.nft.BanTranhNFT.service.impl;


import com.nft.BanTranhNFT.model.OrderItem;
import com.nft.BanTranhNFT.repository.OrderItemRepository;
import com.nft.BanTranhNFT.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public OrderItem addOrderItem(OrderItem orderItem) {
        if(orderItem != null){
            return orderItemRepository.save(orderItem);
        }
        return null;
    }

    @Override
    public OrderItem updateOrderItem(int id, OrderItem orderItem) {
        if(orderItem != null){
            OrderItem orderItem1 = orderItemRepository.getById(id);
            if(orderItem1 != null){
                orderItem1.setOrder(orderItem.getOrder());
                orderItem1.setPainting(orderItem.getPainting());
                orderItem1.setQuantity(orderItem.getQuantity());
                orderItem1.setPrice(orderItem.getPrice());
                return orderItemRepository.save(orderItem1);
            }
        }
        return null;
    }

    @Override
    public boolean deleteOrderItem(int id) {
        if(id >= 1){
            OrderItem orderItem = orderItemRepository.getById(id);
            if(orderItem != null){
                orderItemRepository.delete(orderItem);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<OrderItem> getAllOrderItem() {
        return orderItemRepository.findAll();
    }

    @Override
    public Optional<OrderItem> getOneOrderItem(int id) {
        return orderItemRepository.findById(id);
    }
}
