package com.example.invygo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "ROLES_TBL")
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class Role implements Serializable {
	@Id
	@Column(name = "role_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

}
