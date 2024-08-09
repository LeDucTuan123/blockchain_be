package com.nft.BanTranhNFT.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Orderdetails")
public class OrderItem implements Serializable{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer quantity;
	private Double price;
	@JsonBackReference(value = "orderdetail-order")
	@ManyToOne
	@JoinColumn(name = "orderid")
	Order order;
	@JsonBackReference(value = "orderdetail-painting")
	@ManyToOne
	@JoinColumn(name = "paintingid")
	Painting painting;



    public Integer getPaintingid() {
        return painting != null ? painting.getPaintingId() : null;
    }
}
