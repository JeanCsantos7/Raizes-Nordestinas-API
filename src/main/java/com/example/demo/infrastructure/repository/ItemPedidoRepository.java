package com.example.demo.infrastructure.repository;

import com.example.demo.domain.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<Long, ItemPedido> {
}
