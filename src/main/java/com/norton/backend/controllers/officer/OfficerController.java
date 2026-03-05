package com.norton.backend.controllers.officer;

import com.norton.backend.dto.responses.MeResponse;
import com.norton.backend.services.officer.OfficerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(OfficerController.BASE_URL)
public class OfficerController {

  public static final String BASE_URL = "/api/v1/officer";

  private final OfficerService officerService;

  @GetMapping("/me")
  public ResponseEntity<MeResponse> getMyProfile() {
    return ResponseEntity.ok(officerService.getMyProfile());
  }
}
