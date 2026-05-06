package com.example.demo.infrastructure.repository;

import com.example.demo.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Long, Pedido> {
}
