package com.example.demo.infrastructure.repository;


import com.example.demo.application.projection.ProdutoProjection;
import com.example.demo.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query(value = "SELECT p.* FROM produto p INNER JOIN estoque e ON p.id = e.produto_id WHERE e.unidade_id = :id", nativeQuery = true)
   List<ProdutoProjection> findByUnidade(@Param("id") Long id);


}
