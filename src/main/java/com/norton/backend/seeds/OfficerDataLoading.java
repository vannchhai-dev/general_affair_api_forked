package com.norton.backend.seeds;

import com.norton.backend.enums.DepartmentStatus;
import com.norton.backend.enums.OfficerStatus;
import com.norton.backend.enums.PositionStatus;
import com.norton.backend.models.*;
import com.norton.backend.repositories.*;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 4)
// @Profile("dev")
public class OfficerDataLoading implements CommandLineRunner {

  private final DepartmentRepository departmentRepository;
  private final PositionRepository positionRepository;
  private final OfficerRepository officerRepository;

  public OfficerDataLoading(
      DepartmentRepository departmentRepository,
      PositionRepository positionRepository,
      OfficerRepository officerRepository) {
    this.departmentRepository = departmentRepository;
    this.positionRepository = positionRepository;
    this.officerRepository = officerRepository;
  }

  @Override
  public void run(String... args) {

    if (departmentRepository.count() > 0) {
      return;
    }

    DepartmentModel hr =
        DepartmentModel.builder().name("Human Resources").status(DepartmentStatus.ACTIVE).build();

    DepartmentModel it =
        DepartmentModel.builder().name("IT Department").status(DepartmentStatus.ACTIVE).build();

    departmentRepository.saveAll(List.of(hr, it));

    PositionModel hrManager =
        PositionModel.builder()
            .name("HR Manager")
            .department(hr)
            .status(PositionStatus.ACTIVE)
            .build();

    PositionModel developer =
        PositionModel.builder()
            .name("Software Developer")
            .department(it)
            .status(PositionStatus.ACTIVE)
            .build();

    positionRepository.saveAll(List.of(hrManager, developer));

    OfficerModel officer1 =
        OfficerModel.builder()
            .firstName("John")
            .lastName("Doe")
            .phone("012345678")
            .email("officer1@gmail.com")
            .position(hrManager)
            .status(OfficerStatus.ACTIVE)
            .build();

    OfficerModel officer2 =
        OfficerModel.builder()
            .firstName("Jane")
            .lastName("Smith")
            .phone("098765432")
            .email("officer2@gmail.com")
            .position(developer)
            .status(OfficerStatus.ACTIVE)
            .build();

    officerRepository.saveAll(List.of(officer1, officer2));

    System.out.println("✅ Seed data inserted successfully!");
  }
}
