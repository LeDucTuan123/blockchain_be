package com.nft.BanTranhNFT.controller;

import com.nft.BanTranhNFT.model.Order;
import com.nft.BanTranhNFT.model.OrderItem;
import com.nft.BanTranhNFT.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orderitem")
public class OrderItemController {
    @Autowired
    OrderItemService orderItemService;

    @PostMapping("/add")
    public OrderItem addOrderItem(@RequestBody OrderItem orderItem){
        return orderItemService.addOrderItem(orderItem);
    }

    @PutMapping("/update")
    public OrderItem updateOrderItem(@RequestParam("id") int id, @RequestBody OrderItem orderItem){
        return orderItemService.updateOrderItem(id, orderItem);
    }

    @DeleteMapping("/deletee/{id}")
    public boolean deleteOrderItem(@PathVariable("id") int id){
        return orderItemService.deleteOrderItem(id);
    }

    @DeleteMapping("/delete/{id}")
	public OrderItem delete(@PathVariable("id") Integer id) {
		return orderItemService.delete(id);
	}

    @GetMapping("/list")
    public List<OrderItem> getListOrderItem(){
        return orderItemService.getAllOrderItem();
    }

    @GetMapping("/success/{id}")
	public List<Object[]> getProductInSuccessOrder(@PathVariable("id") Integer id) {
		return orderItemService.getProductInSuccessOrder(id);
	}
}
