package com.norton.backend.dto.request;

import java.util.List;
import lombok.Data;

@Data
public class PermissionRequest {
  private List<String> permissions;
}
