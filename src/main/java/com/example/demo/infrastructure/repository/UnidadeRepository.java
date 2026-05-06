package com.example.demo.infrastructure.repository;

import com.example.demo.domain.model.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadeRepository extends JpaRepository<Long, Unidade> {
}
