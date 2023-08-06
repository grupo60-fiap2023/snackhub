package com.snackhub.infrastructure.order.persistence;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderJpaEntity, String> {

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, attributePaths = "items")
    List<OrderJpaEntity> findAll();
}
