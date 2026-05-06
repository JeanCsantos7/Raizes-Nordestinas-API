package com.example.demo.infrastructure.repository;

import com.example.demo.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Long, Produto> {
}
