package com.nft.BanTranhNFT.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.management.Notification;

import lombok.Data;

import org.hibernate.annotations.NaturalId;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;


@Data
@Table(name = "Users")
@Entity
public class User  implements Serializable{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String phone;
	@NaturalId(mutable = true)
	private String email;
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role;
	private String firstname;
	private String lastname;
	
	
}
