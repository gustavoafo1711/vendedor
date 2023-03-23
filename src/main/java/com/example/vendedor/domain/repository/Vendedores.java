package com.example.vendedor.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vendedor.domain.entity.Vendedor;

public interface Vendedores extends JpaRepository<Vendedor, Integer>{

}
