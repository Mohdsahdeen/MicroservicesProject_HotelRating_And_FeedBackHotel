package com.userservices.entities;




import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "micro_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	
	@Id
	@Column(name="id")
	private String userId;
	
	@Column(name = "Name" , length = 20)
	private String name;
	
	@Column(name = "Email")
	private String email;
	
	@Column(name="About")
	private String about;
	
	
	@Transient
	private List<Rating> ratings = new ArrayList<>();
	
	

}
