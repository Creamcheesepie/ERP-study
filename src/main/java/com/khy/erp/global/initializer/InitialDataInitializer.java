package com.khy.erp.global.initializer;

import com.khy.erp.domain.humanResources.entity.Department;
import com.khy.erp.domain.humanResources.entity.Position;
import com.khy.erp.domain.humanResources.repository.DepartmentRepository;
import com.khy.erp.domain.humanResources.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitialDataInitializer implements ApplicationRunner {

    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        initializeDepartments();
        initializePositions();
    }

    private void initializeDepartments() {
        List.of("경영지원팀", "인사팀", "개발팀","임시팀").forEach(name -> {
            if (!departmentRepository.existsByName(name)) {
                departmentRepository.save(new Department(name));
            }
        });
    }

    private void initializePositions() {
        List.of(
                new Position("사원", "STAFF", 1),
                new Position("대리", "ASSOCIATE", 2),
                new Position("과장", "MANAGER", 3),
                new Position("차장", "DEPUTY_MANAGER", 4),
                new Position("부장", "GENERAL_MANAGER", 5),
                new Position("임시","TEMPORAL_POSITION",0)
        ).forEach(position -> {
            if (!positionRepository.existsByCode(position.getCode())) {
                positionRepository.save(position);
            }
        });
    }
}
