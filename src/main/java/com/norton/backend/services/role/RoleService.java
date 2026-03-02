package com.norton.backend.services.role;

import com.norton.backend.exceptions.ResourceNotFoundException;
import com.norton.backend.models.PermissionModel;
import com.norton.backend.models.UserRoleModel;
import com.norton.backend.repositories.PermissionRepository;
import com.norton.backend.repositories.UserRoleRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleService {

  private final PermissionRepository permissionRepository;
  private final UserRoleRepository userRoleRepository;

  public void assignPermissionsToRole(String roleName, List<String> permissionNames) {

    UserRoleModel role =
        userRoleRepository
            .findByRoleName(roleName)
            .orElseThrow(() -> new ResourceNotFoundException("Role", "roleName", roleName));

    List<PermissionModel> permissions =
        permissionRepository.findAllByPermissionNameIn(permissionNames);

    if (permissions.size() != permissionNames.size()) {
      throw new RuntimeException("Some permissions not found");
    }

    role.getPermissions().addAll(permissions);
  }
}
