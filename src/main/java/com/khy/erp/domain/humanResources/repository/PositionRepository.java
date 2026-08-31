package com.khy.erp.domain.humanResources.repository;

import com.khy.erp.domain.humanResources.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {

    boolean existsByCode(String code);
}
