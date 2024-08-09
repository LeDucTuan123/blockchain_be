package com.nft.BanTranhNFT.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;

import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Orders")
public class Order implements Serializable{
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "order_id")
    // private int orderId;

    // @JsonBackReference(value = "order-user")
    // @ManyToOne
    // @JoinColumn(name = "customer_id")
    // User customer;

    // @Column(name = "total_amount", nullable = false)
    // private double totalAmount;

    // @Column(name = "order_status", nullable = false, length = 50)
    // private String orderStatus;

    // @Column(name = "created_at", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    // @Temporal(TemporalType.TIMESTAMP)
    // private Date createdAt;

    // @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // @JsonManagedReference(value = "orderitem-order")
    // private List<OrderItem> orderItems;

    // // @JsonManagedReference(value = "orderdetail-order")
	// // @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	// // List<OrderDetail> orderdetails;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderdate;
	private Double totalamount;	
	private String codeorder;
	@JsonBackReference(value = "order-user")
	@ManyToOne
	@JoinColumn(name = "userid")
	User user;
	@OneToOne
	@JoinColumn(name = "statussid")
	Statuss statuss;
	@JsonManagedReference(value = "orderdetail-order")
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<OrderItem> orderdetails;
}
