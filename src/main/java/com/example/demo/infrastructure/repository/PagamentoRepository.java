package com.example.demo.infrastructure.repository;

import com.example.demo.domain.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Long, Pagamento> {
}
