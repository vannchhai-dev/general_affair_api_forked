package com.norton.backend.dto.responses;

import lombok.Data;

@Data
public class MeResponse {

  private String uuid;
  private String username;
  private String fullName;
  private String role;

  private OfficerResponse officer;
}
