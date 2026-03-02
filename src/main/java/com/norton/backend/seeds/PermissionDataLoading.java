package com.norton.backend.seeds;

import com.norton.backend.models.PermissionModel;
import com.norton.backend.models.UserRoleModel;
import com.norton.backend.repositories.PermissionRepository;
import com.norton.backend.repositories.UserRoleRepository;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PermissionDataLoading implements CommandLineRunner {

  private final PermissionRepository permissionRepository;
  private final UserRoleRepository roleRepository;

  @Override
  public void run(String... args) {

    PermissionModel createUser = createPermission("CREATE_USER");
    PermissionModel deleteUser = createPermission("DELETE_USER");
    PermissionModel updateUser = createPermission("UPDATE_USER");
    PermissionModel viewUser = createPermission("VIEW_USER");
    PermissionModel manageRoles = createPermission("MANAGE_ROLES");

    createOrUpdateRole(
        "ROLE_ADMIN",
        "System Administrator",
        Set.of(createUser, deleteUser, updateUser, viewUser, manageRoles));

    createOrUpdateRole("ROLE_MANAGER", "Manager Role", Set.of(createUser, updateUser, viewUser));

    createOrUpdateRole("ROLE_OFFICER", "Officer Role", Set.of(viewUser));
  }

  private PermissionModel createPermission(String name) {
    return permissionRepository
        .findByPermissionName(name)
        .orElseGet(
            () ->
                permissionRepository.save(PermissionModel.builder().permissionName(name).build()));
  }

  private void createOrUpdateRole(
      String roleName, String description, Set<PermissionModel> permissions) {

    UserRoleModel role =
        roleRepository
            .findByRoleName(roleName)
            .orElseGet(
                () ->
                    roleRepository.save(
                        UserRoleModel.builder()
                            .roleName(roleName)
                            .description(description)
                            .permissions(Set.of()) // start empty
                            .build()));

    role.getPermissions().addAll(permissions);

    roleRepository.save(role);
  }
}
