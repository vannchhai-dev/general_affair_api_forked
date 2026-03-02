package com.norton.backend.controllers.role;

import com.norton.backend.dto.request.PermissionRequest;
import com.norton.backend.services.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(RoleController.BASE_URL)
public class RoleController {

  public static final String BASE_URL = "/api/roles";
  private final RoleService roleService;

  @PostMapping("/{role}/permissions")
  @PreAuthorize("hasAuthority('MANAGE_ROLES')")
  public ResponseEntity<?> assignPermissions(
      @PathVariable String role, @RequestBody PermissionRequest request) {

    roleService.assignPermissionsToRole(role, request.getPermissions());
    return ResponseEntity.ok("Permissions assigned successfully");
  }
}
