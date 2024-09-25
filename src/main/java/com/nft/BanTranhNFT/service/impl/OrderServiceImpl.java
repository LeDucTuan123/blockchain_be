package com.nft.BanTranhNFT.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nft.BanTranhNFT.model.Order;
import com.nft.BanTranhNFT.model.OrderItem;
import com.nft.BanTranhNFT.repository.OrderItemRepository;
import com.nft.BanTranhNFT.repository.OrderRepository;
import com.nft.BanTranhNFT.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public Order addOrder(Order order) {
        if(order != null){
            return orderRepository.save(order);
        }
        return null;
    }

    // @Override
    // public Order updateOrder(int id, Order order) {
    //     if(order != null){
    //         Order order1 = orderRepository.getById(id);
    //         if(order1 != null){
    //             order1.setUser(order.getUser()));
    //             order1.setStatuss(order.getStatuss());
    //             order1.setTotalamount(order.getTotalamount());
    //             order1.set(order.getCreatedAt());
    //             // order1.setOrderItems(order.getOrderItems());
    //             return orderRepository.save(order1);
    //         }
    //     }
    //     return null;
    // }

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


   

    @Override
    public List<Order> getOrderInCart(Integer id) {
        return orderRepository.getOrderInCartByUser(id);
    }
  
    // @Override
	// public Order getOrderInCart(Integer id) {
	// 	Order o = orderRepository.getOrderInCartByUser(id);
	// 	return o;
	// }

    // public Order create(JsonNode order) {
	// 	ObjectMapper mapper = new ObjectMapper();
	// 	Order o = mapper.convertValue(order, Order.class);

	// 	List<Order> orderFromDB = orderRepository.getOrderInCartByUser(o.getUser().getId());
	// 	if (orderFromDB != null) {
	// 		List<OrderItem> list = o.getOrderdetails();
	// 		List<OrderItem> orderDetailFromDB = orderItemRepository.getOrderDetailByOrderId(orderFromDB.get(0));
	// 		// lưu orderdetail
	// 		for (OrderItem od1 : list) {
	// 			boolean isSave = false;
	// 			for (OrderItem od2 : orderDetailFromDB) {
	// 				if (od1.getPainting() != null && od2.getPainting() != null) {
	// 					if (od1.getPainting().getPaintingId() == od2.getPainting().getPaintingId()) {
	// 						od2.setQuantity(od1.getQuantity() + od2.getQuantity());
	// 						orderItemRepository.save(od2);
	// 						isSave = true;
	// 						break;
	// 					}
	// 				}
					

	// 			}
	// 			if (!isSave) {
	// 				od1.setOrder(orderFromDB);
	// 				orderItemRepository.save(od1);
	// 			}
	// 		}
	// 		return orderRepository.save(orderFromDB);
	// 	}
	// 	return orderRepository.save(o);

	// }
    @Override
    public Order create(JsonNode order) {
        ObjectMapper mapper = new ObjectMapper();
        Order o = mapper.convertValue(order, Order.class);
    
        // Lấy danh sách đơn hàng từ cơ sở dữ liệu
        List<Order> ordersFromDB = orderRepository.getOrderInCartByUser(o.getUser().getId());
        
        Order orderFromDB = null;
    
        // Nếu có đơn hàng trong giỏ hàng, chọn đơn hàng đầu tiên hoặc xử lý theo cách của bạn
        if (ordersFromDB != null && !ordersFromDB.isEmpty()) {
            orderFromDB = ordersFromDB.get(0); // Giả định rằng bạn xử lý đơn hàng đầu tiên nếu có nhiều hơn một đơn hàng
            List<OrderItem> list = o.getOrderdetails();
            List<OrderItem> orderDetailFromDB = orderItemRepository.getOrderDetailByOrderId(orderFromDB.getId());
    
            // Lưu orderdetail
            for (OrderItem od1 : list) {
                boolean isSave = false;
                for (OrderItem od2 : orderDetailFromDB) {
                    if (od1.getPainting() != null && od2.getPainting() != null) {
                        if (od1.getPainting().getPaintingId() == od2.getPainting().getPaintingId()) {
                            od2.setQuantity(od1.getQuantity() + od2.getQuantity());
                            orderItemRepository.save(od2);
                            isSave = true;
                            break;
                        }
                    }
                }
                if (!isSave) {
                    od1.setOrder(orderFromDB);
                    orderItemRepository.save(od1);
                }
            }
            return orderRepository.save(orderFromDB);
        } else {
            // Nếu không có đơn hàng trong giỏ hàng, lưu đơn hàng mới
            return orderRepository.save(o);
        }
    }

    @Override
	public List<Order> getAllOrdersSuccess() {
		return orderRepository.findAll();
	}

    @Override
	public List<Order> getOrderSuccess(Integer id) {
		return orderRepository.getOrderSuccess(id);
	}

@Override
	public Order payment(JsonNode data) {
		List<OrderItem> orderdetails = new ArrayList<OrderItem>();
		ObjectMapper mapper = new ObjectMapper();
		Order order = mapper.convertValue(data, Order.class);

	
		order.setOrderdetails(orderdetails);
	
		Order o = orderRepository.save(order);
		TypeReference<List<OrderItem>> type = new TypeReference<List<OrderItem>>() {
		};
		List<OrderItem> list = mapper.convertValue(data.get("orderdetails"), type);
		for (OrderItem od : list) {
			od.setOrder(o);
			orderItemRepository.save(od);
		}
		return o;
	}

}
