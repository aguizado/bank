package com.example.bank.controller;

import com.example.bank.model.dto.ProfileDto;
import com.example.bank.service.IprofileService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * . Class ProfileController
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/26
 */
@RestController
@RequiredArgsConstructor
public class ProfileController {
  
  @Autowired
  IprofileService profileService;
  
  /**
   * . This method is to save Profile
   *
   * @param profile This is the first parameter
   * @return a HTTP Status
   */
  @PostMapping("/profile")
  public ResponseEntity<ProfileDto> createProfile(@RequestBody ProfileDto profile) {
    try {
      ProfileDto profileDto = profileService.createProfile(profile);
      return new ResponseEntity<>(profileDto, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  /**
   * . This method is to get Profile
   *
   * @return a HTTP Status
   */
  @GetMapping("/profile/getAll")
  public ResponseEntity<List<ProfileDto>> getProfiles() { 
    try {
      List<ProfileDto> profileList = profileService.getProfiles();
      return ResponseEntity.ok(profileList);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }
  
  /**
   * . This method is to get Profile
   *
   * @param profileId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/profile/{profileId}")
  public ResponseEntity<ProfileDto> getProfile(
      @PathVariable("profileId") Integer profileId) {
    Optional<ProfileDto> opProfile = profileService.getProfile(profileId);
    if (opProfile.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(opProfile.get());
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }
  
  /**
   * . This method is to update Profile
   *
   * @param profileId This is the first parameter
   * @param profile   This is the second parameter
   * @return a HTTP Status
   */
  @PutMapping("/profile/{profileId}")
  public ResponseEntity<ProfileDto> editCard(@PathVariable("profileId") Integer profileId,
      @RequestBody ProfileDto profile) {
    Optional<ProfileDto> opProfile = profileService.getProfile(profileId);
    if (opProfile.isPresent()) {
      return new ResponseEntity<>(profileService.editProfile(profile), HttpStatus.OK);
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

}
