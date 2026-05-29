package com.example.demo.infrastructure.repository;

import com.example.demo.domain.enums.CanalPedido;
import com.example.demo.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByCanalPedido(CanalPedido canalPedido);
}
