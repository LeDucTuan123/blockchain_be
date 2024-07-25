package com.nft.BanTranhNFT.controller;

import com.nft.BanTranhNFT.model.Order;
import com.nft.BanTranhNFT.model.Painting;
import com.nft.BanTranhNFT.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/add")
    public Order addOrder(@RequestBody Order order){
        return orderService.addOrder(order);
    }

    @PutMapping("/update")
    public Order updateOrder(@RequestParam("id") int id, @RequestBody Order order){
        return orderService.updateOrder(id, order);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteOrder(@PathVariable("id") int id){
        return orderService.deleteOrder(id);
    }

    @GetMapping("/list")
    public List<Order> getListOrder(){
        return orderService.getAllOrder();
    }
}
