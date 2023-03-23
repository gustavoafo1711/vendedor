package com.example.vendedor.domain.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vendedor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nome")
	private String nome;
	
	@ManyToOne // 01 vendedor pode ter várias lojas
	@JoinColumn(name = "id_loja")
	private Loja loja;
	
	@OneToOne // 01 vendedor só pode ser um tipo de vendedor
	@JoinColumn(name = "id_tipo")
	private Tipo tipo;
	
	@Column(name = "data_cadastro")
	private LocalDate dataCadastro;

}
	