package com.example.demo.infrastructure.repository;

import com.example.demo.domain.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Long, Estoque> {
}
