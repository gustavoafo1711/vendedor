package com.example.vendedor.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vendedor.domain.entity.Tipo;

public interface Tipos extends JpaRepository<Tipo, Integer>{

}
