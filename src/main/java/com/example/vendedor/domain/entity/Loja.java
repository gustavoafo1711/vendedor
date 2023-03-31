package com.example.vendedor.domain.entity;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CNPJ;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loja {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_loja")
	private Integer Id;
	
	@NotBlank(message = "Campo Nome da Loja é obrigatório.")
	@Column(name = "nome_loja", length = 100)
	private String nomeLoja;
	
	@NotBlank(message = "Campo CNPJ é obrigatório.")
	@Column(name = "cnpj", length =14)
	@CNPJ(message = "Informe um CNPJ válido.")
	private String cnpj;
	
	@Column(name = "data_cadastro")
	@JsonIgnore(value = true)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;

	@PrePersist // Setar data atual antes de salvar
	public void prePersist(){
		setDataCadastro(LocalDate.now());
	}
}
