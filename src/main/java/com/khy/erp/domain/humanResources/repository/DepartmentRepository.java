package com.khy.erp.domain.humanResources.repository;

import com.khy.erp.domain.humanResources.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByName(String name);
    boolean existsByName(String name);
}
