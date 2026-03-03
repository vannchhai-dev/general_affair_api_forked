package com.norton.backend.models;

import com.norton.backend.enums.DepartmentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "department")
public class DepartmentModel extends BaseIdModel {

  @NotBlank(message = "Department name is required")
  @Size(max = 100, message = "Department name must not exceed 100 characters")
  @Column(nullable = false, length = 100)
  private String name;

  @Size(max = 500, message = "Description must not exceed 500 characters")
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private DepartmentStatus status;

  @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
  private List<PositionModel> positions;
}
