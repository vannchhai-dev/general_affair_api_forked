package com.norton.backend.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "permissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionModel extends BaseIdModel {

  @Column(name = "per_name", nullable = false, unique = true)
  private String permissionName;
}
