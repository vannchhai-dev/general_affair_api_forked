package com.norton.backend.models;

import com.norton.backend.enums.OfficerStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "officer")
public class OfficerModel extends BaseIdModel {

  @NotBlank(message = "First name is required")
  @Size(max = 255)
  @Column(name = "first_name", length = 255)
  private String firstName;

  @NotBlank(message = "Last name is required")
  @Size(max = 255)
  @Column(name = "last_name", length = 255)
  private String lastName;

  @Size(max = 100)
  @Pattern(regexp = "^[0-9+\\-() ]*$", message = "Invalid phone number format")
  private String phone;

  @Email(message = "Invalid email format")
  @Column(unique = true, nullable = true)
  private String email;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private OfficerStatus status;

  @NotNull(message = "Position is required")
  @ManyToOne
  @JoinColumn(name = "position_id", nullable = false)
  private PositionModel position;
}
