package com.snackhub.infrastructure.product.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductJpaEntity, Long> {

}
