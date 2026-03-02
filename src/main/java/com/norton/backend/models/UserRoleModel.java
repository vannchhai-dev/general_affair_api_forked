package com.norton.backend.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class UserRoleModel extends BaseIdModel {

  @Column(name = "role_name", nullable = false, unique = true)
  private String roleName;

  private String description;

  @OneToMany(mappedBy = "role")
  private List<UserModel> users;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "role_permission",
      joinColumns = @JoinColumn(name = "role_id"),
      inverseJoinColumns = @JoinColumn(name = "permission_id"))
  private Set<PermissionModel> permissions = new HashSet<>();
}
